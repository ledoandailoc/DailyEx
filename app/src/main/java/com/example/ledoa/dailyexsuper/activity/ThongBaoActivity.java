package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.ThongBaoAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemThongBao;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoActivity extends Activity {

	String itemText[] = {"AnhtuanUit da gui loi moi ket ban cho ban",
			"LeLoc da gui loi moi ket ban cho ban", "Ngoc Man da ru ban choi Excercise"};
	ArrayList<ItemThongBao> listThongBao;
	ListView listViewThongBao;
	ThongBaoAdapter adapterTB;
	List<ItemThongBao> resultNotify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_bao);		
		
		/*getActionBar().setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.theme));*/

		listThongBao = new ArrayList<ItemThongBao>();
		listViewThongBao= (ListView)findViewById(R.id.listViewThongBao);
		
		
		 for(int i=0; i< itemText.length;i++){
	            ItemThongBao item = new ItemThongBao();
	            
	            item.setText(itemText[i]);
	            listThongBao.add(item);
	        }	 
		 adapterTB = new ThongBaoAdapter(ThongBaoActivity.this, R.layout.custom_layout_thongbao, listThongBao);
		 listViewThongBao.setAdapter(adapterTB);
		 
		 AsyncCheckNotify asyncCheckNotify = new AsyncCheckNotify();
		 asyncCheckNotify.execute(10);
		 
		 listViewThongBao.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder b=new AlertDialog.Builder(ThongBaoActivity.this);
				 
				b.setTitle("Thông báo");
				b.setMessage("Lời mời kết bạn");
				b.setPositiveButton("Chấp nhận", new DialogInterface. OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
				/*finish();*/
				
					AsyncAcceptFreind asyncAcceptFreind = new AsyncAcceptFreind();
					asyncAcceptFreind.execute(listThongBao.get(position).getId());
					listThongBao.remove(position);
					adapterTB.notifyDataSetChanged();
					
				}});
				b.setNegativeButton("Hủy yêu cầu", new DialogInterface.OnClickListener() {
							
				@Override

				public void onClick(DialogInterface dialog, int which)

				{
					listThongBao.remove(position);
					adapterTB.notifyDataSetChanged();
				dialog.cancel();
			

				}

				});

				b.create().show();
				
			}
		});
	}


	 public class  AsyncCheckNotify extends AsyncTask<Integer, Void, List<ItemThongBao>> {

	        @Override
	        protected List<ItemThongBao> doInBackground(Integer... params) {
	            return null;
	        }
	        @Override
	        protected void onPostExecute(List<ItemThongBao> result) {
	            super.onPostExecute(result);
	            resultNotify = result;
	            listThongBao.clear();
	            /*for(int i = 0; i< result.size(); i++){
	                if(result.get(i).getNotify().toString().equals("Hello"))
	                {
	                	ItemThongBao item = new ItemThongBao();	    	            
	    	            item.setText(result.get(i).getSender().toString()+" da gui cho ban loi moi ket ban!");
	    	            item.setId(result.get(i).getId());
	    	            listThongBao.add(item);                	
	                }
	              *//*  else
	                {
		                ItemThongBao item = new ItemThongBao();	    	            
	    	            item.setText(result.get(i).getSender().toString()+" da chap nhan loi moi ket ban!");
	    	            listThongBao.add(item);	
	                }*//*
	                adapterTB = new ThongBaoAdapter(ThongBaoActivity.this, R.layout.custom_layout_thongbao, listThongBao);
	       		 listViewThongBao.setAdapter(adapterTB);
	            }*/
	        }
	    }
	 
		public class AsyncAcceptFreind extends AsyncTask<Integer, Void, Integer> {

	        @Override
	        protected Integer doInBackground(Integer... params) {
	            return 1;
	        }

	        @Override
	        protected void onPostExecute(Integer integer) {
	            super.onPostExecute(integer);
	            if(integer > 0)
	                Toast.makeText(getApplicationContext(), "Da chap nhan loi moi ket ban!", Toast.LENGTH_SHORT).show();
	            else
	            {
	                Toast.makeText(getApplicationContext(), "Tu choi loi moi ket ban!", Toast.LENGTH_SHORT).show();
	            }
	            
	        }
	    }   
}
