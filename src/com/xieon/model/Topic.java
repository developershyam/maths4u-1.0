package com.xieon.model;

import java.util.List;

public class Topic {
	String num;
	String topic;
	List<String> subtopic;
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
	public List<String> getSubtopic() {
		return subtopic;
	}
	public void setSubtopic(List<String> subtopic) {
		this.subtopic = subtopic;
	}
	@Override
	public String toString() {
		return "Topic [num=" + num + ", topic=" + topic + ", subtopic="
				+ subtopic + "]";
	}
	
}
