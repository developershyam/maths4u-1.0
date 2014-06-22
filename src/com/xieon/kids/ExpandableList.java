package com.xieon.kids;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.xieon.main.BaseActivity;
import com.xieon.main.R;
import com.xieon.model.Topic;

public class ExpandableList extends BaseActivity {
	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> topicCollections;
	ExpandableListView expListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kids_home);
		Intent intent = getIntent();
		String json = intent.getStringExtra("json");
		System.out.println("****************\n*****\n*************"+json);
		createGroupList(json);

		expListView = (ExpandableListView) findViewById(R.id.kids_home_topic_list);
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
				this, groupList, topicCollections);
		expListView.setAdapter(expListAdapter);
		expListView.expandGroup(0);
		// setGroupIndicatorToRight();

		/*
		 * expListView.setOnChildClickListener(new OnChildClickListener() {
		 * 
		 * public boolean onChildClick(ExpandableListView parent, View v, int
		 * groupPosition, int childPosition, long id) { final String selected =
		 * (String) expListAdapter.getChild( groupPosition, childPosition);
		 * Toast.makeText(getBaseContext(), "Timepass  "+selected,
		 * Toast.LENGTH_LONG) .show();
		 * System.out.println("****************\n*****\n*************"); return
		 * true; } });
		 */
	}

	private void createGroupList(String json) {

		try {
			JSONArray jsonArray = new JSONArray(json);
			List<Topic> topics = new ArrayList<Topic>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Topic topic = new Topic();
				topic.setNum(jsonObject.getString("num"));
				topic.setTopic(jsonObject.getString("topic"));
				JSONArray subTopicsArray = jsonObject
						.getJSONArray(("subtopic"));
				List<String> subTopics = new ArrayList<String>();
				for (int j = 0; j < subTopicsArray.length(); j++) {
					JSONObject subTopicObject = subTopicsArray.getJSONObject(j);
					String subTopic = subTopicObject.getString("subtopic"
							+ (j + 1));
					subTopics.add(subTopic);
				}
				topic.setSubtopic(subTopics);
				topics.add(topic);
			}
			groupList = new ArrayList<String>();
			topicCollections = new LinkedHashMap<String, List<String>>();
			for (Topic topic : topics) {
				groupList.add(topic.getTopic());

				childList = new ArrayList<String>();
				List<String> subTopics = topic.getSubtopic();
				for (String subTopic : subTopics) {
					childList.add(subTopic);
				}
				topicCollections.put(topic.getTopic(), childList);

			}
			System.out.println("List of Topics: " + topics);
		} catch (Exception e) {
			System.out.println("Exception: while loading InputActivity#Topics "
					+ e);
		}
	}

	/*
	 * private void createCollection() { // preparing laptops collection(child)
	 * String[] hpModels = { "HP Pavilion G6-2014TX", "ProBook HP 4540",
	 * "HP Envy 4-1025TX" }; String[] hclModels = { "HCL S2101", "HCL L2102",
	 * "HCL V2002" }; String[] lenovoModels = { "IdeaPad Z Series",
	 * "Essential G Series", "ThinkPad X Series", "Ideapad Z Series" }; String[]
	 * sonyModels = { "VAIO E Series", "VAIO Z Series", "VAIO S Series",
	 * "VAIO YB Series" }; String[] dellModels = { "Inspiron", "Vostro", "XPS"
	 * }; String[] samsungModels = { "NP Series", "Series 5", "SF Series" };
	 * 
	 * laptopCollection = new LinkedHashMap<String, List<String>>();
	 * 
	 * for (String laptop : groupList) { if (laptop.equals("HP")) {
	 * loadChild(hpModels); } else if (laptop.equals("Dell"))
	 * loadChild(dellModels); else if (laptop.equals("Sony"))
	 * loadChild(sonyModels); else if (laptop.equals("HCL"))
	 * loadChild(hclModels); else if (laptop.equals("Samsung"))
	 * loadChild(samsungModels); else loadChild(lenovoModels);
	 * 
	 * laptopCollection.put(laptop, childList); } }
	 * 
	 * private void loadChild(String[] laptopModels) { childList = new
	 * ArrayList<String>(); for (String model : laptopModels)
	 * childList.add(model); }
	 */

	/*
	 * private void setGroupIndicatorToRight() { Get the screen width
	 * DisplayMetrics dm = new DisplayMetrics();
	 * getWindowManager().getDefaultDisplay().getMetrics(dm); int width =
	 * dm.widthPixels;
	 * 
	 * expListView.setIndicatorBounds(width - getDipsFromPixel(35), width -
	 * getDipsFromPixel(5)); }
	 */

	// Convert pixel to dip
	public int getDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}
}
