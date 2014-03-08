package com.xieon.speedmath;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xieon.main.R;
import com.xieon.model.PracticeQuestion;

public class PracticeQuestionAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<PracticeQuestion> listData;
	public PracticeQuestionAdapter() {
		// TODO Auto-generated constructor stub
	}

	public PracticeQuestionAdapter(Activity act, List<PracticeQuestion> map) {

		this.listData = map;

		inflater = (LayoutInflater) act
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		// return idlist.size();
		return listData.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		if (convertView == null) {

			vi = inflater.inflate(R.layout.speedmath_practice_question, null);
		}
		return vi;
	}
}