package group14.runchickenrun;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

@SuppressLint("WrongCall") public class GameLoopThread extends Thread {
    private GameView view;
    private boolean running = false;
    
    private long lastFrame; // time at last frame
    private int fps; // frames per second
    private long lastFPS; // last fps time
        
    private List<Sprite> sprites = new ArrayList<Sprite>();
    
    private SoundManager soundManager;
    
    private Chicken chicken;
    
    public GameLoopThread(GameView view) {
          //this.soundManager = sm;
    	  this.view = view;
          this.soundManager = null;
          chicken = new Chicken(view, 50, view.getHeight() / 2);
          sprites = view.createSprites();
    }

    public void setRunning(boolean run) {
          running = run;
    }

    public long getTime() {
    	return System.nanoTime() / 1000000;
    }
    
    public int getDelta() {
    	long time = getTime();
    	int delta = (int) (time - lastFrame);
    	lastFrame = time;
    	return delta;
    }
    
    public void updateFPS() {
    	if (getTime() - lastFPS > 1000) {
    		fps = 0;
    		lastFPS += 1000;
    	}
    	fps++;
    }
    
    @Override
    public void run() {
    	
    	  init();
    	  
          while (running) {
        	  	 //delta = getDelta();
                 Canvas c = null;
                 try {
                        c = view.getHolder().lockCanvas();
                        synchronized (view.getHolder()) {
                        	   update(getDelta());
                        	   if(c != null)
                        		   view.onDraw(c);
                        }
                 } finally {
                        if (c != null) {
                               view.getHolder().unlockCanvasAndPost(c);
                        }
                 }
                 
                 // fps
                 updateFPS();
          }
    }

    /**
     * method before loop starts
     */
    private void init() {
    	getDelta(); // call once before loop to initialise lastFrame
    	lastFPS = getTime(); // call before loop to initialise fps timer
    }

	/**
     * UPDATE SHIT HERE
     * 
     * @param delta time since last frame.
     */
	private void update(int delta) {
		
		
		for(Sprite sprite : sprites) {
			sprite.update(delta);
		}
		
		if(chicken.doWalk() == true) {
	       	chicken.setDx(chicken.getDx() - chicken.getWalkSpeed());
	    }
		
	}

	public List<Sprite> getSprites() {
		return sprites;
	}
	
	public Chicken getChicken() {
		return chicken;
	}
}  
