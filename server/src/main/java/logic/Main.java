package logic;

import database.Connexion;
import database.Request;
import network.rest.RESTApp;
import network.rest.UserControler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import structures.IUser;

import java.util.Scanner;

public class Main
{

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Please specify a path to the database.");
            return;
        }

        System.out.println(args[0]);

        System.out.println("Connecting to database ...");
        Connexion _connexion = connectToDatabase(args[0]);

        System.out.println("Starting API ...");

        RESTApp.run(new Request(_connexion));

        System.out.println("Server started.");

        Scanner in = new Scanner(System.in);

        String s;

        while(in.hasNextLine())
        {

            s = in.nextLine();

            switch(s)
            {
                case "stop":
                    _connexion.close();
                    return;
                default:
                    System.out.println("Command " + s + " not recognized !");
            }

        }
    }

    public static Connexion connectToDatabase(String path)
    {
        Connexion connexion = new Connexion(path);
        connexion.connect();

        return connexion;
    }

}
