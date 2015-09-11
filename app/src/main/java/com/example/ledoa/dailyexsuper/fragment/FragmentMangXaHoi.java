package com.example.ledoa.dailyexsuper.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.MxhAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemMxh;

import java.util.ArrayList;
import java.util.List;


public class FragmentMangXaHoi extends Fragment {
	
	String itemTextTB[] = {"AnhtuanUit","LeLoc","Long Pham Hoang"};
	String itemTextUD[] = {"AnhtuanUit da thay doi hinh dai dien","Thuc ra minh la BD","Haha Loc BD"};
	View view,view1;
	List<ItemMxh> ListMxh;
    ListView listViewMxh;
    MxhAdapter adapterMxh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ListMxh = new ArrayList<>();
		view = inflater.inflate(R.layout.layout_fragment5, container,false);
		
		listViewMxh = (ListView)view.findViewById(R.id.listViewMxh);
		View header = getLayoutInflater(savedInstanceState).inflate(R.layout.header, listViewMxh, false);

	        listViewMxh.addHeaderView(header, null, false);
		
		
		adapterMxh = new MxhAdapter(getActivity(), R.layout.custom_layout_mxh, ListMxh);
		ItemMxh s = new ItemMxh();
		
		for(int i=0; i< itemTextTB.length;i++){
            ItemMxh item = new ItemMxh();
            item.setTkhoan(itemTextTB[i]);
            item.setStatus(itemTextUD[i]);
            ListMxh.add(item);
        }		 	
		
		
		listViewMxh.setAdapter(adapterMxh);
		return view;
	}
	

}

	
