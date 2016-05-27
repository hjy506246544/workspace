package com.gvs.controlpanel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.gvs.controlpanel.bean.BgMusicLrc;
import com.gvs.controlpanel.bean.MusicInfo;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

public class Saomiao {
	public  ArrayList<MusicInfo> query(Context mcontext){

		ArrayList<MusicInfo> arraylist=null;
		//扫描->游标
		Cursor c=mcontext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

		if(c!=null){
			//
			arraylist=new ArrayList<MusicInfo>();

			//

			while(c.moveToNext() ){


				MusicInfo MusicInfo;
				MusicInfo=new MusicInfo();

				String music_name=c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));

				String singer=c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));

				String path=c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));

				String duration=c.getString(c.getColumnIndex(MediaStore.Audio.Media.DURATION));

				//System.out.println(""+music_name);
				//System.out.println(""+singer);
				//System.out.println(""+path);

				MusicInfo.setMusic_name(music_name);
				MusicInfo.setSinger(singer);
				MusicInfo.setPath(path);

				arraylist.add(MusicInfo);

			}


		}
		return arraylist;

	}
	public static  ArrayList<BgMusicLrc> redlrc(String path){

		ArrayList<BgMusicLrc> alist=new ArrayList<BgMusicLrc>();
		File f=new File(path.replace(".mp3", ".lrc"));

		try {
			FileInputStream fs=new FileInputStream(f);

			InputStreamReader inputstreamreader=new InputStreamReader(fs,"utf-8");

			BufferedReader br=new BufferedReader(inputstreamreader);
			String s="";

			while(null!=(s=br.readLine())){

				if(!TextUtils.isEmpty(s)){
					BgMusicLrc lrcMusicInfo=new BgMusicLrc();

					String lylrc=s.replace("[", "");

					String data_ly[]=lylrc.split("]");
					if(data_ly.length>1){

						String time=data_ly[0];
						lrcMusicInfo.setTime(lrcdata(time));

						String lrc=data_ly[1];
						lrcMusicInfo.setIrc(lrc);
						//System.out.println("..............."+lrcMusicInfo.getIrc());


						alist.add(lrcMusicInfo);
						//System.out.println(".................."+alist.get(0).getIrc());

					}
				}

			}



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alist;

	}

	public static int lrcdata(String time){
		time=time.replace(":", "#");
		time=time.replace(".", "#");

		String mTime[]=time.split("#");

		int mtime=Integer.parseInt(mTime[0]);
		int stime=Integer.parseInt(mTime[1]);
		int mitime=Integer.parseInt(mTime[2]);

		int ctime=(mtime*60+stime)*1000+mitime*10;

		return ctime;


	}


}
