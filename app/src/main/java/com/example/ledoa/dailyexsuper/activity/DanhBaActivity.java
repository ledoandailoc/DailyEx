package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhBaAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemDanhBa;

import java.util.ArrayList;

public class DanhBaActivity extends Activity {

	String itemText[] = {"AnhtuanUit", "LeLoc", "Ngoc Man"};
	ArrayList<ItemDanhBa> listDanhBa;
	ListView listViewDanhBa;
	DanhBaAdapter adapterDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_ba);

		listDanhBa = new ArrayList<ItemDanhBa>();
		listViewDanhBa = (ListView)findViewById(R.id.listViewDanhBa);
		
		
		 for(int i=0; i< itemText.length;i++){
	            ItemDanhBa item = new ItemDanhBa();
	            
	            item.setText(itemText[i]);
	            listDanhBa.add(item);
	        }	 
		 adapterDB = new DanhBaAdapter(DanhBaActivity.this, R.layout.custom_layout_danhba, listDanhBa);
		 listViewDanhBa.setAdapter(adapterDB);
		 
		 AsyncGetContacts asyncGetContacts = new AsyncGetContacts();
		 asyncGetContacts.execute(10,1);
	}
	
	public class  AsyncGetContacts extends AsyncTask<Integer , Void, ArrayList<ItemDanhBa>> {
        @Override
        protected ArrayList<ItemDanhBa> doInBackground(Integer... params) {
      
            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<ItemDanhBa> players) {
            super.onPostExecute(players);
           /* listDanhBa =  players;
            adapterDB = new DanhBaAdapter(DanhBaActivity.this, R.layout.custom_layout_danhba, listDanhBa);
            listViewDanhBa.setAdapter(adapterDB);*/
           
        }
    }
}
