package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bullet {

	private float targetX;
	private float targetY;
	private float dx;
	private float dy;
	private float width = 10;
	private float height = 10;
	private float fromX;
	private float fromY;
	
	private int id;
	
	private float bulletSpeed = 5;
	
	private Bitmap bitmap;
	
	public Bullet(GameView gameView, float fromX, float fromY, float x, float y, int id) {
		this.id = id;
		this.fromX = fromX + 25;
		this.fromY = fromY + 25;
		
		if(this.id == Util.GUN_ID) {
			bitmap = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.gunbullet1);		
		}
		
		this.dx = (float) ((x - fromX) / Math.sqrt(((x - fromX) * (x - fromX)) + ((y - fromY) * (y - fromY))));
		this.dy = (float) ((y - fromY) / Math.sqrt(((x - fromX) * (x - fromX)) + ((y - fromY) * (y - fromY))));
	}
	
	public void update(int delta) {
		targetX += dx * bulletSpeed;
		targetY += dy * bulletSpeed;
	}
	
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, fromX + targetX , fromY + targetY, null);
	}
	
}
