package group14.runchickenrun;

import group14.runchickenrun.util.SystemUiHider;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ToggleButton;
import android.support.v4.app.NavUtils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SettingsActivity extends Activity {
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
	
	private ToggleButton soundToggleButton;
	private ToggleButton hintToggleButton;
	private ToggleButton vibrateToggleButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_settings);
		
		soundToggleButton = (ToggleButton) findViewById(R.id.toggle_sound_menu);
		hintToggleButton = (ToggleButton) findViewById(R.id.toggle_tutorial_menu);
		vibrateToggleButton = (ToggleButton) findViewById(R.id.toggle_vibration_menu);
		
		if(prefs == null){
			prefs = this.getSharedPreferences("com.group14.RunChickenRun", Context.MODE_PRIVATE);
			soundToggle = prefs.getBoolean("usingSound", true);
			hintToggle = prefs.getBoolean("usingHints", true);
			vibrateToggle = prefs.getBoolean("usingVibrate", true);
			soundToggleButton.setChecked(soundToggle);
			hintToggleButton.setChecked(hintToggle);
			vibrateToggleButton.setChecked(vibrateToggle);
		}
		
	}
	public void onSoundToggle(View view){
	    if(((ToggleButton) view).isChecked()) {
	    	SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("usingSound", true); // value to store
            editor.commit();
            
            //// SET SOUND
            
            
	    } else {
	    	SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("usingSound", false); // value to store
            editor.commit();
            
            //// REMOVE SOUND
	    }    
	}
	public void onHintToggle(View view){
	    if(((ToggleButton) view).isChecked()) {
	    	SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("usingHints", true); // value to store
            editor.commit();
            
            //// SET HINTS
            
	    } else {
	    	SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("usingHints", false); // value to store
            editor.commit();
            
            //// REMOVE HINTS
            
	    }    
	}
	public void onVibrateToggle(View view){
	    if(((ToggleButton) view).isChecked()) {
	    	SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("usingVibrate", true); // value to store
            editor.commit();
            
            //// SET VIBRATE
            
	    } else {
	    	SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("usingVibrate", false); // value to store
            editor.commit();
            
            //// REMOVE VIBRATE
            
	    }    
	}

}
