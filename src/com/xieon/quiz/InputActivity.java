package com.xieon.quiz;

import java.io.InputStream;

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

public class InputActivity extends BaseActivity {
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.quiz);
	        Spinner dropdown = (Spinner)findViewById(R.id.quizLevel);
	        String[] items = new String[]{"Select Level","Low Level", "Medium Level", "High Level"};
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
	        dropdown.setAdapter(adapter);
	        Button start = (Button)findViewById(R.id.quizStart);
	        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
	        start.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click   
                	v.startAnimation(buttonClick);
                    System.out.println("Hi...............");
                }
            });
	 }
}
