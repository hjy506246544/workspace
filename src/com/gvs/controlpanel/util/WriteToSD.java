package com.gvs.controlpanel.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
/**
 * 将assets文件夹下的数据库写入SD卡中
 * @author hjy
 * 2016-6-3
 */
public class WriteToSD {
	 static String filePath = android.os.Environment.getExternalStorageDirectory()+"/imgs";
	 static String filePath2 = android.os.Environment.getExternalStorageDirectory()+"/imgs2/";

	 /**
	  * 获取assets文件夹中的数据并写入SD卡
	  * 获取单个文件夹中的数据
	  * @param context
	  */
	 public static void write(Context context){
		 InputStream inputStream;
		 try {
			  inputStream = context.getResources().getAssets().open("img/main_light.png");
			  File file = new File(filePath);
			  if(!file.exists()){
			  file.mkdirs();
			  }
			  FileOutputStream fileOutputStream = new FileOutputStream(filePath + "/main_light.png");
			  byte[] buffer = new byte[512];
			  int count = 0;
			  while((count = inputStream.read(buffer)) > 0){
			  fileOutputStream.write(buffer, 0 ,count);
			  }
			  fileOutputStream.flush();
			  fileOutputStream.close();
			  inputStream.close();
			  System.out.println("success");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	}

	/**
	  * 读取assets目录下的所有图片
	  * 获取整个文件夹的数据
	  * @param context
	  */
	public static void getAsset(Context context){
		 List<Map<String, Object>> cateList = new ArrayList<Map<String, Object>>();
		 String[] list_image = null;
		 InputStream open = null;
		 try {
			 //得到assets/img/目录下的所有文件的文件名，以便后面打开操作时使用
		     list_image = context.getAssets().list("img");
		 } catch (IOException e1) {
		     e1.printStackTrace();
		 }
		 for(int i=0;i<list_image.length;++i)
		 {
		     try {
				  String temp = "img/"+list_image[i];
				  open = context.getAssets().open(temp);
				  Bitmap bitmap = BitmapFactory.decodeStream(open);
				  Map<String, Object> map = new HashMap<String, Object>();
				  map.put("name", list_image[i]);
				  map.put("iv", bitmap);
				  map.put("cate_id",i);
				  File file = new File(filePath);
				  if(!file.exists()){
					  file.mkdirs();
				  }
				  FileOutputStream fileOutputStream = new FileOutputStream(filePath + list_image[i]);
				  byte[] buffer = new byte[512];
				  int count = 0;
				  while((count = open.read(buffer)) > 0){
					  fileOutputStream.write(buffer, 0 ,count);
				  }
				  fileOutputStream.flush();
				  fileOutputStream.close();
				  open.close();
				  System.out.println("success2");
				  cateList.add(map);
		     } catch (IOException e) {
		    	 e.printStackTrace();
		     } finally {
		    	 if (open != null) {
				     try {
				       open.close();
				     } catch (IOException e) {
				       e.printStackTrace();
				     }
		    	 }
		     }
		 }
	 }
}
