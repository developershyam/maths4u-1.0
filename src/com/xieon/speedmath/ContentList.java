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

import com.xieon.main.BaseActivity;
import com.xieon.main.R;
import com.xieon.model.TheoryContent;
import com.xieon.utility.AppUtility;

public class ContentList extends BaseActivity {

	int current = 0;
	int currentTopic = 0;
	int maxTopic = 0;
	int groupPosition = 0;
	int childPosition = 0;
	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> topicCollections;
	ExpandableListView expListView;
	List<TheoryContent> theoryContents;
	ContentList contentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contentList = this;
		Intent intent = getIntent();
		groupPosition = intent.getIntExtra("groupPosition", 0);
		childPosition = intent.getIntExtra("childPosition", 0);
		currentTopic = childPosition;
		setContentView(R.layout.speedmath_home);
		theoryContents = getTheoryContent();
		maxTopic = theoryContents.size();
		createGroupList();
		expListView = (ExpandableListView) findViewById(R.id.speedmath_home_topic_list);
		final ContentListAdapter expListAdapter = new ContentListAdapter(
				contentList, groupList, topicCollections);
		expListView.setAdapter(expListAdapter);
		expListView.expandGroup(0);
		// setGroupIndicatorToRight();
		final AlphaAnimation animation = new AlphaAnimation(1F, 0.2F);
		ImageView nextButton = (ImageView) findViewById(R.id.speedmath_home_next);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (currentTopic < maxTopic - 1) {
					v.startAnimation(animation);
					currentTopic++;
					chageTopic();
				}

			}
		});
		ImageView backButton = (ImageView) findViewById(R.id.speedmath_home_back);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (currentTopic > 0) {
					v.startAnimation(animation);
					currentTopic--;
					chageTopic();
				}

			}
		});
	}

	private void createGroupList() {

		try {

			groupList = new ArrayList<String>();
			topicCollections = new LinkedHashMap<String, List<String>>();

			TheoryContent theoryContent = theoryContents.get(currentTopic);

			groupList.add(theoryContent.getNum() + ". "
					+ theoryContent.getTopic());
			topicCollections.put(theoryContent.getNum() + ". "
					+ theoryContent.getTopic(), theoryContent.getContents());

			groupList.add("Examples");
			topicCollections.put("Examples", theoryContent.getExamples());

			groupList.add("Practice Questions");
			topicCollections.put("Practice Questions",
					theoryContent.getPracticeQuestions());

		} catch (Exception e) {
			System.out.println("Exception: while loading InputActivity#Topics "
					+ e);
		}
	}

	// Convert pixel to dip
	public int getDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}

	private List<TheoryContent> getTheoryContent() {

		System.out.println("groupPosition="+groupPosition);
		List<TheoryContent> theoryContents = new ArrayList<TheoryContent>();
		String json="";
		if(groupPosition==0){
			json = AppUtility.loadJSON(getBaseContext(),
					"speedmath_multiplication.json");
		}else if(groupPosition==1){
			json = AppUtility.loadJSON(getBaseContext(),
					"speedmath_square.json");
		}else if(groupPosition==2){
			json = AppUtility.loadJSON(getBaseContext(),
					"speedmath_addition.json");
		}else if(groupPosition==3){
			json = AppUtility.loadJSON(getBaseContext(),
					"speedmath_divisibility.json");
		}else if(groupPosition==4){
			json = AppUtility.loadJSON(getBaseContext(),
					"speedmath_miscellaneous.json");
		}else{
			json = AppUtility.loadJSON(getBaseContext(),
					"speedmath_multiplication.json");
		}
		
		
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				TheoryContent theoryContent = new TheoryContent();
				theoryContent.setNum(jsonObject.getString("num"));
				theoryContent.setTopic(jsonObject.getString("topic"));
				JSONArray contentArray = jsonObject.getJSONArray(("contents"));
				List<String> contents = new ArrayList<String>();
				for (int j = 0; j < contentArray.length(); j++) {
					JSONObject contentObject = contentArray.getJSONObject(j);
					String content = contentObject.getString("content"
							+ (j + 1));
					contents.add(content);
				}
				theoryContent.setContents(contents);
				JSONArray exampleArray = jsonObject.getJSONArray(("examples"));
				List<String> examples = new ArrayList<String>();
				for (int j = 0; j < exampleArray.length(); j++) {
					JSONObject exampleObject = exampleArray.getJSONObject(j);
					String example = exampleObject.getString("example"
							+ (j + 1));
					examples.add(example);
				}
				theoryContent.setExamples(examples);
				JSONArray practiceArray = jsonObject
						.getJSONArray(("practices"));
				List<String> practices = new ArrayList<String>();
				for (int j = 0; j < practiceArray.length(); j++) {
					JSONObject practiceObject = practiceArray.getJSONObject(j);
					String practice = practiceObject.getString("practice"
							+ (j + 1));
					practices.add(practice);
				}
				theoryContent.setPracticeQuestions(practices);
				theoryContents.add(theoryContent);
			}
		} catch (Exception e) {
			System.out.println("Exception in getTheoryContent: " + e);
		}
		return theoryContents;
	}

	private void chageTopic() {
		groupList = new ArrayList<String>();
		topicCollections = new LinkedHashMap<String, List<String>>();

		TheoryContent theoryContent = theoryContents.get(currentTopic);

		groupList.add(theoryContent.getNum() + ". "
				+ theoryContent.getTopic());
		topicCollections.put(
				theoryContent.getNum() + ". " + theoryContent.getTopic(),
				theoryContent.getContents());

		groupList.add("Examples");
		topicCollections.put("Examples", theoryContent.getExamples());

		groupList.add("Practice Questions");
		topicCollections.put("Practice Questions",
				theoryContent.getPracticeQuestions());

		expListView = (ExpandableListView) findViewById(R.id.speedmath_home_topic_list);
		final ContentListAdapter expListAdapter = new ContentListAdapter(
				contentList, groupList, topicCollections);
		expListView.setAdapter(expListAdapter);
		expListView.expandGroup(0);
	}
}
