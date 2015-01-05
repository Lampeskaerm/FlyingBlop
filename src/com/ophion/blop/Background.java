package com.ophion.blop;

public class Background {
	int bgX, bgY, speedX, bgWidth, bgHeight;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = 0;
		bgWidth = 2000;
		bgHeight = 800;
	}
	
	public void update(){
		bgX -= speedX;
		
		if(bgX <= -(bgWidth)){
			bgX += bgWidth*2;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getBgWidth() {
		return bgWidth;
	}

	public void setBgWidth(int bgWidth) {
		this.bgWidth = bgWidth;
	}

	public int getBgHeight() {
		return bgHeight;
	}

	public void setBgHeight(int bgHeight) {
		this.bgHeight = bgHeight;
	}
}
