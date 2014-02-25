package com.xieon.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xieon.model.TheoryContent;
import com.xieon.utility.AppUtility;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ContentActivity extends BaseActivity {

	int currentQuestion=0;
	int currentTopic=0;
	int maxTopic=0;
	List<TheoryContent> theoryContents;
	TextView contentHeadNumView;
	TextView contentHeadView;
	TextView contentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		final AlphaAnimation animation = new AlphaAnimation(1F, 0.2F);
		
		ImageView backBt = (ImageView) findViewById(R.id.content_back);
		ImageView nextBt = (ImageView) findViewById(R.id.content_next);
		ImageView imgHome = (ImageView) findViewById(R.id.content_home);
		ImageView imgQues = (ImageView) findViewById(R.id.content_question);
		ImageView imgKey = (ImageView) findViewById(R.id.content_key);
		ImageView imgSetting = (ImageView) findViewById(R.id.content_setting);
		ImageView imgRight = (ImageView) findViewById(R.id.content_wright);
		
		theoryContents=getTheoryContent();
		maxTopic=theoryContents.get(currentQuestion).getContents().size();
		contentHeadNumView= (TextView) findViewById(R.id.content_text_head_num);
		contentHeadView = (TextView) findViewById(R.id.content_text_head);
		contentView = (TextView) findViewById(R.id.content_text_content);
		contentHeadNumView.setText((currentTopic+1)+". ");
		contentHeadView.setText(theoryContents.get(currentQuestion).getTopic());
		contentView.setText(Html.fromHtml(theoryContents.get(currentQuestion).getContents().get(currentTopic)));
		
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
