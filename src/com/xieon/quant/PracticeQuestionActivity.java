package com.xieon.quant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
	int max;
	List<PracticeQuestion> practiceQuestions;
	List<PracticeQuestion> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quant_practice);
		
		Intent intent = getIntent();
		groupPosition = intent.getIntExtra("groupPosition", 0);
		childPosition = intent.getIntExtra("currentTopic", 0);
		String json = intent.getStringExtra("json");
		Gson gson = new Gson();
		data = gson.fromJson(json,
				new TypeToken<List<PracticeQuestion>>() {
				}.getType());
		max=data.size();
		practiceQuestions=createRandom(data);
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
				RadioButton option=(RadioButton) findViewById(id);
				
				if(practiceQuestion.getAnswer().equalsIgnoreCase(option.getText().toString())){					
					option.setBackgroundColor(Color.GREEN);
				}
				else{
					option.setBackgroundColor(Color.RED);
				}
			}
		});
		next.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				if (current < max-1) {
					v.startAnimation(animation);
					current++;
					setPracticeQuestion(practiceQuestions, current);
				}
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
		question.setText(practiceQuestion.getQuestion());
		
		RadioButton option1 = (RadioButton) findViewById(R.id.quant_radioButton1);
		option1.setText(practiceQuestion.getOption1());
		RadioButton option2 = (RadioButton) findViewById(R.id.quant_radioButton2);
		option2.setText(practiceQuestion.getOption2());
		RadioButton option3 = (RadioButton) findViewById(R.id.quant_radioButton3);
		option3.setText(practiceQuestion.getOption3());
		RadioButton option4 = (RadioButton) findViewById(R.id.quant_radioButton4);
		option4.setText(practiceQuestion.getOption4());
			
	}
	
}
