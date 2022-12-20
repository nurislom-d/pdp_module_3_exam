package org.example.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Card;
import org.example.entities.Client;

import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;

public class Database {

    public static long clientCounter;
    public static long sellerCounter;
    public static Scanner scannerI = new Scanner(in);
    public static Scanner scannerS = new Scanner(in);
    public static String clientReadText = "";
    public static String sellerReadText = "";
//    public static String Text;

    public static void readClients(){

        try(FileReader fileReader = new FileReader("src/main/resources/clients.json");
            InputStream inputStream = new FileInputStream("src/main/resources/clients.json")){

            char[] clients = new char[inputStream.available()];

            fileReader.read(clients);

            for (char s: clients){

                out.print(s);
                clientReadText += s;
            }
        }catch(Exception ex){

            err.println("Caught exception!");
        }

        readClients();
    }
    public static void readSellers(){

        try(FileReader fileReader = new FileReader("src/main/resources/sellers.json");
            InputStream inputStream = new FileInputStream("src/main/resources/sellers.json")){

            char[] sellers = new char[inputStream.available()];

            fileReader.read(sellers);

            for (char s: sellers){

                out.print(s);
                clientReadText += s;
            }
        }catch(Exception ex){

            err.println("Caught exception!");
        }

        readSellers();
    }

    public static void signInAsAdmin(String AdminLogin, String AdminPass){

        if(AdminLogin.equals("admin") && AdminPass.equals("root@1234")){

            out.println("Logged in as Admin successfully");
        }
    }
    public static void signInAsSeller(String SellerLogin){

        String pass;

        if(sellerReadText.contains(SellerLogin)){

            out.print("Enter password for " + SellerLogin + ": ");
            pass = scannerS.nextLine();

            if(sellerReadText.contains(pass)){

                out.println("Signed-In as Client successfully!");
            }else {

                err.println("error");
            }
        }
    }
    public static void signInAsClient(String ClientLogin){

        String pass;

        if(clientReadText.contains(ClientLogin)){

            out.print("Enter password for " + ClientLogin + ": ");
            pass = scannerS.nextLine();

            if(clientReadText.contains(pass)){

                out.println("Signed-In as Client successfully!");
            }else{

                err.println("error");
            }
        }
    }

    public static void signUpAsClient(){

        Gson gson = new Gson();
        Client client = new Client();

        String clientLogin;
        String clientPass;

        out.println("\nSigning-Up as Client!");
        out.print("\nEnter your login: ");
        client.setLogin(scannerS.nextLine());

        if(!clientReadText.contains(client.getLogin())){

            out.print("\nEnter your password: ");
            client.setPass(scannerS.nextLine());

            out.println("\nSigned-Up as Client successfully!");
            addClient(client);
        }else{

            err.println("The login that you entered is already taken!");
        }
    }

    public static void addClient(Client client)  {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonFormat = gson.toJson(client);

        try(FileWriter writer = new FileWriter("src/main/resources/clients.json", true)) {

            writer.write("\n" + jsonFormat);
            clientCounter++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
