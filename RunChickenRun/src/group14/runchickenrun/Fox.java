package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Fox extends Enemy{

	private float x;
	private float y;
	private float width = 50;
	private float height = 58;
	
    private Bitmap bitmap;
	private Bitmap[] bitmaps;
	private final float IMG_INTERVAL = 30f;
	private float imgTimer;
	private int imgIndex = 0;

	private boolean hit = false;
    private float walkSpeed = 3f;
    
	public Fox(GameView gameView, Chicken chicken, float x, float y) {
		super(gameView);
		
		this.imgTimer = (int) (Math.random() * IMG_INTERVAL) + 1;
		this.x = x;
		this.y = y;
		
		bitmaps[0] = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.fox1_new);		
		bitmaps[1] = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.fox2_new);		
		bitmaps[2] = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.fox3_new);		
		
		bitmap = bitmaps[imgIndex];
		
	}
	
	@Override
	public void update(int delta) {
		
		if(imgTimer == 0) {
			imgTimer = IMG_INTERVAL;
			imgIndex = ++imgIndex % bitmaps.length;
		} else imgTimer--;
		
		this.x -= walkSpeed; /* *delta ? */
	}
	
	@Override
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmaps[imgIndex], x , y, null);
	}
	
}
