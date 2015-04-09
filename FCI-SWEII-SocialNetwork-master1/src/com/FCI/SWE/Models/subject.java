package com.FCI.SWE.Models;

public interface subject {
	public abstract void attach(observer obj);
	public abstract void Notify(String convname,String Sender);
}
