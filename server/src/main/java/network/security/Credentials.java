package network.security;

import structures.IAuthentificationData;

public class Credentials implements IAuthentificationData
{

    String username;
    String password;

    public Credentials(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

}
