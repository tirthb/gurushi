package com.gurushi.data.to;

import com.gurushi.data.Verse;

public class VerseMini {
	
	private String number;
	private String text;
	private String translation;
	
	public VerseMini(String number, String text, String translation) {
		this.number = number;
		this.text = text;
		this.translation = translation;
    }
	
	public VerseMini(Verse v) {
		this.number = v.getNumber();
		this.text = v.getText();
		this.translation = v.getTranslation() == null ? null : v.getTranslation().getText();
    }

	public String getNumber() {
		return number;
	}

	public String getText() {
		return text;
	}

	public String getTranslation() {
		return translation;
	}
	
}
