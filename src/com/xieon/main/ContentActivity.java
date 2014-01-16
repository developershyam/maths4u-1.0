package com.xieon.main;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ContentActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		final AlphaAnimation animation = new AlphaAnimation(1F, 0.2F);
		animation.setDuration(1000);
		ImageView backBt = (ImageView) findViewById(R.id.content_back);
		ImageView nextBt = (ImageView) findViewById(R.id.content_next);
		ImageView imgHome = (ImageView) findViewById(R.id.content_home);
		ImageView imgQues = (ImageView) findViewById(R.id.content_question);
		ImageView imgKey = (ImageView) findViewById(R.id.content_key);
		ImageView imgSetting = (ImageView) findViewById(R.id.content_setting);
		ImageView imgRight = (ImageView) findViewById(R.id.content_right);
		
		
		
		backBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
			}
		});
		nextBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
			}
		});
		imgHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
			}
		});
		imgKey.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
			}
		});
		imgQues.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
			}
		});
		imgRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
			}
		});
		imgSetting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
			}
		});
	}

}
