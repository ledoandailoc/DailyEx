package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemThemBan;

import java.util.List;

public class ThemBanAdapter extends ArrayAdapter<ItemThemBan> {
    Context context;
    int resID;
    List<ItemThemBan> objects;
    int result;
    
    
    public ThemBanAdapter(Context context, int resource, List<ItemThemBan> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resID = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, resID, null);
        ImageView avatar = (ImageView) view.findViewById(R.id.AvatarThemBan);
        TextView text = (TextView) view.findViewById(R.id.textViewThemBan);
        final Button button = (Button)view.findViewById(R.id.buttonThemBan);
        
        final ItemThemBan item = objects.get(position);

        //avatar.setImageResource(item.getAvatar());
        text.setText(item.getText());
        avatar.setImageBitmap(item.getAvatar());
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AsyncAddfreind addfreind =new AsyncAddfreind();
				addfreind.execute(item.getMyAccountId(),item.getFriendAccountId());
				 
					 button.setText("Hủy kết bạn");
					 button.setEnabled(false);
				 
				
			}
		});
        
        return view;
    }
    public static Bitmap decodeBase64(String input)
	{
	    byte[] decodedByte = Base64.decode(input, 0);
	    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
    
    public class AsyncAddfreind extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {

            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if(integer > 0){
            	//button.setText("Hủy yêu cầu");
            	result=integer;
            	 Toast.makeText(context, "Gui loi moi ket ban thanh cong!", Toast.LENGTH_SHORT).show();
            }
               
            else
            {
            	
                Toast.makeText(context, "Gui loi moi that bai!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
   
}
