package network.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import structures.IDataAccessor;
import network.security.AuthenticationValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import structures.User;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserControler.class)
public class UserControlerTest implements ApplicationContextAware
{
    IDataAccessor db = Mockito.mock(IDataAccessor.class);

    User user0 = new User(0, "nTest", "mTest", "pTest", 2, "a");
    User user1 = new User(1, "nTest2", "mTest2", "pTest2", 1, "b");

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserControler controller;

    AuthenticationValidator authenticationValidator;

    @Before()
    public void setUp() throws Exception
    {
        when(db.getUserById(0)).thenReturn(user0);
        when(db.getUserById(1)).thenReturn(null);

        when(db.getUserByMail("mTest")).thenReturn(user0);
        when(db.getUserByMail("mail2")).thenReturn(null);

        Mockito.doReturn(user0).when(db).addUser(argThat((User u) -> u.getUserId() == 0));
        Mockito.doReturn(null).when(db).addUser(argThat((User u) -> u.getUserId() == 1));

        authenticationValidator = Mockito.mock(AuthenticationValidator.class);

        when(authenticationValidator.verifyToken("01", 0, db)).thenReturn(true);
        when(authenticationValidator.verifyToken("02", 0, db)).thenReturn(false);
        when(authenticationValidator.verifyToken("02", 1, db)).thenReturn(true);

        when(authenticationValidator.verifyToken("01", "mTest", db)).thenReturn(true);
        when(authenticationValidator.verifyToken("02", "mTest", db)).thenReturn(false);
        when(authenticationValidator.verifyToken("02", "mail2", db)).thenReturn(true);

        ReflectionTestUtils.setField(controller, "authenticationValidator", authenticationValidator);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.getBean(UserControler.class).setDatabaseController(db);
    }

    @Test
    public void getCreditsTest() throws Exception
    {
        mvc.perform(get("/api/v1/user/credits/0").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/v1/user/credits/0").header("Authorization", "01").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));

        mvc.perform(get("/api/v1/user/credits/0").contentType(MediaType.TEXT_PLAIN).header("Authorization", "02"))
                .andExpect(status().isUnauthorized());

        mvc.perform(get("/api/v1/user/credits/1").contentType(MediaType.TEXT_PLAIN).header("Authorization", "02"))
                .andExpect(status().isNotFound());

        mvc.perform(get("/api/v1/user/credits/1").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUserByIdTest() throws Exception
    {
        mvc.perform(get("/api/v1/user/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/v1/user/0").header("Authorization", "02").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        mvc.perform(get("/api/v1/user/0").header("Authorization", "01").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("nTest")))
                .andExpect(jsonPath("$.email", is("mTest")))
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.credits", is(2)));

        mvc.perform(get("/api/v1/user/1").header("Authorization", "02").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUserByEmailTest() throws Exception
    {
        mvc.perform(get("/api/v1/user/mail/mTest").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/v1/user/mail/mTest").header("Authorization", "02").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        mvc.perform(get("/api/v1/user/mail/mTest").header("Authorization", "01").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("nTest")))
                .andExpect(jsonPath("$.email", is("mTest")))
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.credits", is(2)));

        mvc.perform(get("/api/v1/user/mail/mail2").header("Authorization", "02").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addUserTest() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/api/v1/user/").content(mapper.writeValueAsString(user0)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(post("/api/v1/user/").content(mapper.writeValueAsString(user1)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/v1/user/").content(mapper.writeValueAsString(null)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
