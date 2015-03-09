package com.FCI.SWE.Models;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.UserModel.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	private String name;
	private String email;
	private String password;
	private long ID;
	
	
	
	
	

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;

	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	public long getID() {
		return ID;
	}
	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */

	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}

		return null;
	}
	public static UserEntity getSearch(String myemail, String email){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.println("d5l search");
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println("tmam");
			if (entity.getProperty("email").toString().equals(email)) {
				System.out.println("l2ah fel DB");
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}
		System.out.println("ml2ash 7aga fel search");
		return null;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		datastore.put(employee);

		return true;

	}
	
	public static Boolean saveRequest(String myemail,String email,String Rqstatus) {
		if(myemail.equals(email)){
			System.out.println("mynf34 tdef nfsk");
			return false;
		}
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery1 = new Query("Request");
		PreparedQuery pq1 = datastore.prepare(gaeQuery1);
		for (Entity employeee : pq1.asIterable()) {
			System.out.println("d5l l looop");
			if(employeee.getProperty("friendemail").toString().equals(email)
					&&employeee.getProperty("mymail").toString().equals(myemail)
						&&employeee.getProperty("Rqstatus").toString().equals("accepted")){
				System.out.println("already friends");
				return false;
			}
			else if(employeee.getProperty("friendemail").toString().equals(email)
					&&employeee.getProperty("mymail").toString().equals(myemail)
					&&employeee.getProperty("Rqstatus").toString().equals("pending")){
			System.out.println("already sent request");
			return false;
			}
			System.out.println("mfesh false");
//			}else if(employeee.getProperty("friendemail").toString().equals(myemail)
//					&&employeee.getProperty("myemail").toString().equals(email)
//					&&employeee.getProperty("Rqstatus").toString().equals("accepted")){
//			System.out.println("already friends");
//			return false;
//			}else if(employeee.getProperty("friendemail").toString().equals(myemail)
//					&&employeee.getProperty("myemail").toString().equals(email)
//					&&employeee.getProperty("Rqstatus").toString().equals("pending")){
//			System.out.println("he sent you friend request");
//			return false;
//			}
		}
			System.out.println("hydef 3ady");
			Query gaeQuery = new Query("Request");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity employee = new Entity("Request", list.size() + 1);
			employee.setProperty("mymail", myemail);
			employee.setProperty("friendemail", email);
			employee.setProperty("Rqstatus", Rqstatus);
			datastore.put(employee);
			return true;
	}
	public static String checkRequest(String myemail){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.println("checkRequest Entity");
		Query gaeQuery = new Query("Request");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			//System.out.println(entity.getProperty("name").toString());
			if ((entity.getProperty("friendemail").toString().equals(myemail))
					&& entity.getProperty("Rqstatus").equals("pending")) {
				String sender = entity.getProperty("mymail").toString();
				System.out.println("sender is " +entity.getProperty("mymail").toString());
				return sender;
			}
//			else if(entity.getProperty("myemail").toString().equals(myemail)
//					&& entity.getProperty("Rqstatus").equals("pending")) {
//				String sender = entity.getProperty("friendemail").toString();
//				//System.out.println("sender is " +entity.getProperty("mymail").toString());
//				return sender;
//			}
		}

		return null;
	}
	public static String acceptRequest(String myemail){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.println("acceptRequest Entity");
		Query gaeQuery = new Query("Request");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			//System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("friendemail").toString().equals(myemail)
					&& entity.getProperty("Rqstatus").equals("pending")) {
				System.out.println("l2ah fel db");
				entity.setProperty("Rqstatus","accepted");
				datastore.put(entity);
				String sender = entity.getProperty("mymail").toString();
				System.out.println("sender is " +entity.getProperty("mymail").toString());
				System.out.println("sender is " +entity.getProperty("Rqstatus").toString());
				return sender;
			}
		}
		System.out.println("DB fadya");
		return null;
	}
}
