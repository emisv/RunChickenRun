package group14.runchickenrun;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {
	
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    
    private BackgroundSprite backgroundSprite;
    private BackgroundSprite backgroundSprite2;

    private MainActivity mainActivity;
    private SoundManager soundManager;
    
    public GameView(Context context) {
          super(context);
          //soundManager = sm;
          gameLoopThread = new GameLoopThread(this);
          holder = getHolder();
          this.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int x = (int) event.getRawX();
				int y = (int) event.getRawY();
				
				if(x > v.getWidth() / 2 && gameLoopThread.getChicken().getShootTimer() == Chicken.SHOOT_INTERVAL) {
					gameLoopThread.shoot(x, y);
				}
				if(x < v.getWidth() / 2 && gameLoopThread.getChicken().getCanJump()) {
					gameLoopThread.getChicken().jump();
				}
				
				return true;
			}
        	  
          });
          
          holder.addCallback(new SurfaceHolder.Callback() {

                 @Override
                 public void surfaceDestroyed(SurfaceHolder holder) {
                        boolean retry = true;
                        gameLoopThread.setRunning(false);
                        while (retry) {
                               try {
                                     gameLoopThread.join();
                                     retry = false;
                               } catch (InterruptedException e) {
                               }
                        }
                 }

                 @Override
                 public void surfaceCreated(SurfaceHolder holder) {
                        gameLoopThread.setRunning(true);
                        gameLoopThread.start();
                 }

                 @Override
                 public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {
                 }
          });
          
    }
    
	@Override
    protected void onDraw(Canvas canvas) {
    	// background
        canvas.drawColor(Color.BLACK);
        
        backgroundSprite.draw(canvas);
	        
	    if(backgroundSprite.getX() <= -935) backgroundSprite.setX(934);
	    backgroundSprite2.draw(canvas);
	    if(backgroundSprite2.getX() <= -935) backgroundSprite2.setX(934);
	
	    if(gameLoopThread.getChicken().getDoWalk() == true) {
	     	backgroundSprite.setX(backgroundSprite.getX() - gameLoopThread.getChicken().getWalkSpeed());
	       	backgroundSprite2.setX(backgroundSprite2.getX() - gameLoopThread.getChicken().getWalkSpeed());
	    }
        
	    // draw blocks
	    for(Block block : gameLoopThread.getBlocks()) {
	    	block.draw(canvas);
	    }
	    
	    // draw enemies
	    for(Enemy enemy : gameLoopThread.getEnemies()) {
	    	enemy.draw(canvas);
	    }
	    
	    // draw bullets
	    for(Bullet bullet : gameLoopThread.getBullets()) {
	    	bullet.draw(canvas);
	    }
	    
	    
	    // draw chicken
        gameLoopThread.getChicken().draw(canvas);
        
    }

	public void createSprites() {	
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.scrollingbackground);
        backgroundSprite = new BackgroundSprite(this, bmp, gameLoopThread.getChicken(), 0);
		
        Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.scrollingbackground);
        backgroundSprite2 = new BackgroundSprite(this, bmp2, gameLoopThread.getChicken(), 934);
	}
    
}          