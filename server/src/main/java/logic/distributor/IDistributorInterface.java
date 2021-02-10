package logic.distributor;

import structures.IAuthentificationData;
import structures.IConnectionToken;
import structures.IQRCode;
import structures.IUser;

public interface IDistributorInterface
{

    public boolean verifyQRCode(IQRCode qrcode);

    public IUser getUser(int userID);
    public IUser getUser(String email);

    public boolean authorizeDistribution(IUser user, IQRCode code);
    public void validateDistribution(IUser user);

    public void validateCupRecover(IUser user);

    public boolean logUser(String username, String password);
}
