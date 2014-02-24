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

import com.xieon.model.Example;
import com.xieon.model.TheoryContent;
import com.xieon.model.Topic;
import com.xieon.quiz.QuestionActivity;
import com.xieon.utility.AppUtility;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ContentQuestionActivity extends BaseActivity {

	int currentQuestion=0;
	int currentExample=0;
	int maxExample=0;
	List<Example> examples;
	TextView contentQuestionNumView;
	TextView contentQuestionView;
	Button opt1;
	Button opt2;
	Button opt3;
	Button opt4;
	Button solution;
	Button selectedOpt;
	TextView optText1;
	TextView optText2;
	TextView optText3;
	TextView optText4;
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
		solution = (Button) findViewById(R.id.content_question_solution_bt);
		
		optText1=(TextView)findViewById(R.id.content_question_option1_txt);
		optText2=(TextView)findViewById(R.id.content_question_option2_txt);
		optText3=(TextView)findViewById(R.id.content_question_option3_txt);
		optText4=(TextView)findViewById(R.id.content_question_option4_txt);
		
		
		examples=getTheoryExample();
		maxExample=examples.size();
		contentQuestionNumView= (TextView) findViewById(R.id.content_question_questionNoLevel);
		contentQuestionView = (TextView) findViewById(R.id.content_question_questionLevel);
		contentQuestionNumView.setText((currentExample+1)+". ");
		contentQuestionView.setText(examples.get(currentExample).getQuestion());
		optText1.setText(examples.get(currentExample).getOption1());
		optText2.setText(examples.get(currentExample).getOption2());
		optText3.setText(examples.get(currentExample).getOption3());
		optText4.setText(examples.get(currentExample).getOption4());
		
		backBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				int temp=currentExample;
				currentExample--;
				if(currentExample>=0 && currentExample<maxExample){
					contentQuestionNumView.setText((currentExample+1)+". ");
					contentQuestionView.setText(examples.get(currentExample).getQuestion());
					optText1.setText(examples.get(currentExample).getOption1());
					optText2.setText(examples.get(currentExample).getOption2());
					optText3.setText(examples.get(currentExample).getOption3());
					optText4.setText(examples.get(currentExample).getOption4());
				}
				else{
					currentExample=temp;
				}
			}
		});
		nextBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				int temp=currentExample;
				currentExample++;
				if(currentExample>=0 && currentExample<maxExample){
					contentQuestionNumView.setText((currentExample+1)+". ");
					contentQuestionView.setText(examples.get(currentExample).getQuestion());
					optText1.setText(examples.get(currentExample).getOption1());
					optText2.setText(examples.get(currentExample).getOption2());
					optText3.setText(examples.get(currentExample).getOption3());
					optText4.setText(examples.get(currentExample).getOption4());
				}
				else{
					currentExample=temp;
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
				Drawable redBox = getResources().getDrawable(R.drawable.red_box);
				
				if(examples.get(currentExample).getOption1().endsWith(examples.get(currentExample).getAnswer())){
					opt1.setBackground(greenBox);
				}
				else{
					opt1.setBackground(redBox);
				}
				
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
				Drawable redBox = getResources().getDrawable(R.drawable.red_box);
				
				if(examples.get(currentExample).getOption2().endsWith(examples.get(currentExample).getAnswer())){
					opt2.setBackground(greenBox);
				}
				else{
					opt2.setBackground(redBox);
				}
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
				Drawable redBox = getResources().getDrawable(R.drawable.red_box);
				
				if(examples.get(currentExample).getOption3().endsWith(examples.get(currentExample).getAnswer())){
					opt3.setBackground(greenBox);
				}
				else{
					opt3.setBackground(redBox);
				}

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
				Drawable redBox = getResources().getDrawable(R.drawable.red_box);
				
				if(examples.get(currentExample).getOption4().endsWith(examples.get(currentExample).getAnswer())){
					opt4.setBackground(greenBox);
				}
				else{
					opt4.setBackground(redBox);
				}

				if(selectedOpt!=null)
					selectedOpt.setBackground(whiteBox);
				selectedOpt=opt4;
			}
		});
		solution.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				Intent intent = new Intent();				
				intent.setClass(getBaseContext(), ContentSolutionActivity.class);
				Example example=examples.get(currentExample);
				intent.putExtra("example", example);
				startActivity(intent);
			}
		});
	}
	private List<Example> getTheoryExample(){
		
		List<Example> examples=new ArrayList<Example>();
		String json=AppUtility.loadJSON(getBaseContext(), "speedmath_example.json");
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Example example = new Example();
				example.setNum(jsonObject.getString("num"));
				example.setQuestion(jsonObject.getString("question"));
				example.setOption1(jsonObject.getString("option1"));
				example.setOption2(jsonObject.getString("option2"));
				example.setOption3(jsonObject.getString("option3"));
				example.setOption4(jsonObject.getString("option4"));
				example.setAnswer(jsonObject.getString("answer"));
				example.setSolution(jsonObject.getString("solution"));
				examples.add(example);
				
			}
		} catch (Exception e) {
			System.out.println("Exception in getTheoryExample: "+e);
		}
		return examples;
	}
	

}
