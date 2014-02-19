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
        
    private List<Block> blocks = new ArrayList<Block>();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Bullet> bullets = new ArrayList<Bullet>();
    
    private SoundManager soundManager;
    
    private Chicken chicken;
    
    public GameLoopThread(GameView view) {
          //this.soundManager = sm;
    	  this.view = view;
          this.soundManager = null;
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
    	chicken = new Chicken(view, 50, (int) (view.getHeight() * 0.8));
        view.createSprites();
        createMap();
    }

    /**
     * map creation
     */
	private void createMap() {
		for(int i = 0; i < 200; i++) {
        	blocks.add(new GrassBlock(this.view, this.chicken, 0 + i*32, (int) (view.getHeight() * 0.8)));
        	if((int) (Math.random() * 10) == 0) {
        		enemies.add(new Fox(this.view, this.chicken, i * 32, (int) (view.getHeight() * 0.8) - 58));
        	}
        }
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public List<Bullet> getBullets() {
		return bullets;
	}
	
	/**
     * UPDATE SHIT HERE
     * 
     * @param delta time since last frame.
     */
	private void update(int delta) {
		
		for(Enemy enemy : enemies) {
			enemy.update(delta);
		}
		
		for(Bullet bullet : bullets) {
			bullet.update(delta);
		}
		
		// if jumpkey pressed && player.canJump == true { player.jump() }
		
		chicken.update(delta);
		
	}
	
	public Chicken getChicken() {
		return chicken;
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
}  
