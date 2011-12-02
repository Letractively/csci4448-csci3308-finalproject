package com.csci.finalproject.agileassistant.client.rpc;

import com.csci.finalproject.agileassistant.client.LoginInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

}
