package com.familytoto.familytotoProject.config;

public class XssFilter {
	private String contents;
	public XssFilter(String contents) {
		 this.contents = contents.replaceAll("<|/||!|?|html|head|title|meta|body|script|style|base|noscript|" +
		  "form|input|select|option|optgroup|textarea|button|label|fieldset|legend|iframe|embed|object|param|" +
		  "frameset|frame|noframes|basefont|applet| isindex|xmp|plaintext|listing|bgsound|marquee|blink|" +
		  "noembed|comment|xml)/i", "&lt;$3");
	}
	public String getContents() {
		return contents;
	}
}