package group14.runchickenrun;

import group14.runchickenrun.util.SystemUiHider;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainMenuActivity extends Activity {
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	
	private boolean soundToggle;
	private boolean hintToggle;
	private boolean vibrateToggle;
	private SharedPreferences prefs;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_menu);
		
		if(prefs == null){
			prefs = this.getSharedPreferences("com.group14.RunChickenRun", Context.MODE_PRIVATE);
			soundToggle = prefs.getBoolean("usingSound", true);
			hintToggle = prefs.getBoolean("usingHints", true);
			vibrateToggle = prefs.getBoolean("usingVibrate", true);
		}
		
		
	}


	
	public void toSettings(View v){
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
	
	public void toHighscore(View v){
		Intent intent = new Intent(this, HighscoreActivity.class);
		startActivity(intent);
	}
	public void toStartGame(View v){
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		//setContentView(new GameView(this));
	}


}
