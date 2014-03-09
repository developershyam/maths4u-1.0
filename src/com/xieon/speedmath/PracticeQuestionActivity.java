package com.xieon.speedmath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
	int maxPractice;
	int currentPractic;
	List<PracticeQuestion> selectedPracticeQuestions;
	List<PracticeQuestion> practiceQuestions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speedmath_practice);
		Intent intent = getIntent();
		groupPosition = intent.getIntExtra("groupPosition", 0);
		childPosition = intent.getIntExtra("currentTopic", 0);
		String json = intent.getStringExtra("json");
		Gson gson = new Gson();
		practiceQuestions = gson.fromJson(json,
				new TypeToken<List<PracticeQuestion>>() {
				}.getType());
		selectedPracticeQuestions = getSelectPracticeQuestion(practiceQuestions);
		setSelectPracticeQuestion(selectedPracticeQuestions);

		Button back = (Button) findViewById(R.id.speedmath_practice_back);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.putExtra("groupPosition", groupPosition);
				intent.putExtra("currentTopic", childPosition);
				intent.setClass(getBaseContext(), ContentList.class);
				startActivity(intent);
			}
		});

		Button refresh = (Button) findViewById(R.id.speedmath_practice_refresh);
		refresh.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				refresh();
			}
		});

	}

	private List<PracticeQuestion> getSelectPracticeQuestion(
			List<PracticeQuestion> data) {
		List<PracticeQuestion> practiceQuestions = new ArrayList<PracticeQuestion>();
		HashSet<Integer> selected = AppUtility.generateRandom(5, 15);
		for (Iterator iterator = selected.iterator(); iterator.hasNext();) {
			int loc = (Integer) iterator.next();
			PracticeQuestion practiceQuestion = data.get(loc);
			practiceQuestions.add(practiceQuestion);
		}
		return practiceQuestions;
	}

	private void setSelectPracticeQuestion(List<PracticeQuestion> data) {

		final PracticeQuestion practiceQuestion1 = data.get(0);
		TextView text1 = (TextView) findViewById(R.id.speedmath_practice_question_text1);
		text1.setText(practiceQuestion1.getQuestion());
		final EditText input1 = (EditText) findViewById(R.id.speedmath_practice_question_input1);
		Button check1 = (Button) findViewById(R.id.speedmath_practice_question_button1);
		check1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String input = input1.getText().toString();
				String answer = practiceQuestion1.getAnswer();
				if (answer.equals(input)) {
					input1.setBackgroundColor(Color.GREEN);
				} else {
					input1.setBackgroundColor(Color.RED);
				}

			}
		});

		final PracticeQuestion practiceQuestion2 = data.get(1);
		TextView text2 = (TextView) findViewById(R.id.speedmath_practice_question_text2);
		text2.setText(practiceQuestion2.getQuestion());
		final EditText input2 = (EditText) findViewById(R.id.speedmath_practice_question_input2);
		Button check2 = (Button) findViewById(R.id.speedmath_practice_question_button2);
		check2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String input = input2.getText().toString();
				String answer = practiceQuestion2.getAnswer();
				if (answer.equals(input)) {
					input2.setBackgroundColor(Color.GREEN);
				} else {
					input2.setBackgroundColor(Color.RED);
				}

			}
		});

		final PracticeQuestion practiceQuestion3 = data.get(2);
		TextView text3 = (TextView) findViewById(R.id.speedmath_practice_question_text3);
		text3.setText(practiceQuestion3.getQuestion());
		final EditText input3 = (EditText) findViewById(R.id.speedmath_practice_question_input3);
		Button check3 = (Button) findViewById(R.id.speedmath_practice_question_button3);
		check3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String input = input3.getText().toString();
				String answer = practiceQuestion3.getAnswer();
				if (answer.equals(input)) {
					input3.setBackgroundColor(Color.GREEN);
				} else {
					input3.setBackgroundColor(Color.RED);
				}

			}
		});

		final PracticeQuestion practiceQuestion4 = data.get(3);
		TextView text4 = (TextView) findViewById(R.id.speedmath_practice_question_text4);
		text4.setText(practiceQuestion4.getQuestion());
		final EditText input4 = (EditText) findViewById(R.id.speedmath_practice_question_input4);
		Button check4 = (Button) findViewById(R.id.speedmath_practice_question_button4);
		check4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String input = input4.getText().toString();
				String answer = practiceQuestion4.getAnswer();
				if (answer.equals(input)) {
					input4.setBackgroundColor(Color.GREEN);
				} else {
					input4.setBackgroundColor(Color.RED);
				}

			}
		});

		final PracticeQuestion practiceQuestion5 = data.get(4);
		TextView text5 = (TextView) findViewById(R.id.speedmath_practice_question_text5);
		text5.setText(practiceQuestion5.getQuestion());
		final EditText input5 = (EditText) findViewById(R.id.speedmath_practice_question_input5);
		Button check5 = (Button) findViewById(R.id.speedmath_practice_question_button5);
		check5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String input = input5.getText().toString();
				String answer = practiceQuestion5.getAnswer();
				if (answer.equals(input)) {
					input5.setBackgroundColor(Color.GREEN);
				} else {
					input5.setBackgroundColor(Color.RED);
				}

			}
		});

	}

	private void refresh() {
		selectedPracticeQuestions = getSelectPracticeQuestion(practiceQuestions);
		setSelectPracticeQuestion(selectedPracticeQuestions);
	}
}
