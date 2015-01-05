package com.ophion.blop;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.ophion.framework.Game;
import com.ophion.framework.Graphics;
import com.ophion.framework.Image;
import com.ophion.framework.Screen;
import com.ophion.framework.Input.TouchEvent;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

	public static Image tileCenter, tileFloor, tileCeiling, tileLeft, tileRight;

    GameState state = GameState.Ready;

    // Variable Setup
    // You would create game objects here.

    int livesLeft = 1, lastX, lastY;
    boolean isTouchDown;
    Paint paint;
    Blop blop;
    
    private static Background bg1;
	private static Background bg2;
	
	private RectF rectF = new RectF(25,25,150,150);	

    public GameScreen(Game game) {
        super(game);
        
        // Initialize game objects here
        bg1 = new Background(0,0);
        bg2 = new Background(bg1.getBgWidth(),0);
        setBackgroundSpeed(5);
        blop = new Blop();
        blop.setInitPos();
        
        
        bg1.setSpeedX(5);
        bg2.setSpeedX(5);
        
        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

    }
    
    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        
        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins. 
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!
        
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        isTouchDown = game.getInput().isTouchDown(0);
        //This is identical to the update() method from our Unit 2/3 game.
        
        // 1. All touch input is handled here:
        int len = touchEvents.size();
        if(len == 0 && isTouchDown){
        	Log.d("TouchEvent", "X: " + lastX + ", Y: " + lastY);
        	if (lastX < 400) {
                // Move left.
            	blop.moveLeft();
            }

            else if (lastX > 800) {
                // Move right.
            	blop.moveRight();
            } 
            
            else {
            	// Keep still
            	blop.stopLeftRight();
            }
        	
        	if(lastY < 100) {
            	//Move up
            	Log.d("Movement", "Moving Up!");
            	blop.moveUp();
            } 
            
            else if (lastY > 300) {
            	//Move down
            	Log.d("Movement", "Moving Down!");
            	blop.moveDown();
            } 
            
            else {
            	blop.stopUpDown();
            }
        }
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            lastX = event.x;
            lastY = event.y;
        	Log.d("TouchEvent", "X: " + lastX + ", Y: " + lastY);
//          Log.d("Touch event type", "" + event.type);
            if (event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED || event.type == TouchEvent.TOUCH_HOLD) {
            	
                if (event.x < 400) {
                    // Move left.
                	blop.moveLeft();
                }

                else if (event.x > 800) {
                    // Move right.
                	blop.moveRight();
                } 
                
                else {
                	// Keep still
                	blop.stopLeftRight();
                }
                
                if(event.y < 100) {
                	//Move up
                	Log.d("Movement", "Moving Up!");
                	blop.moveUp();
                } 
                
                else if (event.y > 300) {
                	//Move down
                	Log.d("Movement", "Moving Down!");
                	blop.moveDown();
                } else {
                	blop.stopUpDown();
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
            	
            	blop.stopAll();
                
            }
            
        }
        
        // 2. Check miscellaneous events like death:
        
        if (livesLeft == 0) {
            state = GameState.GameOver;
        }
        
        
        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
        blop.update();
        bg1.update();
        bg2.update();
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 300 && event.x < 980 && event.y > 100
                        && event.y < 500) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        // First draw the game elements.

        
        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);
        g.drawImage(Assets.background1, bg1.getBgX(),bg1.getBgY());
        g.drawImage(Assets.background2, bg2.getBgX(), bg2.getBgY());
        //g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawRoundRect(rectF, 75, 75, Color.WHITE);
        g.drawImage(Assets.character, blop.getPos().x, blop.getPos().y);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        blop = null;
        bg1 = null;
        bg2 = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap each side of the screen to move in that direction.",
                640, 300, paint);

    }

    private void drawRunningUI() {
        //Graphics g = game.getGraphics();
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 640, 300, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

	public static Background getBg1() {
		return bg1;
	}

	public static void setBg1(Background bg1) {
		GameScreen.bg1 = bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static void setBg2(Background bg2) {
		GameScreen.bg2 = bg2;
	}

	public Blop getBlop() {
		return blop;
	}

	public void setBlop(Blop blop) {
		this.blop = blop;
	}

	public static void setBackgroundSpeed(int speedX) {
		bg1.setSpeedX(speedX);
		bg2.setSpeedX(speedX);
	}
}