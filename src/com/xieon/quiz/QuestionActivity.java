package com.xieon.quiz;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.xieon.main.BaseActivity;
import com.xieon.main.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class QuestionActivity extends BaseActivity {
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.question);
	        Button start = (Button)findViewById(R.id.quizStart);
	        
	        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
	        start.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click   
                	v.startAnimation(buttonClick);
                	 try {
                		 
                		InputStream is = getAssets().open("question.json");

     					int size = is.available();

     					byte[] buffer = new byte[size];

     					is.read(buffer);

     					is.close();

     					String json = new String(buffer, "UTF-8");
     					
                    	 JSONArray jsonArray = new JSONArray(json);
                    	List<Question> questions=new ArrayList<Question>();
                    	 for (int i = 0; i < jsonArray.length(); i++) {
                    	        JSONObject jsonObject = jsonArray.getJSONObject(i);
                    	        Question question=new Question();
                    	        question.setQuestion(jsonObject.getString("question"));
                    	        question.setAnswer(jsonObject.getString("answer"));
                    	        question.setOption1(jsonObject.getString("option1"));
                    	        question.setOption2(jsonObject.getString("option2"));
                    	        question.setOption3(jsonObject.getString("option3"));
                    	        question.setOption4(jsonObject.getString("option4"));
                    	       
                    	         
                    	      }
                    	 System.out.println("JSON...............\n"+questions);
					} catch (Exception e) {
						System.out.println("Exception: "+e);
					}
                	 
                	 
                    System.out.println("Hi...............");
                    
                }
            });
	 }

	
}
