package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BackgroundSprite extends Sprite{

	private float x = 0;
	private float y = 0;
	private Chicken chicken;
	
	public BackgroundSprite(GameView gameView, Bitmap bmp, Chicken chicken, float x) {
		super(gameView, bmp);
		this.x = x;
		this.chicken = chicken;
	}
	
	@Override
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bmp, x + chicken.getDx() , y, null);
	}

}
