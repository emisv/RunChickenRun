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
    private Sprite sprite;
    private MainActivity mainActivity;
    private SoundManager soundManager;
    
    public GameView(Context context) {
          super(context);
          //soundManager = new SoundManager((MainActivity) context);
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
          Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.chicken1);
          sprite = new Sprite(this, bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	// background
        canvas.drawColor(Color.BLACK);
        
        // chicken
        for(int i = 0; i < gameLoopThread.getSprites().size(); i++) {
        	gameLoopThread.getSprites().get(i).draw(canvas);
        }
    }

	public List<Sprite> createSprites() {
		List<Sprite> sprites = new ArrayList<Sprite>();
		sprites.add(createChicken(R.drawable.chicken1));
		return sprites;
	}
	
	private Sprite createChicken(int resource) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Chicken(50, 50, this, bmp);
	}
	
	private Sprite createSprite(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp);
	}
    
}
    
          