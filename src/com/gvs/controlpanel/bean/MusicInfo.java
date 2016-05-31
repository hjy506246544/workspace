package com.gvs.controlpanel.bean;

import java.io.Serializable;

public class MusicInfo implements Serializable{
	private String id;
	public String singer;
	public String path;
	public String music_name;
	private int duration;
	public boolean isSelect_box=false;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isSelect_box() {
		return isSelect_box;
	}
	public void setSelect_box(boolean isSelect_box) {
		this.isSelect_box = isSelect_box;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
