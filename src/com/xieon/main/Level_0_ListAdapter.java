package com.xieon.main;

import java.util.HashMap;
import java.util.List;

import com.example.math4u_1.R;
import com.xieon.constant.AppConstants;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Level_0_ListAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<HashMap<String, String>> listData;
	ViewHolder holder;

	public Level_0_ListAdapter() {
		// TODO Auto-generated constructor stub
	}

	public Level_0_ListAdapter(Activity act, List<HashMap<String, String>> map) {

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

			vi = inflater.inflate(R.layout.level_0, null);
			holder = new ViewHolder();

			holder.headText = (TextView) vi
					.findViewById(R.id.level_0_head_text);
			holder.leftText = (TextView) vi
					.findViewById(R.id.level_0_left_text);
			holder.rightText = (TextView) vi
					.findViewById(R.id.level_0_right_text);
			holder.headImg = (ImageView) vi.findViewById(R.id.level_0_head_img);
			holder.tailImg = (ImageView) vi.findViewById(R.id.level_0_tail_img);

			vi.setTag(holder);
		} else {

			holder = (ViewHolder) vi.getTag();
		}
		final View vifinal=vi;

		// Setting all values in listview
		holder.headText.setText(listData.get(position).get(
				AppConstants.HEAD_TEXT));
		holder.leftText.setText(listData.get(position).get(
				AppConstants.LEFT_TEXT));
		holder.rightText.setText(listData.get(position).get(
				AppConstants.RIGHT_TEXT));
		// Setting an head image
		String uri = AppConstants.DRAWABLE + "/"
				+ listData.get(position).get(AppConstants.HEAD_IMG);
		int imageResource = vi
				.getContext()
				.getApplicationContext()
				.getResources()
				.getIdentifier(
						uri,
						null,
						vi.getContext().getApplicationContext()
								.getPackageName());
		Drawable image = vi.getContext().getResources()
				.getDrawable(imageResource);
		holder.headImg.setImageDrawable(image);

		// Setting an head image
		uri = AppConstants.DRAWABLE + "/"
				+ listData.get(position).get(AppConstants.TAIL_IMG);
		imageResource = vi
				.getContext()
				.getApplicationContext()
				.getResources()
				.getIdentifier(
						uri,
						null,
						vi.getContext().getApplicationContext()
								.getPackageName());
		image = vi.getContext().getResources().getDrawable(imageResource);
		holder.tailImg.setImageDrawable(image);
		return vi;
	}

	static class ViewHolder {

		TextView headText;
		TextView leftText;
		TextView rightText;
		ImageView headImg;
		ImageView tailImg;

	}
}