package structures;

import structures.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDataAccessor
{
    public IUser getUserById(int userID);
    public IUser getUserByCredentials(String username);
    public IUser getUserByMail(String userMail);
    public IUser addUser(UserSecuredInfo infos);
    public void updateUser(User user);
    public void removeUser(User user) throws SQLException;
    public String getConnectionTokenByUserId(int userID);

    public void setConnectionToken(int userID, String token) throws SQLException;
    public void setQRCode(int userID, String qrCode) throws SQLException;

    public IAdmin getAdminById(int userID);
    public IAdmin getAdminByUsername(String userName);
    public boolean isAdminMailUsed(String email);
    public IAdmin addAdmin(IAdmin admin);
    public void updateAdmin(IAdmin user);
    public void removeAdmin(IAdmin user);
    public IConnectionToken getConnectionTokenByAdminId(int adminID);

    public ArrayList<Integer> getMachinesId();

    public IDistributor getMachineById(int distributorID);
    public IDistributor addMachine(IDistributor distributor);
    public void updateMachine(IDistributor user);
    public void removeMachine(IDistributor user);

    public ArrayList<IQRCode> getQRCodesByUserId(int userID);
    public User isQRCodeValid(String qrcode);

    public boolean isEmailUsed(String email);
    public boolean isUsernameUsed(String username);
}
