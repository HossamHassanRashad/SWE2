package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class conversationSubject implements subject{
	ArrayList <observer>list;
	
	public void sendconv(String sender, String msg,String conversationName) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("convmsg");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity cm = new Entity("convmsg", list.size() + 1);

		cm.setProperty("sender", sender);
		cm.setProperty("msg", msg);
		cm.setProperty("conversationName", conversationName);
		datastore.put(cm);
		Notify(conversationName,sender);
	}
	
	public void createconv(String conversationName ,String myemail ,String emails[] ) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		conversationObservers coo = new conversationObservers();
		coo.setEmail(myemail);
		this.attach(coo);
		Query gaeQuery = new Query("convobservers");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity cm1 = new Entity("convobservers", list.size() + 1);
		cm1.setProperty("conversationName", conversationName);
		cm1.setProperty("email", myemail);
		datastore.put(cm1);
		for(String email : emails){
		Entity cm = new Entity("convobservers", list.size() + 1);
		cm.setProperty("conversationName", conversationName);
		cm.setProperty("email", email);
		datastore.put(cm);
		coo.setEmail(email);
		this.attach(coo);
		}
	}

	
	@Override
	public void attach(observer obj) {
		list.add(obj);
	}

	@Override
	public void Notify(String convname,String sender ) {
		for(observer o : list){
			o.update(convname,sender);
		}
	}

}
