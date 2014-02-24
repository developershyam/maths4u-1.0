package com.xieon.model;

import java.util.List;

public class TheoryContent2 {

	String num;
	String topic;
	List<String> contents;
	List<String> examples;
	List<String> practiceQuestions;

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

	
	public List<String> getExamples() {
		return examples;
	}

	public void setExamples(List<String> examples) {
		this.examples = examples;
	}

	public List<String> getPracticeQuestions() {
		return practiceQuestions;
	}

	public void setPracticeQuestions(List<String> practiceQuestions) {
		this.practiceQuestions = practiceQuestions;
	}

	@Override
	public String toString() {
		return "TheoryContent2 [num=" + num + ", topic=" + topic
				+ ", contents=" + contents + ", examples=" + examples
				+ ", practiceQuestions=" + practiceQuestions + "]";
	}

	

}
