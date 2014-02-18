package group14.runchickenrun;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

public class SoundManager {
	
	SoundPool soundPool;
	AudioManager audioManager;
	MediaPlayer mediaPlayer;
	int shotID;
	int cluckID;
	int ohID;
	
	public SoundManager(MainActivity activity){
		soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0); 
		audioManager = (AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		try{
			AssetFileDescriptor descriptor = activity.getAssets().openFd("pistolshot.wav");
			shotID = soundPool.load(descriptor, 1);
			descriptor = activity.getAssets().openFd("cluck.wav");
			cluckID = soundPool.load(descriptor, 1);
			descriptor = activity.getAssets().openFd("ohhh.wav");
			ohID = soundPool.load(descriptor, 1);
			
		}
		catch(Exception e){
			Log.d("Sound Sample","Sound fucked up");
			throw new RuntimeException(e);
			
		}
		mediaPlayer = new MediaPlayer();
		try
		{
			AssetFileDescriptor descriptor = activity.getAssets().openFd( "wahwah.wav" );
	        mediaPlayer.setDataSource( descriptor.getFileDescriptor() );
	        mediaPlayer.prepare();
	        mediaPlayer.setLooping(true);
	        mediaPlayer.start();
		
		}catch(Exception e){
			e.printStackTrace();
			Log.d("Sound Sample","Sound fucked up again with the music");
			throw new RuntimeException(e);
			
		}
	}
	
	public void playShot(){
		int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(shotID, volume, volume, 1, 0, 1);
	}
	
	public void playCluck(){
		int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(cluckID, volume, volume, 1, 0, 1);
	}
	public void playOh(){
		int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(ohID, volume, volume, 1, 0, 1);
	}
	
	public void toggleSound(){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
		else{
			mediaPlayer.start();
		}
	}
	
	public void dispose(){
		soundPool.release();
		mediaPlayer.release();
	}

}
