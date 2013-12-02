package com.example.math4u_1;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Level_1_HeadActivity extends BaseActivity {

	List<HashMap<String, String>> listdata;
	String headerURL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_1);
		int listPosition = getIntent().getIntExtra(AppConstants.LIST_POSITION,
				0);
		String id = getIntent().getStringExtra(AppConstants.ID);
		headerURL = getIntent().getStringExtra(AppConstants.HEADER_URL);
		headerURL = AppUtility.addHeaderURLSuffix(headerURL);
		listdata = getListdata();
		TextView headText = (TextView) findViewById(R.id.level_1_head_text);
		TextView headerUrlText = (TextView) findViewById(R.id.level_1_header_url);

		headText.setText(listdata.get(listPosition).get(AppConstants.HEAD_TEXT));

		headerUrlText.setText(Html.fromHtml(AppUtility
				.createHeaderURL(headerURL)));

	}

}
