package structures;

import network.security.UserService;

import java.io.UnsupportedEncodingException;

public class UserSecuredInfo extends UserInfo
{

    private String passwordHash;

    public UserSecuredInfo(String userName, String userMail, String password) throws UnsupportedEncodingException {
        super(userName, userMail);

        passwordHash = UserService.hashPassword(password);
    }

    public void setPassword(String password) throws UnsupportedEncodingException {
        passwordHash = UserService.hashPassword(password);
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }
}
