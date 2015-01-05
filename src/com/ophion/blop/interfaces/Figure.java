package com.ophion.blop.interfaces;

import android.graphics.Point;

public interface Figure {
	
	public void setPosition(Point pos);
	
	public Point getPosition();
	
	public String getName();
	
	public void setName(String name);
}
