package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Block {

	protected GameView gameView;
	
	public Block(GameView gameView) {
		this.gameView = gameView;
	}

	public abstract void draw(Canvas canvcas);
	
}
