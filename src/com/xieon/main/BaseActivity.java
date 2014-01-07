package com.xieon.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.xieon.constant.AppConstants;
import com.xieon.level_1.Level_1_HeadActivity;
import com.xieon.quiz.InputActivity;
import com.xieon.utility.AppUtility;

public class BaseActivity extends Activity {

	private List<HashMap<String, String>> listdata;

	public List<HashMap<String, String>> getListdata() {
		setListData();
		return listdata;
	}

	public void setListdata(List<HashMap<String, String>> listdata) {
		this.listdata = listdata;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		
		inflater.inflate(R.menu.mainmenu, menu);
		Drawable d = getResources().getDrawable(R.drawable.actionbar_img);
		getActionBar().setBackgroundDrawable(d);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menuItemHome:
			setHome();
			return true;
		case R.id.menuItem0:
			handleClickItem(0);
			return true;
		case R.id.menuItem1:
			handleClickItem(1);
			return true;
		case R.id.menuItem2:
			handleClickItem(2);
			return true;
		case R.id.menuItem3:
			handleClickItem(3);
			return true;
		case R.id.menuItem4:
			startQuiz(4);
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void setHome() {

		Intent intent = new Intent();
		intent.setClass(getBaseContext(), MainActivity.class);

		startActivity(intent);
	}

	private void handleClickItem(int position) {

		Intent intent = new Intent();
		intent.setClass(getBaseContext(), Level_1_HeadActivity.class);

		// parameters
		intent.putExtra(AppConstants.LIST_POSITION, position);
		intent.putExtra(AppConstants.ID,
				listdata.get(position).get(AppConstants.ID));
		intent.putExtra(
				AppConstants.HEADER_URL,
				AppUtility.shrink(listdata.get(position).get(
						AppConstants.HEAD_TEXT)));
		startActivity(intent);
	}

	private void setListData() {
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(getAssets().open(
					AppConstants.LEVEL_0_LIST_DATA_LOC));

			listdata = new ArrayList<HashMap<String, String>>();

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList mainNodeList = doc
					.getElementsByTagName(AppConstants.LIST_DATA);

			HashMap<String, String> map = null;

			for (int i = 0; i < mainNodeList.getLength(); i++) {

				map = new HashMap<String, String>();

				Node firstNode = mainNodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstElement = (Element) firstNode;
					// ID-------
					NodeList idList = firstElement
							.getElementsByTagName(AppConstants.ID);
					Element firstIdElement = (Element) idList.item(0);
					NodeList textIdList = firstIdElement.getChildNodes();
					map.put(AppConstants.ID,
							((Node) textIdList.item(0)).getNodeValue());

					// HEAD_TEXT-------
					NodeList headTextList = firstElement
							.getElementsByTagName(AppConstants.HEAD_TEXT);
					Element headTextElement = (Element) headTextList.item(0);
					NodeList childHeadTextList = headTextElement
							.getChildNodes();
					map.put(AppConstants.HEAD_TEXT,
							((Node) childHeadTextList.item(0)).getNodeValue());
					// LEFT_TEXT-------
					NodeList leftTextList = firstElement
							.getElementsByTagName(AppConstants.LEFT_TEXT);
					Element leftTextElement = (Element) leftTextList.item(0);
					NodeList childLeftTextList = leftTextElement
							.getChildNodes();

					map.put(AppConstants.LEFT_TEXT,
							((Node) childLeftTextList.item(0)).getNodeValue());

					// RIGHT_TEXT-------
					NodeList rightTextList = firstElement
							.getElementsByTagName(AppConstants.RIGHT_TEXT);
					Element rightTextElement = (Element) rightTextList.item(0);
					NodeList childRightTextList = rightTextElement
							.getChildNodes();
					map.put(AppConstants.RIGHT_TEXT,
							((Node) childRightTextList.item(0)).getNodeValue());

					// HEAD_IMG-------
					NodeList headImgList = firstElement
							.getElementsByTagName(AppConstants.HEAD_IMG);
					Element headImgElement = (Element) headImgList.item(0);
					NodeList childHeadImgList = headImgElement.getChildNodes();

					map.put(AppConstants.HEAD_IMG,
							((Node) childHeadImgList.item(0)).getNodeValue());

					// TAIL_IMG-------
					NodeList tailImgList = firstElement
							.getElementsByTagName(AppConstants.TAIL_IMG);
					Element tailImgElement = (Element) tailImgList.item(0);
					NodeList childTailImgList = tailImgElement.getChildNodes();

					map.put(AppConstants.TAIL_IMG,
							((Node) childTailImgList.item(0)).getNodeValue());

					listdata.add(map);
				}
			}

		}

		catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void startQuiz(int position) {

		Intent intent = new Intent();
		intent.setClass(getBaseContext(), InputActivity.class);

		
		startActivity(intent);
	}

}
