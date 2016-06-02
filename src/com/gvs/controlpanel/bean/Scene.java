package com.gvs.controlpanel.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Scene  implements Parcelable{
	private String sceneId;
	private String sceneName;
	private String sceneImg;
	private long refreshTime;
	private int isLocation;
	public Scene(String sceneId, String sceneName, String sceneImg,
			long refreshTime, int isLocation) {
		super();
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.sceneImg = sceneImg;
		this.refreshTime = refreshTime;
		this.isLocation = isLocation;
	}

	public Scene() {
		super();
	}
	public long getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(long refreshTime) {
		this.refreshTime = refreshTime;
	}
	public int getIsLocation() {
		return isLocation;
	}
	public void setIsLocation(int isLocation) {
		this.isLocation = isLocation;
	}
	public String getSceneId() {
		return sceneId;
	}
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	public String getSceneImg() {
		return sceneImg;
	}
	public void setSceneImg(String sceneImg) {
		this.sceneImg = sceneImg;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<Scene> CREATOR = new Creator<Scene>() {
		@Override
		public Scene[] newArray(int size) {
			return new Scene[size];
		}
		@Override
		public Scene createFromParcel(Parcel source) {
			Scene scene = new Scene();
			scene.sceneId = source.readString();
			scene.sceneImg = source.readString();
			scene.sceneName = source.readString();
			scene.refreshTime = source.readLong();
			scene.isLocation = source.readInt();
			return scene;
		}

	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(sceneImg);
		dest.writeString(sceneName);
		dest.writeString(sceneId);
		dest.writeLong(isLocation);
		dest.writeLong(refreshTime);
	}
}
