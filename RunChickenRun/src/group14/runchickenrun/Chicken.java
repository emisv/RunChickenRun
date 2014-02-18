package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Chicken{

	private float x;
	private float y;
	private String isFat;
	
	private float dx;
	private int moveSpeed = 5;
	private boolean doWalk = true;
	private float walkSpeed = 2f;
	
	private GameView gameView;
	private Bitmap bitmap;
	
	public Chicken(GameView gameView, float x, float y) {
		//super(gameView, bmp);
		this.x = x;
		this.y = y;
		this.gameView = gameView;
		
		bitmap = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.chicken1);

		
	}
	
	public void update(int delta) {
		if (x > gameView.getWidth() - bitmap.getWidth() - moveSpeed) {
            moveSpeed = -5;
		}
		if (x + moveSpeed< 0) {
            moveSpeed = 5;
		}
		x = x + (moveSpeed * delta);
	}
	
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x , y, null);
	}
	
	public float getDx() {
		return dx;
	}
	
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	public boolean doWalk() {
		return doWalk;
	}
	
	public float getWalkSpeed() {
		return walkSpeed;
	}
}
