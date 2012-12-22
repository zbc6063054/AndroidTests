package com.android.test.apidemo.app;

import com.android.test.apidemo.R;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SetWallPaperActivity extends Activity {
	final static private int[] mColors=
		{Color.BLUE, Color.GREEN, Color.RED, Color.LTGRAY, Color.MAGENTA, Color.CYAN,
        		Color.YELLOW, Color.WHITE};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.wallpaper_2);
		
		final WallpaperManager wallpaperManager=WallpaperManager.getInstance(this);
		final Drawable wallpaper=wallpaperManager.getDrawable();
		final ImageView imageView=(ImageView)findViewById(R.id.imageview);
		imageView.setDrawingCacheEnabled(true);
		imageView.setImageDrawable(wallpaper);
		
		Button randomize=(Button)findViewById(R.id.randomize);
		randomize.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				int color=(int)Math.floor(Math.random()*mColors.length);
				wallpaper.setColorFilter(mColors[color], PorterDuff.Mode.MULTIPLY);
				imageView.setImageDrawable(wallpaper);
				imageView.invalidate();
			}
		});
		
		Button setWallPaper=(Button)findViewById(R.id.setwallpaper);
		setWallPaper.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
					wallpaperManager.setBitmap(imageView.getDrawingCache());
					finish();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		Button chooseWallPaper=(Button)findViewById(R.id.choosewallpaper);
		chooseWallPaper.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
				startActivity(intent);
			}
			
		});
		
	}

	
	
}
