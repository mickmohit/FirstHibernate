package org.mohit.model;

import javax.persistence.Embeddable;

@Embeddable
public class Nick_Names {

	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
