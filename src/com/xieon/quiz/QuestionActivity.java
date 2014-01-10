package com.xieon.quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.xieon.main.BaseActivity;
import com.xieon.main.R;
import com.xieon.model.User;
import com.xieon.utility.AppUtility;

public class QuestionActivity extends BaseActivity {

	List<Question> questions;
	int index=0;
	int maxQues;
	User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		Button start = (Button) findViewById(R.id.quizStart);
		Intent intent=getIntent();
		String json=intent.getStringExtra("questions");
		user=(User)intent.getSerializableExtra("user");
		questions=getQuestions(json);
		questions=getLimitedQuestion(questions, AppUtility.generateRandom(5, 20));
		maxQues=questions.size();
		setQuestion(index);
		final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
		start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				v.startAnimation(buttonClick);
				index++;
				System.out.println("maxQues: " + maxQues);
				if(index<maxQues){
					RadioGroup rg = (RadioGroup) findViewById(R.id.radioButtons);
				    final String userAnswer = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
				    questions.get(index-1).setUserAnswer(userAnswer);
				    setQuestion(index);
				    System.out.println("index: " + index);
				}else{
					System.out.println("last index: " + index);
					RadioGroup rg = (RadioGroup) findViewById(R.id.radioButtons);
				    final String userAnswer = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
				    questions.get(index-1).setUserAnswer(userAnswer);
					
					Intent intent = new Intent();
            		intent.setClass(getBaseContext(), QuizCompleteActivity.class);            		
            		
            		String score=createScore(questions);
            		user.setScore(score);
            		intent.putExtra("user", user);
            		
            		startActivity(intent);
            		System.out.println("List of questions: " + questions);
				}

			}
		});
	}

	public List<Question> getQuestions(String json) {

		List<Question> questions = null;
		try {
			JSONArray jsonArray = new JSONArray(json);
			questions = new ArrayList<Question>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Question question = new Question();
				question.setQuestion(jsonObject.getString("question"));
				question.setAnswer(jsonObject.getString("answer"));
				question.setOption1(jsonObject.getString("option1"));
				question.setOption2(jsonObject.getString("option2"));
				question.setOption3(jsonObject.getString("option3"));
				question.setOption4(jsonObject.getString("option4"));
				questions.add(question);

			}
			System.out.println("List of questions: " + questions);
		} catch (Exception e) {
			System.out
					.println("Exception: while loading InputActivity#getQuestions "
							+ e);
		}
		return questions;
	}
	public void setQuestion(int position){
		Question question=questions.get(position);
		TextView questionNoLevel=(TextView)findViewById(R.id.questionNoLevel);
		TextView questionLevel=(TextView)findViewById(R.id.questionLevel);
		RadioButton radioButton1=(RadioButton)findViewById(R.id.radioButton1);
		RadioButton radioButton2=(RadioButton)findViewById(R.id.radioButton2);
		RadioButton radioButton3=(RadioButton)findViewById(R.id.radioButton3);
		RadioButton radioButton4=(RadioButton)findViewById(R.id.radioButton4);
		RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioButtons);
		radioGroup.clearCheck();
		questionNoLevel.setText("Q. "+(position+1));
		questionLevel.setText(question.getQuestion());
		radioButton1.setText(question.getOption1());
		radioButton2.setText(question.getOption2());
		radioButton3.setText(question.getOption3());
		radioButton4.setText(question.getOption4());
		
	}
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public List<Question> getLimitedQuestion(List<Question> questions,HashSet<Integer> random){
		
		List<Question> newQues=new ArrayList<Question>();
		int quesNum;
		Iterator<Integer> itr=random.iterator();
		while(itr.hasNext()){
			quesNum=itr.next();
			newQues.add(questions.get(quesNum));
		}
		System.out.println("List of limited questions: " + questions);
		return newQues;
	}
	
	public String createScore(List<Question> questions){
		String score="";
		int scoreValue=0;		
		int size=questions.size();
		for (int i = 0; i < size; i++) {
			try {
				if(questions.get(i).getAnswer().endsWith(questions.get(i).getUserAnswer())){
					scoreValue+=1;
				}	
				System.out.println("question num: "+i+", question size: "+size+" question: "+questions.get(i));
			} catch (Exception e) {
				System.out.println("Exception: while creating score "+e);
				System.out.println("question: "+questions.get(i));
			}			
		}
		score=(scoreValue*100/size)+" % ";
		return score;
	}
}
