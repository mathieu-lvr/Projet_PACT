package com.e.network;

import com.e.structures.IAuthentificationData;
import com.e.structures.UserInfo;
import com.e.structures.UserSecuredInfo;

import java.io.IOException;

public interface IServerInterface
{

    public void connect(IAuthentificationData data) throws Exception;
    public int getCredits(int userID) throws IOException, HTTPError;
    public UserInfo getUserInfo(int userID) throws Exception;
    public void setUserInfo(UserInfo info);
    public void setPassword(String password);
    public void createAccount(UserSecuredInfo securedInfos) throws Exception;
    /*
    public void buyCredits();
    */
}
