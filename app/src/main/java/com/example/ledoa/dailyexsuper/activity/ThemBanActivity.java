package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.ThemBanAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemThemBan;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ThemBanActivity extends Activity {
	
		String itemText[] = {"AnhtuanUit",
				"LeLoc", "Ngoc Man"};
		ArrayList<ItemThemBan> listThongBao;
		ListView listViewThongBao;
		ThemBanAdapter adapterTB;
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_them_ban);
			
			getActionBar().setBackgroundDrawable(getResources().getDrawable(
			        R.drawable.theme));
			
			listThongBao = new ArrayList<ItemThemBan>();
			listViewThongBao= (ListView)findViewById(R.id.listViewThemBan);
			
			  this.addControls();
			this.addEvents();
			 for(int i=0; i< itemText.length;i++){
				 ItemThemBan item = new ItemThemBan();
		            
		            item.setText(itemText[i]);
		            item.setTextButton("Hủy yêu cầu");
		            
		            listThongBao.add(item);
		        }	 
			 adapterTB = new ThemBanAdapter(ThemBanActivity.this, R.layout.custom_layout_themban, listThongBao);
			 listViewThongBao.setAdapter(adapterTB);		
			 AsyncLoadAccounts asyncLoadAccounts = new AsyncLoadAccounts();
			 asyncLoadAccounts.execute();
		}
		
		 public class  AsyncLoadAccounts extends AsyncTask<Void , Void, List<ItemThemBan>> {
		        @Override
		        protected List<ItemThemBan> doInBackground(Void... params) {
		            return null;
		        }
		        @Override
		        protected void onPostExecute(List<ItemThemBan> players) {
		            super.onPostExecute(players);
		            listThongBao.clear();
		            Bitmap s = BitmapFactory.decodeResource(getApplicationContext().getResources(),
							R.drawable.avatar);
		          /*  for(int i = 0; i< players.size(); i++){

		               // if(players.get(i).getId() != MyAccountId ) {
		                	ItemThemBan pl = new ItemThemBan();
		                    pl = players.get(i);
		                
		                    	pl.setAvatar(s);
		                    listThongBao.add(pl);
		                //}
		            }

		            adapterTB = new ThemBanAdapter(ThemBanActivity.this, R.layout.custom_layout_themban, listThongBao);
					listViewThongBao.setAdapter(adapterTB);*/
		        }
		      
		    }

		 ImageButton btnCapture, btnUpload;
			ImageView imageView;
			private Uri fileUri;
			String picturePath;
			Uri selectedUriImage;
			Bitmap selectedBitmap;
			String ba1;

	

			public void addControls() {
				btnCapture = (ImageButton) findViewById(R.id.btnCapture1);
				btnUpload = (ImageButton) findViewById(R.id.btnUpload1);
				imageView = (ImageView) findViewById(R.id.Imageprev1);
				btnUpload.setEnabled(false);
			}

			public void addEvents() {
				btnCapture.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						capturePicture();
					}
				});
				btnUpload.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						uploadPictureToServer();
						for (ItemThemBan item : listThongBao) {
							if(item.getFriendAccountId() == 6)
							{
								item.setAvatar(uploadPictureToServer());
							}
						}
						adapterTB.notifyDataSetChanged();
					}
				});
			}

			/**
			 * hàm xử lý lấy thumbnail để tối ưu bộ nhớ
			 * 
			 * @param pathHinh
			 * @return
			 */
			public Bitmap getThumbnail(String pathHinh) {
				BitmapFactory.Options bounds = new BitmapFactory.Options();
				bounds.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(pathHinh, bounds);
				if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
					return null;
				int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
						: bounds.outWidth;
				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inSampleSize = originalSize / 500;
				return BitmapFactory.decodeFile(pathHinh, opts);
			}

			/**
			 * Hàm xử lys lấy encode hình để gửi lên Server
			 */
			private Bitmap uploadPictureToServer() {
				//Log.e("path", "----------------" + picturePath);
				ByteArrayOutputStream bao = new ByteArrayOutputStream();
				selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
				byte[] ba = bao.toByteArray();
				ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
		        
				//Log.e("base64", "-----" + ba1);

				// Upload hình lên server
		/*		UploadToServerTask uploadToServer = new UploadToServerTask(
						ThemBanActivity.this, ba1);
				uploadToServer.execute();*/
				
				
				//imageView.setImageBitmap(decodeBase64(ba1));
				return decodeBase64(ba1);
			}

			public static Bitmap decodeBase64(String input)
			{
			    byte[] decodedByte = Base64.decode(input, 0);
			    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
			}
			
			private void capturePicture() {
				// Kiểm tra Camera trong thiết bị
				if (getApplicationContext().getPackageManager().hasSystemFeature(
						PackageManager.FEATURE_CAMERA)) {
					// Mở camera mặc định
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

					// Tiến hành gọi Capture Image intent
					startActivityForResult(intent, 100);

				} else {
					Toast.makeText(getApplication(), "Camera không được hỗ trợ",
							Toast.LENGTH_LONG).show();
				}
			}

			/**
			 * Lấy đường dẫn file hình theo uri hình
			 * 
			 * @param uriImage
			 * @return
			 */
			public String getPicturePath(Uri uriImage) {
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(uriImage, filePathColumn,
						null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String path = cursor.getString(columnIndex);
				cursor.close();
				return path;
			}

			protected void onActivityResult(int requestCode, int resultCode, Intent data) {
				if ((requestCode == 100 || requestCode == 200)
						&& resultCode == RESULT_OK) {
					// Lấy URI hình kết quả trả về
					selectedUriImage = data.getData();
					// lấy đường dẫn hình
					picturePath = getPicturePath(selectedUriImage);
					// lấy thumbnail để tối ưu bộ nhớ
					selectedBitmap = getThumbnail(picturePath);
					//Xoay hinh theo chieu chup
					selectedBitmap = rotateImageIfRequired(selectedBitmap,
							selectedUriImage);
					//View hinh
					
					btnUpload.setEnabled(true);
				}
			}

			/**
			 * Hàm hiển thị Camera folder và cho phép hiển thị hình người sử dụng chọn
			 * lên giao diện, hình này sẽ được gửi lên Server nếu muốn
			 */
			public void processChonHinh() {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(intent, 200);
			}

			/**
			 * Quay lại hình nếu chưa đúng
			 * 
			 * @param img
			 * @param selectedImage
			 * @return
			 */
			private Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) {

				// Detect rotation
				int rotation = getRotation();
				if (rotation != 0) {
					Matrix matrix = new Matrix();
					matrix.postRotate(rotation);
					Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(),
							img.getHeight(), matrix, true);
					img.recycle();
					return rotatedImg;
				} else {
					return img;
				}
			}

			/**
			 * Lấy Rotation của hình
			 * 
			 * @return
			 */
			private int getRotation() {
				String[] filePathColumn = { MediaStore.Images.Media.ORIENTATION };
				Cursor cursor = getContentResolver().query(selectedUriImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int rotation = 0;
				rotation = cursor.getInt(0);
				cursor.close();
				return rotation;
			}




}
