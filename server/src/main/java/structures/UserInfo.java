package structures;

import com.kosprov.jargon2.api.Jargon2;

import java.io.UnsupportedEncodingException;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;

public class UserInfo
{
    protected String userName;
    protected String userMail;

    public UserInfo(String userName, String userMail)
    {
        this.userName = userName;
        this.userMail = userMail;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserMail()
    {
        return userMail;
    }

    public void setUserMail(String userMail)
    {
        this.userMail = userMail;
    }
}
