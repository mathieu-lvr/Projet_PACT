package com.e.structures;

public class Credentials implements IAuthentificationData
{

    private String _username;
    private String _password;

    public Credentials(String username, String password)
    {
        _username = username;
        _password = password;
    }

    @Override
    public String getPassword() {
        return _password;
    }

    @Override
    public String getUsername() {
        return _username;
    }

    @Override
    public String toString()
    {
        return "Credentials [username="
                + _username
                + ", password="
                + _password + "]";
    }
}
