package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Chicken extends Sprite{

	private float x;
	private float y;
	private String isFat;
	
	private float dx;
	private int moveSpeed = 5;
	
	public Chicken(GameView gameView, Bitmap bmp, float x, float y) {
		super(gameView, bmp);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void update(int delta) {
		if (x > gameView.getWidth() - bmp.getWidth() - moveSpeed) {
            moveSpeed = -5;
		}
		if (x + moveSpeed< 0) {
            moveSpeed = 5;
		}
		x = x + (moveSpeed * delta);
	}
	
	@Override
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bmp, x , y, null);
	}
	
	public float getDx() {
		return dx;
	}
	
	
}
