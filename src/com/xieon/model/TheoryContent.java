package com.xieon.model;

import java.util.List;

public class TheoryContent {

	String num;
	String topic;
	List<String> contents;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<String> getContents() {
		return contents;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "TheoryContent [num=" + num + ", topic=" + topic + ", contents="
				+ contents + "]";
	}

}
