package network.security;

import structures.IDataAccessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
public class AuthValidatorTest
{

    IDataAccessor db = Mockito.mock(IDataAccessor.class);

    User user0 = new User("nTest", "mTest", 0, 2);
    User user1 = new User("nTest2", "mTest2", 1, 1);

    @Before()
    public void setUp() throws Exception
    {
        Mockito.when(db.getUserByMail("mTest")).thenReturn(user0);
        Mockito.when(db.getUserByMail("mTest2")).thenReturn(null);

        Mockito.when(db.getConnectionTokenByUserId(0)).thenReturn("01");
        Mockito.when(db.getConnectionTokenByUserId(1)).thenReturn(null);
    }

    @Test
    public void verifyTokenTest() throws Exception
    {
        AuthenticationValidator authenticationValidator = new AuthenticationValidator();

        assertTrue(authenticationValidator.verifyToken("01", 0, db));
        assertFalse(authenticationValidator.verifyToken("02", 0, db));
        assertFalse(authenticationValidator.verifyToken("02", 1, db));

        assertTrue(authenticationValidator.verifyToken("01", "mTest", db));
        assertFalse(authenticationValidator.verifyToken("02", "mTest", db));
        assertFalse(authenticationValidator.verifyToken("02", "mTest2", db));

    }

}
