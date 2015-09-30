package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.caches.ImageLoaderUtil;
import com.example.ledoa.dailyexsuper.customview.CircleImageView;
import com.example.ledoa.dailyexsuper.socketio.MySocket;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;

public class ThemBanAdapter extends ArrayAdapter<User> {
    private Context context;
    private ArrayList<User> mList;
    private Socket mSocket;

    public ThemBanAdapter(Context context, ArrayList<User> list) {
        super(context, 0, list);
        this.context = context;
        this.mList = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_layout_themban, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvId.setText("ID: "+mList.get(position)._id);
        viewHolder.tvUsername.setText(mList.get(position).username);
        if (mList.get(position).avatar != null) {
            ImageLoaderUtil.display(mList.get(position).avatar, viewHolder.ivUseravatar);
        } else {
            viewHolder.ivUseravatar.setImageResource(R.drawable.avt);
        }

        viewHolder.btThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket = MainApplication.getMySocket().getSocket();
                mSocket.emit("addFriend", mList.get(position)._id);
                viewHolder.btThemBan.setVisibility(View.INVISIBLE);
                Toast.makeText(context, "Đã gửi yêu cầu kết bạn thành công.", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public class ViewHolder {

        public TextView tvId;
        public TextView tvUsername;
        public CircleImageView ivUseravatar;
        public Button btThemBan;

        public ViewHolder(View rootView) {
            tvId = (TextView) rootView.findViewById(R.id.tv_id);
            tvUsername = (TextView) rootView.findViewById(R.id.tv_username);
            ivUseravatar = (CircleImageView) rootView.findViewById(R.id.iv_avatar_user);
            btThemBan = (Button) rootView.findViewById(R.id.btThemBan);
            btThemBan.setText("Thêm");
        }
    }

}
