package com.xieon.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.xieon.main.BaseActivity;
import com.xieon.main.R;
import com.xieon.model.User;
public class QuizCompleteActivity extends BaseActivity {
	
	User user;
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.quiz_complete);
	        Intent intent=getIntent();
	        user=(User)intent.getSerializableExtra("user");
	        Button start = (Button)findViewById(R.id.quizStart);
	        TextView name = (TextView)findViewById(R.id.quiz_complete_name);
	        name.setText("Hi "+user.getUserName()+",");
	        TextView score = (TextView)findViewById(R.id.quiz_complete_score);
	        score.setText("You have scored "+user.getScore());
	        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
	        start.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click   
                	v.startAnimation(buttonClick);
                	Intent intent = new Intent();
            		intent.setClass(getBaseContext(), InputActivity.class);
            		startActivity(intent);
                }
            });
	 }
	
}
