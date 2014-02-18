package group14.runchickenrun;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

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
        
        // hej
        if(backgroundSprite != null && backgroundSprite != null) {
        	backgroundSprite.draw(canvas);
	        
	        if(backgroundSprite.getX() <= 935) backgroundSprite.setX(934);
	        backgroundSprite2.draw(canvas);
	        if(backgroundSprite2.getX() <= 935) backgroundSprite2.setX(934);
	
	        if(gameLoopThread.getChicken().doWalk() == true) {
	        	backgroundSprite.setX(backgroundSprite.getX() - gameLoopThread.getChicken().getWalkSpeed());
	        	backgroundSprite2.setX(backgroundSprite2.getX() - gameLoopThread.getChicken().getWalkSpeed());
	        }
        }
        gameLoopThread.getChicken().draw(canvas);
        
        for(int i = 0; i < gameLoopThread.getSprites().size(); i++) {
        	gameLoopThread.getSprites().get(i).draw(canvas);
        }
    }

	public List<Sprite> createSprites() {
		List<Sprite> sprites = new ArrayList<Sprite>();
		//sprites.add(createChicken(R.drawable.chicken1));
		
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.scrollingbackground);
		// skit
		bmp.getHeight();
		gameLoopThread.getDelta();
		gameLoopThread.getChicken();
		this.getBottom();
		// ez
        backgroundSprite = new BackgroundSprite(this, bmp, gameLoopThread.getChicken(), 0);
		
        Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.scrollingbackground);
        backgroundSprite2 = new BackgroundSprite(this, bmp2, gameLoopThread.getChicken(), 934);
        
        for(int i = 0; i < 200; i++) {
        	sprites.add(createGrassBlock(R.drawable.grassblock, 0 + i*32, (int) (this.getHeight() * 0.8), gameLoopThread.getChicken()));
        }
        
		return sprites;
	}
	
	private Sprite createGrassBlock(int resource, int x, int y, Chicken chicken) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new GrassBlock(this, bmp, chicken, x, y);
	}
	
	
	private Sprite createSprite(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp);
	}
    
}
    
          