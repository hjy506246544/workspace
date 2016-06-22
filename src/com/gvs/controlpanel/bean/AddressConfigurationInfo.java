package com.gvs.controlpanel.bean;

import java.io.Serializable;

public class AddressConfigurationInfo implements Serializable{
	private String id;
	public String configurationName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConfigurationName() {
		return configurationName;
	}
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
}
