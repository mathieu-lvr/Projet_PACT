// pour les Test

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
package com.pact.bdd;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.argThat;

// pour la connexion à la BDD
import java.sql.PreparedStatement;

import Connexion


public class Test {
@RunWith(PowerMockRunner.class)


//Test de getUserById
public class getUserByIdTest {

    Connexion db ;
    // User user0 = new User(1, "nameTest1", "mailTest1", "passwordTest1", 2);
    // User user1 = new User(2, "nameTest2", "mailTest2", "passwordTest2", 1);

    @Before()
    public void setUp() throws Exception
    {
       PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Users VALUES(1,"idTest1","mailTest1",2, "aaaaaa");
       PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Users VALUES(2,"idTest2","mailTest2",0, "bbbbbb");
       preparedStatement.executeUpdate();

       Connexion db = new Connexion(dbPath);
       db.connect();

    }

    @Test
    public void verifyGetUserByIdTest() throws Exception
    {
    	  assertTrue(db.getUserById.verifyToken("idtest1", 1, db));
        assertFalse(db.getUserById.verifyToken("idTest2", 1, db));
        assertFalse(db.getUserById.verifyToken("idTest1", 2, db));
    }
}


//Test de getUserByMail
public class getUserByIMailTest {

  Connexion db;


	@Before()
	public void setUp() throws Exception
	{
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Users VALUES(1,"idTest1","mailTest1",2, "aaaaaa");
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Users VALUES(2,"idTest2","mailTest2",0, "bbbbbb");
    preparedStatement.executeUpdate();

    Connexion db = new Connexion(dbPath);
    db.connect();

	}

	@Test
	public void verifyGetUserByEmailTest() throws Exception
	{
  		assertTrue(authenticationValidator.verifyToken(1, "mailTest1", db));
  		assertFalse(authenticationValidator.verifyToken(2, "mailTest1" , db));
        assertFalse(authenticationValidator.verifyToken(1, "mailTest2", db));
	}
}

//Test de getUserByCredentials
public class getUserByCredentialsTest {

  Connexion db;


	@Before()
	public void setUp() throws Exception
	{
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Users VALUES(1,"idTest1","mailTest1",2, "aaaaaa");
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Users VALUES(2,"idTest2","mailTest2",0, "bbbbbb");
    preparedStatement.executeUpdate();

    Connexion db = new Connexion(dbPath);
    db.connect();

	}

	@Test
	public void verifyGetUserByCredentialsTest() throws Exception
	{
		assertTrue(authenticationValidator.verifyToken(1, "idTest1", db));
		assertFalse(authenticationValidator.verifyToken(2, "idTest1" , db));
		assertFalse(authenticationValidator.verifyToken(1, "idTest2", db));
	}
}

//Test de getDistributorsById
public class getDistributorsByIdTest {

  Connexion db;
	//Machine machine1 = new Machine(1, "Telecom", 200, "password1");
	// Machine machine2 = new Machine(2, "Polytechnique", 400, "password2");

	@Before()
	public void setUp() throws Exception
	{
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Machines VALUES(1, "Telecom", 200, "password1");
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT INTO Machines VALUES(2, "Polytechnique", 400, "password2");
    preparedStatement.executeUpdate();

    Connexion db = new Connexion(dbPath);
    db.connect();

	}

	@Test
	public void verifyGetDistributorsByIdTest() throws Exception
	{
		assertTrue(authenticationValidator.verifyToken("Telecom", 1, db));
		assertFalse(authenticationValidator.verifyToken("Polytechnique", 1 , db));
		assertFalse(authenticationValidator.verifyToken("Telecom", 2, db));
	}
}

//Test de addUser
public class addUserTest {
  Connexion db;

	@Before()
	public void setUp() throws Exception
	{
  Connexion db = new Connexion(dbPath);
  db.connect();
  db.addUser(1,"idTest1","mailTest1",2, "aaaaaa");

	}

	@Test
	public void verifyaddUserTest() throws Exception
	{
		assertTrue(authenticationValidator.verifyToken("idTest1", 1, db));
	}
}

//Test de getDistributorsById
public class getDistributorsByIdTest {

  Connexion db;

	@Before()
	public void setUp() throws Exception
	{
    Connexion db = new Connexion(dbPath);
    db.connect();
    db.addMachine(1, "Telecom", 200, "password1");
	}

	@Test
	public void verifyGetUserTest() throws Exception
	{
		assertTrue(authenticationValidator.verifyToken("Telecom", 1, db));
	}
}

}
