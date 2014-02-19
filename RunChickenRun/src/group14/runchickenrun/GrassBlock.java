package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class GrassBlock extends Block{
	
	private float x;
	private float y;
	private Chicken chicken;
    private Bitmap bmp;
	
	public GrassBlock(GameView gameView, Chicken chicken, float x, float y) {
		super(gameView);
		this.bmp = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.grassblock);
		this.x = x;
		this.y = y;
		this.chicken = chicken;
	}
	
	@Override
	public void draw(Canvas canvas) {
        canvas.drawBitmap(bmp, x + chicken.getDx() , y, null);
	}
	
}
