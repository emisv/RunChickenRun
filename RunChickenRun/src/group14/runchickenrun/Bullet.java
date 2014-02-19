package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bullet {

	private float x;
	private float y;
	private float dx;
	private float dy;
	private float width = 10;
	private float height = 10;
	
	private int id;
	
	private float bulletSpeed = 5;
	
	private Bitmap bitmap;
	
	public Bullet(GameView gameView, float fromX, float fromY, float x, float y, int id) {
		this.id = id;
		this.x = x;
		this.y = y;
		
		if(this.id == Util.GUN_ID) {
			bitmap = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.gunbullet1);		
		}
		
		fromX += 25;
		fromY += 25;
		
		this.dx = (float) ((x - fromX) / Math.sqrt(((x - fromX) * (x - fromX)) + ((y - fromY) * (y - fromY))));
		this.dy = (float) ((y - fromY) / Math.sqrt(((x - fromX) * (x - fromX)) + ((y - fromY) * (y - fromY))));
	}
	
	public void update(int delta) {
		x += dx * bulletSpeed;
		y += dy * bulletSpeed;
	}
	
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x , y, null);
	}
	
}
