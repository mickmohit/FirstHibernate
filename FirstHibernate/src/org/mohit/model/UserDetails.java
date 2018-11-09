package org.mohit.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.Type;

@Entity(name="USER_DETAIL")

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

@NamedQuery(name="UserDetails.byId", query="from USER_DETAIL where userId = ?")

@NamedNativeQuery(name = "UserDetails.byName", query = "select * from USER_DETAIL where USER_NAME=?", resultClass=UserDetails.class)

public class UserDetails {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Embedded
	@AttributeOverrides ({
	@AttributeOverride(name="street", column=@Column(name="HOME_STREET_NAME")),
	@AttributeOverride(name="city", column=@Column(name="HOME_CITY_NAME")),
	@AttributeOverride(name="state", column=@Column(name="HOME_STATE_NAME")),
	@AttributeOverride(name="pincode", column=@Column(name="HOME_PINCODE"))
	})
	
	private Address homeaddress;

	@Embedded
	private Address officeaddress;
	
	private Date joinedDate;
	
	/*private String address;*/
	

	private String description;
	
	@Embedded
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="USER_NICK_NAMES", joinColumns=@JoinColumn(name="USER_ID")
	)
	
	@GenericGenerator(name = "sequence-gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name="NAME_ID") }, generator = "sequence-gen", type = @Type(type="long"))
	private  Collection<Nick_Names> listOfNames = new ArrayList();
	
	/*public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}*/

	/*@OneToOne
	private Vechile vech;*/
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinTable(joinColumns=@JoinColumn(name="USER_ID"),
		inverseJoinColumns=@JoinColumn(name="VECHILE_ID")
			)
	private Collection<Vechile> vech = new ArrayList<Vechile>();
		
	
	
	/*public Vechile getVech() {
		return vech;
	}
	public void setVech(Vechile vech) {
		this.vech = vech;
	}*/
	
	public Collection<Vechile> getVech() {
		return vech;
	}
	public void setVech(Collection<Vechile> vech) {
		this.vech = vech;
	}
	
	
	
	public Collection<Nick_Names> getListOfNames() {
		return listOfNames;
	}
	public void setListOfNames(Set<Nick_Names> listOfNames) {
		this.listOfNames = listOfNames;
	}
	public String getDescription() {
		return description;
	}
	public Address getHomeaddress() {
		return homeaddress;
	}
	public void setHomeaddress(Address homeaddress) {
		this.homeaddress = homeaddress;
	}
	public Address getOfficeaddress() {
		return officeaddress;
	}
	public void setOfficeaddress(Address officeaddress) {
		this.officeaddress = officeaddress;
	}
	/*public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}*/
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
