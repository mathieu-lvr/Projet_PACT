package com.e.structures;

public class UserSecuredInfo extends UserInfo
{

    private String password;

    public UserSecuredInfo(String userName, String userMail, String password)
    {
        super(userName, userMail);

        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}
