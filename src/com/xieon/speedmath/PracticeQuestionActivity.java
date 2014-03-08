package com.xieon.speedmath;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.xieon.main.BaseActivity;
import com.xieon.main.Level_0_ListAdapter;
import com.xieon.main.R;
import com.xieon.model.PracticeQuestion;
import com.xieon.model.TheoryContent;
import com.xieon.utility.AppUtility;

public class PracticeQuestionActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speedmath_practice_home);
		List<PracticeQuestion> listdata=new ArrayList<PracticeQuestion>();
		PracticeQuestionAdapter adapter = new PracticeQuestionAdapter(this, listdata);
		final ListView listview = (ListView) findViewById(R.id.speedmath_practice_list);
		listview.setAdapter(adapter);
	}

	
}
