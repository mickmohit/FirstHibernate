package org.mohit.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.mohit.model.Address;
import org.mohit.model.FourWheeler;
import org.mohit.model.Nick_Names;
import org.mohit.model.TwoWheeler;
import org.mohit.model.UserDetails;
import org.mohit.model.Vechile;

public class HibernateTest {

	public static void main(String[] args)
	{
		UserDetails ud = new UserDetails();
		
		Address add=new Address();
		add.setCity("Gurgaon");
		add.setPincode("122001");
		add.setState("Haryana");
		add.setStreet("HSBC");
		//ud.setUserId(2);
		
		Address add2=new Address();
		add2.setCity("Jaipur");
		add2.setPincode("122001");
		add2.setState("Rajasthan");
		add2.setStreet("Sector-4");
		
		ud.setUserName("Mukul-Second User");
		ud.setJoinedDate(new Date());
		/*ud.setAddress(add);*/
		ud.setHomeaddress(add);
		ud.setOfficeaddress(add2);
		ud.setDescription("Veer");
		
		
		Nick_Names nn1= new Nick_Names(); 
		nn1.setNickName("Paaji");
		
		Nick_Names nn2= new Nick_Names(); 
		nn2.setNickName("Veere");
		
		ud.getListOfNames().add(nn1);
		ud.getListOfNames().add(nn2);
		
		
		Vechile vech= new Vechile();
		vech.setVechileName("Car");
		
		Vechile vech2= new Vechile();
		vech2.setVechileName("Truck");
		
		/*ud.setVech(vech);*/
		
		ud.getVech().add(vech);
		ud.getVech().add(vech2);
		
		vech.setUserDetails(ud);
		vech2.setUserDetails(ud);
		
		
		TwoWheeler bike= new TwoWheeler();
		bike.setVechileName("Bike");
		bike.setSteeringHandle("Bike Steering Handle");
		
		FourWheeler car= new FourWheeler();
		car.setVechileName("Car");
		car.setSteeringWheel("Car Steering Wheel");
		
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory(); 
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		session.persist(ud);
		
		//session.save(ud);
				
		/*session.save(vech);
		
		session.save(vech2);*/
		
		session.save(car);
		session.save(bike);
		
		session.getTransaction().commit();
		
		System.out.println("fdfd");
		
	//	session.close();
		
		ud=null;
		 session= sessionFactory.openSession();
		 session.beginTransaction();
		ud = (UserDetails)session.get(UserDetails.class, 5);
		System.out.println("User name is:" +ud.getUserName());
		
		//session.delete(ud);
		
		ud.setUserName("Mohit New User");
		session.update(ud);
		
		session.getTransaction().commit();
		//session.close();
		System.out.println(ud.getListOfNames().size());
		
		session= sessionFactory.openSession();
		 session.beginTransaction();
		Query query=session.createQuery("from USER_DETAIL where userId> 5");
		
		int minId=5;
		String userName="Mukul-Second User";
		
		Query query_new=session.createQuery("select userName from USER_DETAIL where userId>"+minId);
		
		Query query_injection=session.createQuery("select userName from USER_DETAIL where userId> :userId and userName= :userName ");
		query_injection.setInteger("userId", minId);
		query_injection.setString("userName", userName);
		
		query.setFirstResult(8);
		query.setMaxResults(12);
		
		Query query_named=session.getNamedQuery("UserDetails.byId");
		query_named.setInteger(0, 5);
		
		Query query_namedNative=session.getNamedQuery("UserDetails.byName");
		query_namedNative.setString(0, "Mukul-Second User");
		
		Criteria criteria= session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.eq("userName", "Mukul-Second User"));
		
		/*EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		   EntityManager entitymanager = emfactory.createEntityManager( );
		
		CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();

		// Making The Query Object From The 'CriteriaBuilder' Instance
	        CriteriaQuery<Object> queryObj = criteriaBuilder.createQuery();
		    Root<UserDetails> from = queryObj.from(UserDetails.class);
		        
		    // Step #1 - Displaying All Records
		    System.out.println("\n! Display All Records For The 'Employee' Table !\n");
		    CriteriaQuery<Object> selectQuery = queryObj.select(from);
		    TypedQuery<Object> typedQuery = entitymanager.createQuery(selectQuery);
		    
		    List<Object> resultlist = typedQuery.getResultList();
		    
		    for(Object o:resultlist) {
		    	UserDetails e = (UserDetails)o;
		        System.out.println("UID : " + e.getUserId() + " Uname : " + e.getUserName());
		     }
		    
		    //Ordering the records 
		    System.out.println("Select all records by follow ordering");
		    CriteriaQuery<Object> select1 = queryObj.select(from);
		    select1.orderBy(criteriaBuilder.asc(from.get("ename")));
		    TypedQuery<Object> typedQuery1 = entitymanager.createQuery(select1);
		    List<Object> resultlist1 = typedQuery1.getResultList();

		    for(Object o:resultlist1){
		    	UserDetails e = (UserDetails)o;
		        System.out.println("UID : " + e.getUserId() + " Uname : " + e.getUserName());
		    }*/
		    
		List users=query.list();
		
		List<UserDetails> users_new=(List<UserDetails>)query.list();
		
		List<String> usersNames=(List<String>)query_new.list();
		
		List<String> usersNamesInjections=(List<String>)query_injection.list();
		
		List<UserDetails> users_named=(List<UserDetails>)query_named.list();
		
		List<UserDetails> users_namedNative=(List<UserDetails>)query_namedNative.list();
		
		List<UserDetails> users_criteria=(List<UserDetails>)criteria.list();
		
		session.getTransaction().commit();
		
		session.close();
		
		for(UserDetails u: users_criteria)
		{
			System.out.println("Criteria Query"+u.getUserName());
		}
		
		for(UserDetails u: users_namedNative)
		{
			System.out.println("Named Native Query"+u.getUserName());
		}
		
		
		for(UserDetails u: users_named)
		{
			System.out.println("Named Query"+u.getUserName());
		}
		
		
		for(String u: usersNamesInjections)
		{
			System.out.println(u);
		}
		
		for(String u: usersNames)
		{
			System.out.println(u);
		}

		for(UserDetails u: users_new)
		{
			System.out.println(u.getUserName());
		}
		
		System.out.println(users.size());
		
		
		
		Session session2= sessionFactory.openSession();
		 session2.beginTransaction();
		UserDetails ud2 = (UserDetails)session2.get(UserDetails.class, 5);
		
		session2.getTransaction().commit();
		
		session2.close();
	}
}
