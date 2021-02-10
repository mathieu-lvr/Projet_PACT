package network.security;

import com.kosprov.jargon2.api.Jargon2;
import structures.IDataAccessor;
import network.security.exceptions.InvalidCredentialsException;
import structures.IUser;
import structures.User;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.UUID;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;
import static com.kosprov.jargon2.api.Jargon2.jargon2Verifier;

public class UserService {

    public String login(String username, String password, IDataAccessor db) throws InvalidCredentialsException, UnsupportedEncodingException, SQLException {
        IUser user = db.getUserByCredentials(username);

        Jargon2.Verifier verifier = jargon2Verifier();

        boolean matches = verifier.hash(((User)user).getHashPassword()).password(password.getBytes("UTF-8")).verifyEncoded();

        if(user == null || !matches)
        {
            throw new InvalidCredentialsException("Given credentials are invalid.");
        }
        else
        {
            String token = getNewUUIDString();

            db.setConnectionToken(((User)user).getUserId(), token);

            return token;
        }
    }

    public String getNewUUIDString()
    {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    public static String hashPassword(String password) throws UnsupportedEncodingException {
        Jargon2.Hasher hasher = jargon2Hasher()
                .type(Jargon2.Type.ARGON2d)
                .memoryCost(65536)
                .timeCost(4)
                .parallelism(8)
                .saltLength(64)
                .hashLength(128);

        return hasher.password(password.getBytes("UTF-8")).encodedHash();
    }
}
