package com.FCI.SWE.Models;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class MsgReplyCommand implements Command{
	String Sender,Result;
	private UserEntity obj;
	@Override
	public String excute(String myemail) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query gaeQuery1 = new Query("notifications");
			PreparedQuery pq1 = datastore.prepare(gaeQuery1);
			for(Entity noti:pq1.asIterable()){
				if(noti.getProperty("Reciever").toString().equals(myemail)
						&&noti.getProperty("Type").toString().equals("MsgReplyCommand")
						&&noti.getProperty("State").toString().equals("unread")){
//					noti.setProperty("State", "read");
//					datastore.put(noti);
					Sender = noti.getProperty("Sender").toString();
				 Result =	obj.showNewMsg(myemail, Sender);
				}
			}
			return Result;
	}

}
