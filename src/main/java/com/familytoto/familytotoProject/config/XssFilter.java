package com.familytoto.familytotoProject.config;

public class XssFilter {
	private String contents;
	public XssFilter(String contents) {
		 this.contents = contents.replaceAll("(?i)<script", "&lt;script");
		 contents = contents.replaceAll("(?i)<iframe", "&lt;iframe");
	}
	
	public String getContents() {
		return contents;
	}
}