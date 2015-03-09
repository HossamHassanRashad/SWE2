package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.UserEntity;
import com.FCI.SWE.UserModel.User;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class Service {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	
	@POST
	@Path("/sendRequest")
	public String sendRequest(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	
	@POST
	@Path("/searchFriend")
	public String searchService( @FormParam("myemail") String myemail, @FormParam("email") String email) {
		JSONObject object = new JSONObject();
		//User.getUser(fname);
		UserEntity user = UserEntity.getSearch(myemail,email);
		System.out.println("returned object"+ user.getEmail());
		if (user.equals(null)) {
			object.put("Status", "Failed");

		} else{
		object.put("Status", "OK");
		object.put("name", user.getName());
		object.put("email", user.getEmail());
		object.put("myemail", myemail);
		}
		return object.toString();
	}
	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("ID", user.getID());
		}

		return object.toString();

	}
	@POST
	@Path("/sendRq")
	public String sendRq(@FormParam("myemail") String myemail ,@FormParam("email") String email) {
		System.out.println("email from searvice "+email);
		JSONObject object = new JSONObject();
		Boolean added= UserEntity.saveRequest(myemail,email,"pending");
		if (added.equals(null)||added.equals(false)) {
			object.put("Status", "Failed");
			System.out.println("false from DB in service");
		}else{
		System.out.println("d5l l else");
		object.put("Status", "OK");
		object.put("email", email);
		object.put("myemail", myemail);
		}
		return object.toString();
	}
	@POST
	@Path("/checkRq")
	public String checkRq(@FormParam("myemail") String myemail) {
		JSONObject object = new JSONObject();
		String checked= UserEntity.checkRequest(myemail);
		System.out.println("checkRq service");
		if (checked.equals(null)) {
			object.put("Status", "Failed");

		} else{
		object.put("Status", "OK");
		object.put("email", checked);
		object.put("myemail", myemail);
		}
		return object.toString();
	}
	@POST
	@Path("/acceptRq")
	public String acceptRq(@FormParam("myemail") String myemail) {
		JSONObject object = new JSONObject();
		String accepted= UserEntity.acceptRequest(myemail);
		System.out.println("acceptRq service");
		if (accepted.equals(null)) {
			System.out.println("l DB rgg3t null");
			object.put("Status", "Failed");

		} else{
		System.out.println("l DB rgg3t email");
		object.put("Status", "OK");
		object.put("email", accepted);
		object.put("myemail", myemail);
		}
		return object.toString();
	}
}