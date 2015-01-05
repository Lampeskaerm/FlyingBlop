package com.ophion.blop;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.shapes.OvalShape;

public class Blop{
	Point pos = new Point();
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	
	private final Point startPos = new Point(200,600);
	private String name;
	private boolean isJumping = false;
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private boolean isMovingUp = false;
	private boolean isMovingDown = false;
	
	private int speedX = 0;
	private int speedY = 0;
	private int maxX = 1000;
	private int minX = 0;
	private int minY = 0;
	private int maxY = 600;
	
	public void update(){
		if(pos.x <= minX && speedX < 0 || pos.x >= maxX && speedX > 0) { 
			speedX = 0;
		} else {
			pos.x += speedX;
		}
		if(pos.y <= minY && speedY < 0){
			pos.y = minY;
			speedY = 0;
		} else if (pos.y >= maxY && speedY > 0){
			pos.y = maxY;
			speedY = 0;
		} else {
			pos.y += speedY;
		}
	}
	
	public void moveRight(){
		speedX = MOVESPEED;
		setMovingRight(true);
	}
	
	public void moveLeft(){
		speedX = (-MOVESPEED);
		setMovingLeft(true);
	}
	
	public void moveUp(){
		speedY = (-MOVESPEED);
		setMovingUp(true);
	}
	
	public void moveDown(){
		speedY = (MOVESPEED);
		setMovingDown(true);
	}
	
	public void stopLeftRight() {
		setMovingLeft(false);
		setMovingRight(false);
		speedX = 0;
	}
	
	public void stopUpDown() {
		setMovingUp(false);
		setMovingDown(false);
		speedY = 0;
	}
	
	public void stopAll(){
		stopLeftRight();
		stopUpDown();
	}
	
	public void jump(){
		if(!isJumping){
			setSpeedY(JUMPSPEED);
			setJumping(true);
		}
	}
	
	public Point getPos(){
		return pos;
	}
	
	public void setPos(Point pos){
		this.pos = pos;
	}
	
	public Point getStartPos() {
		return startPos;
	}
	
	public void setInitPos(){
		this.pos = startPos;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public boolean isMovingUp() {
		return isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	public boolean isMovingDown() {
		return isMovingDown;
	}

	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public int getSpeedX(){
		return speedX;
	}
	
	public void setSpeedX(int speedX){
		this.speedX = speedX;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
