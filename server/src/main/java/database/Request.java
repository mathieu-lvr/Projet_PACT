package database;

import structures.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Request implements IDataAccessor {
	private Connexion connexion;
	
	public Request(Connexion connexion){
		this.connexion = connexion;
	}
	
	
	//REQUEST FOR USER
	public IUser getUserById(int userID){
		String sql = "SELECT * FROM Users WHERE userId=" + userID;
		ResultSet resultat = connexion.query(sql); 
		User user = null;
		try {
			user = new User(resultat.getInt(1),
					resultat.getString(2),
					resultat.getString(3),
					resultat.getString(4),
					resultat.getInt(5),
					resultat.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
		return user;
	}

	//REQUEST FOR USER
	public User isQRCodeValid(String QR){
		String sql = "SELECT * FROM Users WHERE QRcode='" + QR+ "'";
		ResultSet resultat = connexion.query(sql);
		User user = null;
		try {
			user = new User(resultat.getInt(1),
					resultat.getString(2),
					resultat.getString(3),
					resultat.getString(4),
					resultat.getInt(5),
					resultat.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	/* IMPORTANT */
	@Override
	public IUser getUserByCredentials(String username) {
		String sql = "SELECT * FROM Users WHERE userName='" + username + "'";
		ResultSet resultat = connexion.query(sql);
		User user = null;

		if(resultat == null)
		{
			return null;
		}

		try {
			user = new User(resultat.getInt(1),
					resultat.getString(2),
					resultat.getString(3),
					resultat.getString(4),
					resultat.getInt(5),
					resultat.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
		return user;
	}

	@Override
    public IUser getUserByMail(String userMail){
		String sql = "SELECT * FROM Users WHERE userMail='" + userMail + "'";
		ResultSet resultat = connexion.query(sql); 
		User user = null;
		try {
			user = new User(resultat.getInt(1),
					resultat.getString(2),
					resultat.getString(3),
					resultat.getString(4),
					resultat.getInt(5),
					resultat.getString(6));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
		return user;
	}

	/*IMPORTANT*/
	@Override
	public String getConnectionTokenByUserId(int userID) {
		String sql = "SELECT token FROM Users WHERE userId=" + userID;
		ResultSet resultat = connexion.query(sql);

		try
		{
			return resultat.getString(1);
		}
		catch(SQLException e)
		{
			return null;
		}
	}

	/*IMPORTANT*/
	@Override
	public void setConnectionToken(int userID, String token) throws SQLException {
		connexion.updateToken(userID, token);
	}

	/*IMPORTANT*/
	@Override
	public void setQRCode(int userID, String qrCode) throws SQLException {
		connexion.updateQRcode(userID, qrCode);
	}

	@Override
	public IAdmin getAdminById(int userID) {
		return null;
	}

	@Override
	public IAdmin getAdminByUsername(String userName) {
		return null;
	}

	@Override
	public boolean isAdminMailUsed(String email) {
		return false;
	}

	@Override
	public IAdmin addAdmin(IAdmin admin) {
		return null;
	}

	@Override
	public void updateAdmin(IAdmin user) {
	}

	@Override
	public void removeAdmin(IAdmin user) {

	}

	@Override
	public IConnectionToken getConnectionTokenByAdminId(int adminID) {
		return null;
	}

	@Override
	public ArrayList<Integer> getMachinesId() {
		return null;
	}

	@Override
	public ArrayList<IQRCode> getQRCodesByUserId(int userID) {
		return null;
	}

	@Override
    public boolean isEmailUsed(String email){
    	String sql = "SELECT * FROM Users WHERE userMail='" + email+"'";
		ResultSet resultat = connexion.query(sql);
		return (resultat != null);
    };

	@Override
	public boolean isUsernameUsed(String username){
		String sql = "SELECT * FROM Users WHERE userName='" + username+"'";
		ResultSet resultat = connexion.query(sql);
		return (resultat != null);
	};

    @Override
    public IUser addUser(UserSecuredInfo infos)
	{
		User user = new User(-1, infos.getUserName(), infos.getUserMail(), infos.getPasswordHash(), 0, "");

    	if(connexion.addUser(user))
		{
			return this.getUserByCredentials(infos.getUserName());
		}
		else
		{
			return null;
		}
    };

    @Override
    public void updateUser(User user){
    	connexion.updateUser(user.getUserId(), user.getUserName(), user.getUserMail(),
				user.getHashPassword(), user.getNumberCredits(), user.getQrCode());
    };

    @Override
    public void removeUser(User user) throws SQLException {
    	connexion.deleteUser(user.getUserId());
    };

    @Override
    public Machine getMachineById(int machineId){
    	String sql = "SELECT * FROM Machines WHERE machineId=" + machineId;
		ResultSet resultat = connexion.query(sql); 
		Machine machine = null;
		try {
			machine = new Machine(resultat.getInt(1),
					resultat.getString(2),
					resultat.getInt(3),
					resultat.getString(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return machine;
    };

    public IDistributor addMachine(IDistributor machine){
    	/*if(connexion.addMachine(machine))
		{
			return machine;
		}
    	else
		{
			return null;
		}*/
		return null;
    };

	@Override
    public void updateMachine(IDistributor machine){
    	//connexion.updateMachine(machine.getMachineId(), machine.getLocalisation(), machine.getNumberEcocup(), machine.getPassword());

    };

	@Override
    public void removeMachine(IDistributor machine){
    	//connexion.deleteMachine(machine.getMachineId());

    };

}
