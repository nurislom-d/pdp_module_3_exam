package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.database.Database;
import org.example.entities.Client;
import javax.xml.crypto.Data;
import java.io.FileWriter;

public class Controller {

    public void mainController(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String mainMenu = """
                
                0 - Exit;
                1 - Log-In;
                2 - Sign-Up;
                """;

        String ndMainMenu = """
                
                0 - Back;
                1 - Admin;
                2 - Seller;
                3 - Client;
                """;

        String welcomeText = "\nWelcome to the program!";
        String chooseOneRequest = "\nChoose one: ";
        String asText = "Okay, now choose your role: ";
        String byeText = "\nGoodbye sir, have a nice day!";

        while (true){

            System.out.println(mainMenu);
            System.out.print(chooseOneRequest);

            switch (Database.scannerI.nextInt()){

                case 0 -> {

                    System.out.println(byeText);
                    return;
                }
                case 1 -> {

                    System.out.println(ndMainMenu);
                    System.out.print(asText);

                    switch (Database.scannerI.nextInt()){

                        case 0:
                            System.out.println(byeText);
                            return;

                        case 1:
                            String inputLoginA, inputPassA;

                            System.out.print("Enter your login: ");
                            inputLoginA = Database.scannerS.nextLine();

                            System.out.print("Enter your password for " + inputLoginA + ": ");
                            inputPassA = Database.scannerS.nextLine();

                            Database.signInAsAdmin(inputLoginA, inputPassA);
                            break;

                        case 2:
                            String inputLoginS;

                            System.out.print("Enter your login: ");
                            inputLoginS = Database.scannerS.nextLine();

                            Database.signInAsSeller(inputLoginS);
                            break;

                        case 3:
                            String inputLoginC;

                            System.out.print("Enter your login: ");
                            inputLoginC = Database.scannerS.nextLine();

                            Database.signInAsClient(inputLoginC);
                            return;
                    }
                }
                case 2 -> {

                    Database.signUpAsClient();
                    break;
                }

                default -> {

                    System.err.println("\nUnrecognized command!");
                }
            }
        }
    }
}
