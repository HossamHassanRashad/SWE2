package com.FCI.SWE.UserModel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Controller.UserController;
import com.FCI.SWE.Models.UserEntity;

public class User {
	private String name;
	private String pass;
	private String email;
	private static User currentActiveUser;
	private long ID;
	private UserController userController;
	private User() {
		// TODO Auto-generated constructor stub
	}
	public static User getCurrentActiveUser(){
		return currentActiveUser;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public long getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public static void signout(){
		currentActiveUser = null;
	}
	public static void getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			currentActiveUser = new User();
			currentActiveUser.setName(object.get("name").toString());
			currentActiveUser.setEmail(object.get("email").toString());
			currentActiveUser.setPass(object.get("password").toString());
			currentActiveUser.setID(Long.parseLong(object.get("ID").toString()));
			//return new UserEntity(object.get("name").toString(), object.get(
			//		"email").toString(), object.get("password").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;

	}

}
