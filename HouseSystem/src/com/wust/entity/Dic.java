package com.wust.entity;

public class Dic {
	private String code;
	private String text;
	private String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public String toString() {
		return this.code+"\t"+this.text+"\t"+this.type;
	}

}
