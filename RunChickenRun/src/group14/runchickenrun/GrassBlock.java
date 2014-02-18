package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GrassBlock extends Block{
	
	private float x;
	private float y;
	private Chicken chicken;
	
	public GrassBlock(GameView gameView, Bitmap bitmap, Chicken chicken, float x, float y) {
		super(gameView, bitmap);
		this.x = x;
		this.y = y;
		this.chicken = chicken;
	}
	
	@Override
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bmp, x + chicken.getDx() , y, null);
	}
	
}
