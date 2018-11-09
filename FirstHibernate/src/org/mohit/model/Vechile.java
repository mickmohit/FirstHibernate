package org.mohit.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
/*@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="VECHILE_TYPE", discriminatorType=DiscriminatorType.STRING
		)*/
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public class Vechile {

	@Id @GeneratedValue
	private int vechileID;
	private String vechileName;

	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private UserDetails userDetails;

	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public int getVechileID() {
		return vechileID;
	}
	public void setVechileID(int vechileID) {
		this.vechileID = vechileID;
	}
	public String getVechileName() {
		return vechileName;
	}
	public void setVechileName(String vechileName) {
		this.vechileName = vechileName;
	}
	
	
}
