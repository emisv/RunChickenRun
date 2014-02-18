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
    
    private Sprite backgroundSprite;
    private Sprite backgroundSprite2;

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
        
        backgroundSprite.draw(canvas);
        backgroundSprite2.draw(canvas);
        
        // chicken
        for(int i = 0; i < gameLoopThread.getSprites().size(); i++) {
        	gameLoopThread.getSprites().get(i).draw(canvas);
        }
    }

	public List<Sprite> createSprites() {
		List<Sprite> sprites = new ArrayList<Sprite>();
		sprites.add(createChicken(R.drawable.chicken1));
		
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.scrollingbackground);
        backgroundSprite = new BackgroundSprite(this, bmp, (Chicken) sprites.get(0), 0);
		
        Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.scrollingbackground);
        backgroundSprite2 = new BackgroundSprite(this, bmp, (Chicken) sprites.get(0), 934);
        
        for(int i = 0; i < 200; i++) {
        	sprites.add(createGrassBlock(R.drawable.grassblock, 0 + i*32, 250 - 32, (Chicken) sprites.get(0)));
        }
        
		return sprites;
	}
	
	private Sprite createGrassBlock(int resource, int x, int y, Chicken chicken) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new GrassBlock(this, bmp, chicken, x, y);
	}
	
	private Sprite createChicken(int resource) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Chicken(this, bmp, 50, 180);
	}
	
	private Sprite createSprite(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp);
	}
    
}
    
          