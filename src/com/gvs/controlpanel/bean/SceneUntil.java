package com.gvs.controlpanel.bean;
/**
 * 数据库
 * 设置场景静态方法
 * @author hjy
 * 2016-6-2
 */
public class SceneUntil {
	 public static boolean  isSearch=false;//判断是否处于查询的表单的标志(flag)
	 public static boolean  isInsert=false;//判断是否处于添加的表单的标志(flag)
	 public static boolean  isUpdataOrDelete=false;//判断是否处于删除或更新的表单的标志(flag)
	 public static boolean  isDelete=false;//判断是否处于删除的表单的标志(flag)
	public static boolean isSearch() {
		return isSearch;
	}
	public static void setSearch(boolean isSearch) {
		SceneUntil.isSearch = isSearch;
	}
	public static boolean isInsert() {
		return isInsert;
	}
	public static void setInsert(boolean isInsert) {
		SceneUntil.isInsert = isInsert;
	}
	public static boolean isUpdataOrDelete() {
		return isUpdataOrDelete;
	}
	public static void setUpdataOrDelete(boolean isUpdataOrDelete) {
		SceneUntil.isUpdataOrDelete = isUpdataOrDelete;
	}
	public static boolean isDelete() {
		return isDelete;
	}
	public static void setDelete(boolean isDelete) {
		SceneUntil.isDelete = isDelete;
	}
}
