package network.security;

import structures.IDataAccessor;
import structures.User;

public class AuthenticationValidator
{


    public boolean verifyToken(String token, int id, IDataAccessor db)
    {
        String dbToken = db.getConnectionTokenByUserId(id);

        if(dbToken == null)
        {
            return false;
        }

        return dbToken.equals(token);
    }

    public boolean verifyToken(String token, String mail, IDataAccessor db)
    {
        User u = (User)(db.getUserByMail(mail));

        if(u == null)
        {
            return false;
        }

        return db.getConnectionTokenByUserId(u.getUserId()).equals(token);
    }

}
