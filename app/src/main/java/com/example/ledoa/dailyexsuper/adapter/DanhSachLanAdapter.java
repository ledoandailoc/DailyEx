package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.LanTap;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay;

import java.util.List;

public class DanhSachLanAdapter extends ArrayAdapter<LanTap> {
	Context context;
	int resource;
	List<LanTap> list;
	
	DatabaseHandle databaseHandle;
	
	public DanhSachLanAdapter(Context context, int resource, List<LanTap> objects) {
		super(context, resource, objects);

		this.context = context;
		this.resource = resource;
		this.list = objects;
	}
	
	@Override
	public View getView(int vitri, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);
		
		TextView textGio = (TextView) view.findViewById(R.id.textView_ngay);
		TextView textTenTongThoiGian = (TextView) view.findViewById(R.id.textView_tongthoigian);
		TextView textView1 = (TextView) view.findViewById(R.id.textView1);
		TextView textView2 = (TextView) view.findViewById(R.id.textView2);
		TextView textView3 = (TextView) view.findViewById(R.id.textView3);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView_lichtapdetail);
		
		LanTap lanTap = new LanTap();
		lanTap = list.get(vitri);

		if (lanTap.getTenMonTap().equals("DB")) {
			imageView.setImageResource(R.drawable.walk);
			textView1.setText(String.valueOf("Số động tác: " + lanTap.getSoDongTac()));
			textView2.setText(String.valueOf("Vận tốc cao nhất: ") + lanTap.getVanTocCaoNhat() + "km/giờ");
			textView3.setText(String.valueOf("Tốc độ trung bình: ") + lanTap.getTocDoTrungBinh() + "km/giờ");
		}
		else if(lanTap.getTenMonTap().equals("CB")){
			imageView.setImageResource(R.drawable.running);
			textView1.setText(String.valueOf("Vận tốc cao nhất: ") + lanTap.getVanTocCaoNhat() + "km/giờ");
			textView2.setText(String.valueOf("Tốc độ trung bình: ") + lanTap.getTocDoTrungBinh() + "km/giờ");
			textView3.setText("");
		}
		else if(lanTap.getTenMonTap().equals("DX")){
			imageView.setImageResource(R.drawable.cycling);
			textView1.setText(String.valueOf("Vận tốc cao nhất: ") + lanTap.getVanTocCaoNhat() + "km/giờ");
			textView2.setText(String.valueOf("Tốc độ trung bình: ") + lanTap.getTocDoTrungBinh() + "km/giờ");
			textView3.setText("");
		}
		else if(lanTap.getTenMonTap().equals("HD")){
			imageView.setImageResource(R.drawable.hitdat);
			textView1.setText(String.valueOf("Số động tác: " + lanTap.getSoDongTac()));
			textView2.setText("");
			textView3.setText("");
		}

		String time = lanTap.getNgay().substring(11,16);
		/*String gioPhut = time.substring(6,8);*/
		textGio.setText(String.valueOf(time));
		textTenTongThoiGian.setText("Tổng thời gian: " + String.valueOf(DoiGioPhutGiay.GiaySangGio(Long.parseLong(String.valueOf(lanTap.getTongThoiGian())))));

		
		
		return view;
	}

}
