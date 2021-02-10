package network.security;

import structures.IDataAccessor;
import network.security.exceptions.InvalidCredentialsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
//@PrepareForTest(UserService.class)
public class UserServiceTest
{
    IDataAccessor db = Mockito.mock(IDataAccessor.class);

    User user0 = new User("nTest", "mTest", 0, 2);
    User user1 = new User("nTest2", "mTest2", 1, 1);

    UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

    @Before()
    public void setUp() throws Exception
    {
        Mockito.when(db.getUserByCredentials("mTest", "pTest")).thenReturn(user0);
        Mockito.when(db.getUserByCredentials("mTest", "pTest2")).thenReturn(null);
        Mockito.when(db.getUserByCredentials("mTest2", "pTest2")).thenReturn(null);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void verifyLoginNotFoundTest() throws Exception
    {
        (new UserService()).login("mTest2", "pTest2", db);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void verifyLoginInvalidTest() throws Exception
    {
        (new UserService()).login("mTest", "pTest2", db);
    }

    @Test
    public void verifyLogin() throws Exception
    {
        UserService s = new UserService();

        UserService uS = Mockito.spy(s);

        Mockito.doReturn("01").when(uS).getNewUUIDString();

        assertTrue(uS.login("mTest", "pTest", db).equals("01"));
    }
}
