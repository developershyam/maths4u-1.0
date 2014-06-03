package com.xieon.quant;

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

public class PracticeQuestionActivity extends BaseActivity {

	int groupPosition;
	int childPosition;
	int current;
	int selectedOptionId;
	int selectedOptionColor;
	boolean fromExplain;
	
	int max;
	List<PracticeQuestion> practiceQuestions;
	List<PracticeQuestion> data;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quant_practice);
		
		Intent intent = getIntent();
		groupPosition = intent.getIntExtra("groupPosition", 0);
		childPosition = intent.getIntExtra("childPosition", 0);
		
		fromExplain= intent.getBooleanExtra("fromExplain", false);
		current= intent.getIntExtra("current", 0);
		selectedOptionId=intent.getIntExtra("selectedOptionId", -1);
		selectedOptionColor=intent.getIntExtra("selectedOptionColor", 0);
		
		String json = intent.getStringExtra("json");
		Gson gson = new Gson();
		data = gson.fromJson(json,
				new TypeToken<List<PracticeQuestion>>() {
				}.getType());
		max=data.size();
		if(fromExplain){
			practiceQuestions=data;
		}else{
			selectedOptionColor=Color.WHITE;
			practiceQuestions=createRandom(data);
		}
		
		setPracticeQuestion(practiceQuestions, current);
		
		Button check=(Button)findViewById(R.id.quant_check);
		Button explain=(Button)findViewById(R.id.quant_explaination);
		Button next=(Button)findViewById(R.id.quant_next);
		Button back=(Button)findViewById(R.id.quant_bck);
		
		final AlphaAnimation animation = new AlphaAnimation(1F, 0.2F);
		
		check.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				v.startAnimation(animation);
				RadioButton option1=(RadioButton) findViewById(R.id.quant_radioButton1);
				RadioButton option2=(RadioButton) findViewById(R.id.quant_radioButton2);
				RadioButton option3=(RadioButton) findViewById(R.id.quant_radioButton3);
				RadioButton option4=(RadioButton) findViewById(R.id.quant_radioButton4);
				option1.setBackgroundColor(Color.WHITE);
				option2.setBackgroundColor(Color.WHITE);
				option3.setBackgroundColor(Color.WHITE);
				option4.setBackgroundColor(Color.WHITE);
				
				PracticeQuestion practiceQuestion=practiceQuestions.get(current);
				RadioGroup radioGroup=(RadioGroup) findViewById(R.id.quant_radioButtons);
				int id=radioGroup.getCheckedRadioButtonId();
				System.out.println("getCheckedRadioButtonId : "+id);
				if(id!= -1){
					
					RadioButton option=(RadioButton) findViewById(id);
					
					if(practiceQuestion.getAnswer().replaceAll("\\<[^>]*>","").equalsIgnoreCase(option.getText().toString())){					
						option.setBackgroundColor(Color.GREEN);
						selectedOptionColor=Color.GREEN;
					}
					else{
						option.setBackgroundColor(Color.RED);
						selectedOptionColor=Color.RED;
					}
				}
			}
		});
		next.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				if (current < max-1) {
					v.startAnimation(animation);
					current++;
					fromExplain=false;
					selectedOptionColor=Color.WHITE;
					selectedOptionId=-1;
					setPracticeQuestion(practiceQuestions, current);
				}
			}
		});
		
		explain.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				RadioGroup radioGroup=(RadioGroup) findViewById(R.id.quant_radioButtons);
				selectedOptionId=radioGroup.getCheckedRadioButtonId();
				Intent intent = new Intent();
				intent.putExtra("groupPosition", groupPosition);
				intent.putExtra("childPosition", childPosition);
				intent.putExtra("current", current);
				intent.putExtra("selectedOptionId", selectedOptionId);
				intent.putExtra("fromExplain", true);
				intent.putExtra("selectedOptionColor", selectedOptionColor);
				Gson gson = new Gson();
				String jsonPractice = gson.toJson(practiceQuestions);
				intent.putExtra("json", jsonPractice);
				intent.setClass(getBaseContext(), ExplainPracticeActivity.class);            		      		
				startActivity(intent);
			}
		});
		
		back.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.putExtra("groupPosition", groupPosition);
				intent.putExtra("childPosition", childPosition);
				intent.setClass(getBaseContext(), ContentList.class);            		      		
				startActivity(intent);
			}
		});
		
	}
	private List<PracticeQuestion> createRandom(
			List<PracticeQuestion> data) {
		List<PracticeQuestion> practiceQuestions = new ArrayList<PracticeQuestion>();
		HashSet<Integer> selected = AppUtility.generateRandom(max, max);
		for (Iterator iterator = selected.iterator(); iterator.hasNext();) {
			int loc = (Integer) iterator.next();
			PracticeQuestion practiceQuestion = data.get(loc);
			practiceQuestions.add(practiceQuestion);
		}
		return practiceQuestions;
	}
	
	private void setPracticeQuestion(List<PracticeQuestion> data, int current) {
				

		final PracticeQuestion practiceQuestion = data.get(current);
		TextView num = (TextView) findViewById(R.id.quant_questionNoLevel);
		num.setText("Q. "+(current+1)+" ");
		TextView question = (TextView) findViewById(R.id.quant_questionLevel);
		question.setText(Html.fromHtml(practiceQuestion.getQuestion()));
		
		RadioButton option1 = (RadioButton) findViewById(R.id.quant_radioButton1);
		option1.setText(Html.fromHtml(practiceQuestion.getOption1()));
		option1.setChecked(false);
		RadioButton option2 = (RadioButton) findViewById(R.id.quant_radioButton2);
		option2.setText(Html.fromHtml(practiceQuestion.getOption2()));
		option2.setChecked(false);
		RadioButton option3 = (RadioButton) findViewById(R.id.quant_radioButton3);
		option3.setText(Html.fromHtml(practiceQuestion.getOption3()));
		option3.setChecked(false);
		RadioButton option4 = (RadioButton) findViewById(R.id.quant_radioButton4);
		option4.setText(Html.fromHtml(practiceQuestion.getOption4()));
		option4.setChecked(false);
		
		option1.setBackgroundColor(Color.WHITE);
		option2.setBackgroundColor(Color.WHITE);
		option3.setBackgroundColor(Color.WHITE);
		option4.setBackgroundColor(Color.WHITE);
		
		RadioGroup radioGroup=(RadioGroup) findViewById(R.id.quant_radioButtons);
		radioGroup.clearCheck();
		
		if(fromExplain && selectedOptionId!=-1){
			
			RadioButton selectedOption = (RadioButton) findViewById(selectedOptionId);
			selectedOption.setChecked(true);
			selectedOption.setBackgroundColor(selectedOptionColor);
			
		}
			
	}
	
}
