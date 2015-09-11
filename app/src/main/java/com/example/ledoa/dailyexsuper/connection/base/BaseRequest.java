package com.example.ledoa.dailyexsuper.connection.base;

import android.os.AsyncTask;


import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.util.CommonUtils;
import com.example.ledoa.dailyexsuper.util.UserPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public abstract class BaseRequest<T extends BaseResponse> {

    protected abstract void onStart();

    protected abstract void onSuccess(T entity, int statusCode, String message);

    protected abstract void onError(int statusCode, String message);

    protected abstract T handleData(JSONObject json) throws JSONException;

    private String method;
    private String url;
    private HashMap<String, String> headers;
    private HashMap<String, String> params;

    private AsyncTask<Void, Void, T> task = null;

    public BaseRequest(String method, String url, HashMap<String, String> headers) {
        this(method, url, headers, new HashMap<String, String>());
    }

    public BaseRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.params = params;

        if (this.headers == null) {
            this.headers = new HashMap<>();
        }
        UserPref userPref = new UserPref();
        if (userPref.getUser() != null) {
            this.headers.put("Authorization", MainApplication.getContext().getResources().getString(R.string.token_builder, userPref.getUser().token));
        }
        buildTask();
    }

    private void buildTask() {
        task = new AsyncTask<Void, Void, T>() {

            @Override
            protected void onPreExecute() {
                if (isCancelled()) {
                    return;
                }
                onStart();
            }

            @Override
            protected T doInBackground(Void... param) {
                if (isCancelled()) {
                    return null;
                }
                HttpURLConnection conn = null;
                URL urlObject;
                String response = "";
                T result = null;
                try {
                    urlObject = new URL(url);
                    conn = (HttpURLConnection) urlObject.openConnection();
                    conn.setReadTimeout(Config.MAX_TIMEOUT);
                    conn.setConnectTimeout(Config.MAX_TIMEOUT);
                    conn.setRequestMethod(method);
                    conn.setUseCaches(false);
                    if (method.equals(Method.POST)) {
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                    }

                    // add headers
                    if (headers != null && headers.size() > 0) {
                        for (Map.Entry<String, String> entry : headers.entrySet()) {
                            conn.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }

                    // add params
                    if (params != null && params.size() > 0) {
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(getPostDataString(params));

                        writer.flush();
                        writer.close();
                        os.close();
                    }

                    int responseCode = conn.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                        if (response.length() > 0) {
                            result = handleData(new JSONObject(response));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(T result) {
                if (isCancelled()) {
                    return;
                }
                if (result != null) {
                    if (result.statusCode == 200 && result.success) {
                        onSuccess(result, result.statusCode, result.message);
                    } else {
                        onError(result.statusCode, result.message);
                    }
                } else {
                    onError(0, MainApplication.getContext().getString(R.string.error_network_request_fail));
                }
            }
        };
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), Config.CHARSET));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), Config.CHARSET));
        }
        return result.toString();
    }

    public void execute() {
        if (CommonUtils.isNetworkConnected(MainApplication.getContext())) {
            if (task != null) {
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        } else {
            onError(0, MainApplication.getContext().getResources().getString(R.string.error_network_no_internet));
        }
    }

    public void cancel() {
        if (task != null) {
            task.cancel(true);
        }
    }

}
