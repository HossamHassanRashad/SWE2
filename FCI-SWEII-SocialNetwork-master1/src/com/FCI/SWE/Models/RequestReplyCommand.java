package com.FCI.SWE.Models;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class RequestReplyCommand implements Command{
	String result,Sender;
	private UserEntity obj;
	@Override
	public String excute(String myemail) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			
			Query gaeQuery1 = new Query("notifications");
			PreparedQuery pq1 = datastore.prepare(gaeQuery1);
			for (Entity noti1 : pq1.asIterable()){
				if(noti1.getProperty("Reciever").toString().equals(myemail)
						&&noti1.getProperty("Type").toString().equals("RequestReplyCommand")
						&&noti1.getProperty("State").toString().equals("unread")){
					//Sender = noti1.getProperty("Sender").toString();
//					noti1.setProperty("State", "read");
//					datastore.put(noti1);
					result = obj.checkRequest(myemail);
				}
			}
			return result;
	}
}
