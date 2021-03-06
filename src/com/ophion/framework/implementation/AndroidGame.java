package com.ophion.framework.implementation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.ophion.framework.*;

public abstract class AndroidGame extends Activity implements Game{
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//Sets width and height off the app and scales it to screen.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		int frameBufferWidth = 1280;
		int frameBufferHeight = 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, 
				frameBufferHeight, Config.RGB_565);
		
		//New approach to getting screen width/height
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		float scaleX = (float) frameBufferWidth / displayMetrics.widthPixels;
		float scaleY = (float) frameBufferHeight / displayMetrics.widthPixels;
		
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getInitScreen();
		setContentView(renderView);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	@Override
	public void onResume(){
		super.onResume();
        screen.resume();
        renderView.resume();
	}
	
	@Override
	public void onPause(){
		super.onPause();
        renderView.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
	}
	
	@Override
	public Input getInput(){
		return input;
	}
	
	@Override
	public FileIO getFileIO(){
		return fileIO;
	}
	
	@Override
	public Graphics getGraphics(){
		return graphics;
	}
	
	@Override
	public Audio getAudio(){
		return audio;
	}
	
	@Override
	public void setScreen(Screen screen){
		if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
	}
	
	@Override
	public Screen getCurrentScreen(){
		return screen;
	}
}
