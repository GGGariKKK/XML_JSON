package com.iharkusha.util.menu_mini_framework;

import java.lang.reflect.Method;

public class MenuItem {
	private Object obj;
	private String label;
	private String target;
	private boolean isExitItem;
	
	public MenuItem(String label) { this(label, null, null); } // cannot invoke this MenuItem ie. dummy item
	
	public MenuItem(String label, Object obj, String target) {
		this.label = label;
		this.obj = obj;
		this.target = target;
	}
	
	public String getLabel() { return label; }

	void invoke() {
		if (target == null) return;
		
		try {
			Method method = obj.getClass().getDeclaredMethod(target);
			method.setAccessible(true);
			method.invoke(obj);
		}
		catch (Exception ex) { ex.printStackTrace(); }
	}

	boolean isExitItem() { return isExitItem; }

	void setExitItem(boolean isExitItem) { this.isExitItem = isExitItem; }
	
	public String toString() { return getLabel(); }
}