package database;
import structures.IDistributor;
import structures.IUser;
import structures.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	private String dBPath = "Chemin à la base de donnée SQLite";
    private Connection connection = null;
    private Statement statement = null;
 
    public Connexion(String dBPath) {
        this.dBPath = dBPath;
    }
 
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + dBPath + " avec succès");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion");
        }
    }
    
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet query(String request) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requet : " + request);
        }
        return resultat;
    }
    
    /*public boolean addMachine(IDistributor machine) {
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Machines VALUES(?,?,?,?)");
            preparedStatement.setLong(1, machine.getMachineId());
            preparedStatement.setString(2, machine.getLocalisation());
            preparedStatement.setLong(3, machine.getNumberEcocup());
            preparedStatement.setString(4, machine.getPassword());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return false;
        }
    }*/
    
    
    public boolean addUser(User user) {
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (userName, userMail, hashPassword, numberCredits, QRcode) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserMail());
            preparedStatement.setString(3, user.getHashPassword());
            preparedStatement.setInt(4, user.getNumberCredits());
            preparedStatement.setString(5, user.getQrCode());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    
    public void addTransaction(Transaction transaction) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Transaction VALUES(?,?,?,?,?)");
        preparedStatement.setLong(1, transaction.getTransactionId());
        preparedStatement.setLong(2, transaction.getUserId());
        preparedStatement.setLong(3, transaction.getMachineId());
        preparedStatement.setDate(4, transaction.getDate());
        preparedStatement.setString(5, transaction.getType());
        preparedStatement.executeUpdate();
    }

    public boolean updateUser(int userId, String userName, String userMail, String hashPassword, int numberCredits, String qrCode)
    {
    	try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET userName = ? , "
                + "userMail = ? , "
                + "hashPassword = ? , "
                + "numberCredits = ? , "
                + "qrCode = ?  "
                + "WHERE userId = ?");

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userMail);
            preparedStatement.setString(3, hashPassword);
            preparedStatement.setInt(4, numberCredits);
            preparedStatement.setString(5, qrCode);
            preparedStatement.setInt(6, userId);


            preparedStatement.executeUpdate();

        return true;
    	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } 	
    }

    public void updateToken(int userId, String token) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET token = ? WHERE userId = ?");
        preparedStatement.setString(1, token);
        preparedStatement.setInt(2, userId);

        preparedStatement.executeUpdate();
    }

    public void updateQRcode(int userId, String qrCode) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET QRcode = ? WHERE userId = ?");
        preparedStatement.setString(1, qrCode);
        preparedStatement.setInt(2, userId);

        preparedStatement.executeUpdate();
    }


    public void updateMachine(int machineId, String localisation, int numberEcocup, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Machines SET localisation = ? , "
            + "numberEcocup = ? "
            + "password = ? "
            + "WHERE machineId = ?");

        preparedStatement.setString(1, localisation);
        preparedStatement.setInt(2, numberEcocup);
        preparedStatement.setString(3, password);
        preparedStatement.setInt(4, machineId);

        preparedStatement.executeUpdate();
    }
    
    public void deleteUser(int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE userId = ?");
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();
    }
    
    public void deleteMachine(int machineId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Machines WHERE machineId = ?");
        preparedStatement.setInt(1, machineId);
        preparedStatement.executeUpdate();
    }
    
    public void deleteTransaction(int transactionId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Transaction WHERE transactionId = ?");
        preparedStatement.setInt(1, transactionId);
        preparedStatement.executeUpdate();
    }
}
