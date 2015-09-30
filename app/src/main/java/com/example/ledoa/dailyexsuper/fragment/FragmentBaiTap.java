package com.example.ledoa.dailyexsuper.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.TranningPlanAdapter;

public class FragmentBaiTap extends Fragment {

	View view;
	String argMonTap[] = {"Dành cho người mới bắt đầu", "Chương trình tăng sức bền", "Chương trình giảm cân"};
	int argImg[] = {R.drawable.training_walk,R.drawable.training_run,R.drawable.training_cycle};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_training, container, false);
		Context context = getContext();

		ListView listView = (ListView) view.findViewById(R.id.listView_tranning);

		TranningPlanAdapter adapter = new TranningPlanAdapter(context, R.layout.item_training_plan, argMonTap, argImg);
		listView.setAdapter(adapter);

		return view;
	}

}


	
