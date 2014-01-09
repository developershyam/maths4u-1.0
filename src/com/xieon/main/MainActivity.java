package com.xieon.main;

import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.xieon.constant.AppConstants;
import com.xieon.level_1.Level_1_HeadActivity;
import com.xieon.utility.AppUtility;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainActivity extends BaseActivity {

	// List items
	// ListView list;
	Level_0_ListAdapter adapter = null;
	List<HashMap<String, String>> listdata;
	String headerURL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listdata = getListdata();
		adapter = new Level_0_ListAdapter(this, listdata);

		final ListView listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				//Drawable d1=view.getBackground();
				//Drawable d2 = view.getContext().getApplicationContext()
					//	.getResources().getDrawable(R.drawable.actionbar_img);

				//view.setBackground(d2);
				//view.animate().alpha(0);
				
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Level_1_HeadActivity.class);

				// parameters
				intent.putExtra(AppConstants.LIST_POSITION, position);
				intent.putExtra(AppConstants.ID,
						listdata.get(position).get(AppConstants.ID));
				intent.putExtra(
						AppConstants.HEADER_URL,
						AppUtility.shrink(listdata.get(position).get(
								AppConstants.HEAD_TEXT)));
				

				// start the  activity
				startActivity(intent);
				
			}
		});

	}

}
