package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Data;
import com.example.ledoa.dailyexsuper.util.Constant;

import java.util.ArrayList;


public class MessageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Data> mList;

    public MessageAdapter(Context context, ArrayList<Data> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).typeDisplay;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR:
                ViewHolderChatTextLeftWithAvatar viewHolder1;
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.chat_text_left_with_avatar, parent, false);
                    viewHolder1 = new ViewHolderChatTextLeftWithAvatar(convertView);
                    convertView.setTag(viewHolder1);
                } else {
                    viewHolder1 = (ViewHolderChatTextLeftWithAvatar) convertView.getTag();
                }
                viewHolder1.tvMessage.setText(mList.get(position).message.message);
                if (mList.get(position).user.avatar != null) {
                    //ImageLoaderUtil.display(mList.get(position).user.avatar, viewHolder1.imAvatarChat);
                } else {
                    viewHolder1.imAvatarChat.setImageResource(R.drawable.avt);
                }
                break;
            case Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR:
                ViewHolderChatTextRightWithAvatar viewHolder2;
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.chat_text_righ_with_avatar, parent, false);
                    viewHolder2 = new ViewHolderChatTextRightWithAvatar(convertView);
                    convertView.setTag(viewHolder2);
                } else {
                    viewHolder2 = (ViewHolderChatTextRightWithAvatar) convertView.getTag();
                }
                viewHolder2.tvMessage.setText(mList.get(position).message.message);
                if (mList.get(position).user.avatar != null) {
                    //.display(mList.get(position).user.avatar, viewHolder2.imAvatarChat);
                } else {
                    viewHolder2.imAvatarChat.setImageResource(R.drawable.avt);
                }
                break;
            case Constant.CHAT_TYPE_TEXT_LEFT_WITHOUT_AVATAR:
                ViewHolderChatTextLeftWithoutAvatar viewHolder3;
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.chat_text_left_without_avatar, parent, false);
                    viewHolder3 = new ViewHolderChatTextLeftWithoutAvatar(convertView);
                    convertView.setTag(viewHolder3);
                } else {
                    viewHolder3 = (ViewHolderChatTextLeftWithoutAvatar) convertView.getTag();
                }
                viewHolder3.tvMessage.setText(mList.get(position).message.message);
                break;
            case Constant.CHAT_TYPE_TEXT_RIGHT_WITHOUT_AVATAR:
                ViewHolderChatTextRightWithoutAvatar viewHolder4;
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.chat_text_right_without_avatar, parent, false);
                    viewHolder4 = new ViewHolderChatTextRightWithoutAvatar(convertView);
                    convertView.setTag(viewHolder4);
                } else {
                    viewHolder4 = (ViewHolderChatTextRightWithoutAvatar) convertView.getTag();
                }
                viewHolder4.tvMessage.setText(mList.get(position).message.message);
                break;
            case Constant.CHAT_TYPE_IMAGE_LEFT_WITH_AVATAR:
                final ViewHolderChatImageLeftWithAvatar viewHolder5;
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.chat_image_left_with_avatar, parent, false);
                    viewHolder5 = new ViewHolderChatImageLeftWithAvatar(convertView);
                    convertView.setTag(viewHolder5);
                } else {
                    viewHolder5 = (ViewHolderChatImageLeftWithAvatar) convertView.getTag();
                }
                if (mList.get(position).user.avatar != null) {
                    //ImageLoaderUtil.display(mList.get(position).user.avatar, viewHolder5.imAvatarChat);
                } else {
                    viewHolder5.imAvatarChat.setImageResource(R.drawable.avt);
                }
                //ImageLoaderUtil.display(mList.get(position).file.thumbnail, viewHolder5.ivPicture);
                break;
            case Constant.CHAT_TYPE_IMAGE_RIGHT_WITH_AVATAR:
                final ViewHolderChatImageRightWithAvatar viewHolder6;
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.chat_image_right_with_avatar, parent, false);
                    viewHolder6 = new ViewHolderChatImageRightWithAvatar(convertView);
                    convertView.setTag(viewHolder6);
                } else {
                    viewHolder6 = (ViewHolderChatImageRightWithAvatar) convertView.getTag();
                }
                if (mList.get(position).user.avatar != null) {
                    //ImageLoaderUtil.display(mList.get(position).user.avatar, viewHolder6.imAvatarChat);
                } else {
                    viewHolder6.imAvatarChat.setImageResource(R.drawable.avt);
                }
                //ImageLoaderUtil.display(mList.get(position).file.thumbnail, viewHolder6.ivPicture);
                break;
        }

        return convertView;
    }

    public class ViewHolderChatTextRightWithAvatar {
        TextView tvMessage;
        ImageView imAvatarChat;

        public ViewHolderChatTextRightWithAvatar(View rootView) {
            tvMessage = (TextView) rootView.findViewById(R.id.tvSms);
            imAvatarChat = (ImageView) rootView.findViewById(R.id.iv_avatar_myself);
        }
    }

    public class ViewHolderChatTextRightWithoutAvatar {
        TextView tvMessage;

        public ViewHolderChatTextRightWithoutAvatar(View rootView) {
            tvMessage = (TextView) rootView.findViewById(R.id.tvSms);
        }
    }

    public class ViewHolderChatTextLeftWithAvatar {
        TextView tvMessage;
        ImageView imAvatarChat;

        public ViewHolderChatTextLeftWithAvatar(View rootView) {
            tvMessage = (TextView) rootView.findViewById(R.id.tvSms);
            imAvatarChat = (ImageView) rootView.findViewById(R.id.iv_avatar_other);
        }
    }

    public class ViewHolderChatTextLeftWithoutAvatar {
        TextView tvMessage;

        public ViewHolderChatTextLeftWithoutAvatar(View rootView) {
            tvMessage = (TextView) rootView.findViewById(R.id.tvSms);
        }
    }

    public class ViewHolderChatImageLeftWithAvatar {
        ImageView ivPicture;
        ImageView imAvatarChat;

        public ViewHolderChatImageLeftWithAvatar(View rootView) {
            ivPicture = (ImageView) rootView.findViewById(R.id.iv_picture_other);
            imAvatarChat = (ImageView) rootView.findViewById(R.id.iv_avatar_other);
        }
    }

    public class ViewHolderChatImageRightWithAvatar {
        ImageView ivPicture;
        ImageView imAvatarChat;

        public ViewHolderChatImageRightWithAvatar(View rootView) {
            ivPicture = (ImageView) rootView.findViewById(R.id.iv_picture_myself);
            imAvatarChat = (ImageView) rootView.findViewById(R.id.iv_avatar_myself);
        }
    }
}
