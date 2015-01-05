package com.ophion.blop;

import com.ophion.framework.Game;
import com.ophion.framework.Graphics;
import com.ophion.framework.Graphics.ImageFormat;
import com.ophion.framework.Screen;

public class LoadingScreen extends Screen{
	public LoadingScreen(Game game){
		super(game);
	}
	
	@Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.jpeg", ImageFormat.RGB565);
        Assets.click = game.getAudio().createSound("explosion.ogg");
        Assets.character = g.newImage("blop.png", ImageFormat.RGB565);
        Assets.background1 = g.newImage("bg1.jpg", ImageFormat.RGB565);
        Assets.background2 = g.newImage("bg2.jpg", ImageFormat.RGB565);
        
        game.setScreen(new MainMenuScreen(game));
    }


    @Override
    public void paint(float deltaTime) {


    }


    @Override
    public void pause() {


    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }
}
