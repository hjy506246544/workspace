package com.gvs.controlpanel.bean;

public class Scene {
	private Integer sceneId;
	private String sceneName;
	private String sceneImg;
	public Integer getSceneId() {
		return sceneId;
	}
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
		sceneId = 1;
		sceneId = 2;
		sceneId = 3;
	}
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
		if(sceneId==1){
			this.sceneName = "晨起模式";
		}else
		if(sceneId==2){
			this.sceneName = "聚餐模式";
		}else
		if(sceneId==3){
			this.sceneName = "自定义模式";
		}
	}
	public String getSceneImg() {
		return sceneImg;
	}
	public void setSceneImg(String sceneImg) {
		this.sceneImg = sceneImg;
	}
}
