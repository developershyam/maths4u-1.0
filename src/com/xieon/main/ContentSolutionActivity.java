package com.xieon.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xieon.model.Example;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ContentSolutionActivity extends BaseActivity {

	int currentExample=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_solution);
		final AlphaAnimation animation = new AlphaAnimation(1F, 0.2F);
		
		ImageView imgHome = (ImageView) findViewById(R.id.content_solution_home);
		ImageView imgQues = (ImageView) findViewById(R.id.content_solution_question);
		ImageView imgKey = (ImageView) findViewById(R.id.content_solution_key);
		ImageView imgSetting = (ImageView) findViewById(R.id.content_solution_setting);
		ImageView imgRight = (ImageView) findViewById(R.id.content_solution_wright);
		
		Intent intent=getIntent();
		Example example=(Example)intent.getSerializableExtra("example");
		TextView headNum = (TextView) findViewById(R.id.content_solution_text_head_num);
		TextView head = (TextView) findViewById(R.id.content_solution_text_head);
		TextView headContent = (TextView) findViewById(R.id.content_solution_text_content);
		headNum.setText(example.getNum()+". ");
		head.setText(example.getQuestion());
		headContent.setText(Html.fromHtml(example.getSolution()));
		imgHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				Intent intent = new Intent();
				intent.setClass(getBaseContext(), ContentActivity.class);
				startActivity(intent);
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
				v.startAnimation(animation);
				Intent intent = new Intent();
				intent.setClass(getBaseContext(), ContentQuestionActivity.class);
				startActivity(intent);
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
