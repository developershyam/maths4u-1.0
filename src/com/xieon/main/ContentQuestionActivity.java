package com.xieon.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xieon.model.TheoryContent;
import com.xieon.model.Topic;
import com.xieon.utility.AppUtility;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ContentQuestionActivity extends BaseActivity {

	int currentQuestion=0;
	int currentTopic=0;
	int maxTopic=0;
	List<TheoryContent> theoryContents;
	TextView contentHeadNumView;
	TextView contentHeadView;
	TextView contentView;
	Button opt1;
	Button opt2;
	Button opt3;
	Button opt4;
	Button selectedOpt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_question);
		final AlphaAnimation animation = new AlphaAnimation(1F, 0.2F);
		
		ImageView backBt = (ImageView) findViewById(R.id.content_question_back);
		ImageView nextBt = (ImageView) findViewById(R.id.content_question_next);
		ImageView imgHome = (ImageView) findViewById(R.id.content_question_home);
		ImageView imgQues = (ImageView) findViewById(R.id.content_question_question);
		ImageView imgKey = (ImageView) findViewById(R.id.content_question_key);
		ImageView imgSetting = (ImageView) findViewById(R.id.content_question_setting);
		ImageView imgRight = (ImageView) findViewById(R.id.content_question_wright);
		
		opt1 = (Button) findViewById(R.id.content_question_option1_bt);
		opt2 = (Button) findViewById(R.id.content_question_option2_bt);
		opt3 = (Button) findViewById(R.id.content_question_option3_bt);
		opt4 = (Button) findViewById(R.id.content_question_option4_bt);
		/*theoryContents=getTheoryContent();
		maxTopic=theoryContents.get(currentQuestion).getContents().size();
		contentHeadNumView= (TextView) findViewById(R.id.content_text_head_num);
		contentHeadView = (TextView) findViewById(R.id.content_text_head);
		contentView = (TextView) findViewById(R.id.content_text_content);
		contentHeadNumView.setText((currentTopic+1)+". ");
		contentHeadView.setText(theoryContents.get(currentQuestion).getTopic());
		contentView.setText(Html.fromHtml(theoryContents.get(currentQuestion).getContents().get(currentTopic)));*/
		
		backBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				int temp=currentTopic;
				currentTopic--;
				if(currentTopic>=0 && currentTopic<maxTopic){
					contentHeadNumView.setText((currentTopic+1)+". ");
					contentView.setText(Html.fromHtml(theoryContents.get(currentQuestion).getContents().get(currentTopic)));
				}
				else{
					currentTopic=temp;
				}
			}
		});
		nextBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				int temp=currentTopic;
				currentTopic++;
				if(currentTopic>=0 && currentTopic<maxTopic){
					contentHeadNumView.setText((currentTopic+1)+". ");
					contentView.setText(Html.fromHtml(theoryContents.get(currentQuestion).getContents().get(currentTopic)));
				}
				else{
					currentTopic=temp;
				}
			}
		});
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
		opt1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				Drawable greenBox = getResources().getDrawable(R.drawable.green_box);
				Drawable whiteBox = getResources().getDrawable(R.drawable.white_box);
				
				opt1.setBackground(greenBox);
				if(selectedOpt!=null)
					selectedOpt.setBackground(whiteBox);
				selectedOpt=opt1;				
			}
		});
		opt2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				Drawable greenBox = getResources().getDrawable(R.drawable.green_box);
				Drawable whiteBox = getResources().getDrawable(R.drawable.white_box);
				
				opt2.setBackground(greenBox);
				if(selectedOpt!=null)
					selectedOpt.setBackground(whiteBox);
				selectedOpt=opt2;
			}
		});
		opt3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				Drawable greenBox = getResources().getDrawable(R.drawable.green_box);
				Drawable whiteBox = getResources().getDrawable(R.drawable.white_box);
				
				opt3.setBackground(greenBox);				
				if(selectedOpt!=null)
					selectedOpt.setBackground(whiteBox);
				selectedOpt=opt3;
			}
		});
		opt4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				Drawable greenBox = getResources().getDrawable(R.drawable.green_box);
				Drawable whiteBox = getResources().getDrawable(R.drawable.white_box);
				
				opt4.setBackground(greenBox);
				if(selectedOpt!=null)
					selectedOpt.setBackground(whiteBox);
				selectedOpt=opt4;
			}
		});
	}
	private List<TheoryContent> getTheoryContent(){
		
		List<TheoryContent> theoryContents=new ArrayList<TheoryContent>();
		String json=AppUtility.loadJSON(getBaseContext(), "speedmath_therory.json");
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				TheoryContent theoryContent = new TheoryContent();
				theoryContent.setNum(jsonObject.getString("num"));
				theoryContent.setTopic(jsonObject.getString("topic"));
				JSONArray contentArray= jsonObject.getJSONArray(("contents"));
				List<String> contents=new ArrayList<String>();
				for (int j = 0; j < contentArray.length(); j++) {
					JSONObject contentObject = contentArray.getJSONObject(j);
					String content=contentObject.getString("content"+(j+1));
					contents.add(content);
				}
				theoryContent.setContents(contents);
				theoryContents.add(theoryContent);
			}
		} catch (Exception e) {
			System.out.println("Exception in getTheoryContent: "+e);
		}
		return theoryContents;
	}
	

}
