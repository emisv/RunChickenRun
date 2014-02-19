package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Chicken{

	private float x;
	private float y;
	private float width = 64;
	private float height = 56;
	
	private float dx;
	private int moveSpeed = 5;
	private boolean doWalk = true;
	private float walkSpeed = 2f;
	
	private GameView gameView;
	
	private Bitmap bitmap;
	private Bitmap[] bitmaps = new Bitmap[3];
	private final float IMG_INTERVAL = 30f;
	private float imgTimer = IMG_INTERVAL;
	private int imgIndex = 0;
	
	private final float JUMP_INTERVAL = 20f;
	private float jumpTimer;
	private float jumpSpeed = 13f;
	private boolean canJump = true;
	
	private float shootTimer;
	
	public Chicken(GameView gameView, float x, float y) {
		this.x = x;
		this.y = y;
		this.gameView = gameView;
		
		bitmaps[0] = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.chicken1);		
		bitmaps[1] = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.chicken2);		
		bitmaps[2] = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.chicken3);		
		
		bitmap = bitmaps[imgIndex];
	}
	
	public void update(int delta) {
		/*if (x > gameView.getWidth() - bitmap.getWidth() - moveSpeed) {
            moveSpeed = -5;
		}
		if (x + moveSpeed< 0) {
            moveSpeed = 5;
		}
		x = x + (moveSpeed * delta);*/
		
		if(imgTimer == 0) {
			imgTimer = IMG_INTERVAL;
			imgIndex = ++imgIndex % bitmaps.length;
		} else imgTimer--;
		
		if(doWalk) {
	       	dx -= walkSpeed;
	    }
		
		if(jumpTimer > 0) {
			y -= jumpSpeed;
			jumpTimer--;
		}
		
		if(!canJump) {
			y += Util.GRAVITY_SPEED;
		}
		
		
	}
	
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmaps[imgIndex], x , y, null);
	}
	
	public float getDx() {
		return dx;
	}
	
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	public boolean getDoWalk() {
		return doWalk;
	}
	
	public float getWalkSpeed() {
		return walkSpeed;
	}
	
	public boolean getCanJump() {
		return canJump;
	}
	
	public void setDoWalk(boolean walk) {
		this.doWalk = walk;
	}
	
	public void jump() {
		jumpTimer = JUMP_INTERVAL;
	}
}
