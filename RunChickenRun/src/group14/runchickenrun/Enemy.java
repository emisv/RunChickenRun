package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Enemy {

	protected GameView gameView;
	
    protected float x;
    protected float y;
    
	public Enemy(GameView gameView) {
		this.gameView = gameView;
	}

	public abstract void update(int delta);
	
	public abstract void draw(Canvas canvas);
	 
}
