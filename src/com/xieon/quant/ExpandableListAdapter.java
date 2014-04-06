package com.xieon.quant;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xieon.main.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity context;
	private Map<String, List<String>> topicCollections;
	private List<String> groupList;

	public ExpandableListAdapter(Activity context, List<String> groupList,
			Map<String, List<String>> topicCollections) {
		this.context = context;
		this.topicCollections = topicCollections;
		this.groupList = groupList;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return topicCollections.get(groupList.get(groupPosition)).get(
				childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String laptop = (String) getChild(groupPosition, childPosition);
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.child_text_arrow, null);
		}
		RelativeLayout relativeLayout = (RelativeLayout) convertView
				.findViewById(R.id.child_text_arrow);
		final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
		relativeLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				v.startAnimation(buttonClick);
				System.out.println("\n***position**\n");
				System.out.println("groupPosition=" + groupPosition
						+ ", childPosition=" + childPosition);
				Intent intent = new Intent();
				Context context = v.getContext();
				intent.putExtra("groupPosition", groupPosition);
				intent.putExtra("childPosition", childPosition);
				intent.setClass(context, com.xieon.quant.ContentList.class);
				context.startActivity(intent);
			}
		});

		TextView item = (TextView) convertView
				.findViewById(R.id.child_text_arrow_heading);

		/*
		 * ImageView delete = (ImageView) convertView
		 * .findViewById(R.id.child_text_arrow_arrow);
		 * delete.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View v) { AlertDialog.Builder builder = new
		 * AlertDialog.Builder(context);
		 * builder.setMessage("Do you want to remove?");
		 * builder.setCancelable(false); builder.setPositiveButton("Yes", new
		 * DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int id) { List<String> child =
		 * topicCollections .get(groupList.get(groupPosition));
		 * child.remove(childPosition); notifyDataSetChanged(); } });
		 * builder.setNegativeButton("No", new DialogInterface.OnClickListener()
		 * { public void onClick(DialogInterface dialog, int id) {
		 * dialog.cancel(); } }); AlertDialog alertDialog = builder.create();
		 * alertDialog.show(); } });
		 */

		item.setText(laptop);
		return convertView;
	}

	public int getChildrenCount(int groupPosition) {
		return topicCollections.get(groupList.get(groupPosition)).size();
	}

	public Object getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	public int getGroupCount() {
		return groupList.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String laptopName = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_text, null);
		}
		TextView item = (TextView) convertView
				.findViewById(R.id.group_text_head);
		item.setTypeface(null, Typeface.BOLD);
		item.setText(laptopName);
		return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}