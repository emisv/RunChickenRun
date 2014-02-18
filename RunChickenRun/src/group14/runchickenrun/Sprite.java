package group14.runchickenrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
 
public class Sprite {       
       protected GameView gameView;
       protected Bitmap bmp;
      
       public Sprite(GameView gameView, Bitmap bmp) {
             this.gameView=gameView;
             this.bmp=bmp;
       }
 
       public void update(int delta) {
             
       }
      
       public void draw(Canvas canvas) {
             //canvas.drawBitmap(bmp, x , 10, null);
       }
}  
