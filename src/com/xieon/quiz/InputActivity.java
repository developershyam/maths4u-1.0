package com.xieon.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.xieon.main.BaseActivity;
import com.xieon.main.R;
import com.xieon.model.User;
import com.xieon.utility.AppUtility;

public class InputActivity extends BaseActivity {
	
	User user;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.quiz_input);
	        final Spinner dropdown = (Spinner)findViewById(R.id.quizLevel);
	        String[] items = new String[]{"Select Level","Low Level", "Medium Level", "High Level"};
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
	        dropdown.setAdapter(adapter);
	        TextView editName = (TextView)findViewById(R.id.editName);
	        TextView editAge = (TextView)findViewById(R.id.editAge);
	        Intent intent=getIntent();
	        user=(User)intent.getSerializableExtra("user");
	        if(user!=null){	        	
	        	editName.setText(user.getUserName());
	        	editAge.setText(user.getAge().toString());
	        	if(user.getLevel().equals("Low Level")){
	        		dropdown.setSelection(1);
	        	}else if(user.getLevel().equals("Medium Level")){
	        		dropdown.setSelection(2);
	        	}else if(user.getLevel().equals("High Level")){
	        		dropdown.setSelection(3);
	        	}
	        }
	        else{
	        	user=new User();
	        }
	        	
	        
	        Button start = (Button)findViewById(R.id.quizStart);
	        
	        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
	        start.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click   
                	v.startAnimation(buttonClick);
                	Intent intent = new Intent();
                	EditText userName = (EditText)findViewById(R.id.editName);
                	EditText age = (EditText)findViewById(R.id.editAge);                	
                	user.setUserName(userName.getText().toString());
                	user.setAge(Long.valueOf(age.getText().toString()));
                	user.setLevel(dropdown.getSelectedItem().toString());
            		intent.setClass(getBaseContext(), QuestionActivity.class);
            		String json=AppUtility.loadJSON(getContext(),"question.json");
            		intent.putExtra("questions", json);
            		intent.putExtra("user", user);
            		startActivity(intent);
                }
            });
	 }
	
}
