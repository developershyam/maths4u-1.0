package com.xieon.logical;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xieon.main.BaseActivity;
import com.xieon.main.R;
import com.xieon.model.PracticeQuestion;
import com.xieon.utility.AppUtility;

public class ExplainPracticeActivity extends BaseActivity {

	int groupPosition;
	int childPosition;
	int current;
	int selectedOptionId;
	int selectedOptionColor;
	List<PracticeQuestion> practiceQuestions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logical_explain);
		
		Intent intent = getIntent();
		groupPosition = intent.getIntExtra("groupPosition", 0);
		childPosition = intent.getIntExtra("childPosition", 0);
		current = intent.getIntExtra("current", 0);
		selectedOptionId=intent.getIntExtra("selectedOptionId", 0);
		selectedOptionColor=intent.getIntExtra("selectedOptionColor", 0);
		
		String json = intent.getStringExtra("json");
		Gson gson = new Gson();
		practiceQuestions = gson.fromJson(json,
				new TypeToken<List<PracticeQuestion>>() {
				}.getType());
		
		setPracticeExplain(practiceQuestions, current);
		
		Button back=(Button)findViewById(R.id.logical_explain_back);
		
		final AlphaAnimation animation = new AlphaAnimation(1F, 0.2F);
		
		back.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				v.startAnimation(animation);
				
				Intent intent = new Intent();
				intent.putExtra("groupPosition", groupPosition);
				intent.putExtra("childPosition", childPosition);
				intent.putExtra("current", current);
				intent.putExtra("fromExplain", true);
				intent.putExtra("selectedOptionId", selectedOptionId);
				intent.putExtra("selectedOptionColor", selectedOptionColor);
				Gson gson = new Gson();
				String jsonPractice = gson.toJson(practiceQuestions);
				intent.putExtra("json", jsonPractice);
				intent.setClass(getBaseContext(), PracticeQuestionActivity.class);            		      		
				startActivity(intent);
			}
		});
			
		
		
	}
	
	private void setPracticeExplain(List<PracticeQuestion> data, int current) {
				

		final PracticeQuestion practiceQuestion = data.get(current);
		TextView num = (TextView) findViewById(R.id.logical_explain_questionNoLevel);
		num.setText("Q. "+(current+1)+" ");
		TextView question = (TextView) findViewById(R.id.logical_explain_questionLevel);
		question.setText(Html.fromHtml(practiceQuestion.getQuestion()));
		
		TextView explain = (TextView) findViewById(R.id.logical_explain_text);
		explain.setText(Html.fromHtml(practiceQuestion.getExplanation()));
			
	}
	
}
