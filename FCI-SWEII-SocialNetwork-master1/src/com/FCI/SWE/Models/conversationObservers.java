package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class conversationObservers implements observer{
String email;
String groupID;
public void setEmail(String email) {
	this.email = email;
}
public String getEmail() {
	return email;
}
public void setGroupID(String groupID) {
	this.groupID = groupID;
}
public String getGroupID() {
	return groupID;
}
public boolean addunseenmessege(String convname,String sender){
	String reciever;
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("seenmsg");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
	
	Query gaeQuery2 = new Query("convobservers");
	PreparedQuery pq2 = datastore.prepare(gaeQuery2);
	
	for(Entity co : pq2.asIterable()){
		if(co.getProperty("conversationName").toString().equals(convname)
				
				&&!co.getProperty("email").equals(sender)){
			reciever = co.getProperty("email").toString();
			Entity cm = new Entity("seenmsg", list.size() + 1);
			cm.setProperty("conversationName", convname);
			cm.setProperty("seen", "no");
			cm.setProperty("reciever", reciever);
			datastore.put(cm);
		}
	}
	return true;
}


public boolean seemessege(String convname,String email){
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("seenmsg");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	for(Entity co : pq.asIterable()){
		if(co.getProperty("conversationName").toString().equals(convname)
				&&co.getProperty("email").toString().equals(email)){
			co.setProperty("seen", "yes");
			datastore.put(co);
		}
	}
	return true;
}
@Override
public void update(String convname,String sender) {
	this.addunseenmessege(convname,sender);
}

}
