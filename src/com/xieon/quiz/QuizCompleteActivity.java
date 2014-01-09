package com.xieon.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import com.xieon.main.BaseActivity;
import com.xieon.main.R;
public class QuizCompleteActivity extends BaseActivity {
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.quiz_complete);
	        
	        Button start = (Button)findViewById(R.id.quizStart);
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
