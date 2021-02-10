package structures;

import com.kosprov.jargon2.api.Jargon2;
import network.security.UserService;
import structures.IUser;

import javax.annotation.Resource;

import java.io.UnsupportedEncodingException;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;

public class User implements IUser {
	private int userId;
	private String userName;
	private String userMail;
	private String hashPassword;
	private int numberCredits;
	private String qrCode;

	public User(int userId, String userName, String userMail,
			String hashPassword, int numberCredits, String qrCode) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.hashPassword = hashPassword;
		this.numberCredits = numberCredits;
		this.qrCode = qrCode;
	}

	public void setPassword(String password) throws UnsupportedEncodingException {

		hashPassword = UserService.hashPassword(password);
	}

	public UserInfo getInfos()
	{
		return new UserInfo(userName, userMail);
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
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

	public String getHashPassword()
	{
		return hashPassword;
	}

	public int getNumberCredits()
	{
		return numberCredits;
	}

	public void setNumberCredits(int numberCredits)
	{
		this.numberCredits = numberCredits;
	}

	public String getQrCode()
	{
		return qrCode;
	}

	public void setQrCode(String qrCode)
	{
		this.qrCode = qrCode;
	}

}
