import BlackList.BlackList;
import Connections.*;
import EncryptionDecryption.Decryption;
import EncryptionDecryption.Encryption;
import Location.Location;
import SignIn.*;
import Account.*;
import OperationsDocumentation.Documentation;
import SignIn.Registration;
import Disconnection.*;

import java.io.*;
import java.net.InetAddress;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {


        HashMap<String, String> loginsPasswords = new HashMap<>();
        loginsPasswords.put("dan", "123");
        loginsPasswords.put("bob", "456");
        loginsPasswords.put("kate", "789");




        HashMap<String, Account> loginAccountLst = new HashMap<String, Account>();

        String ip = InetAddress.getLocalHost().getHostAddress();

        Account ac1 = new Account("dan", "123", ip);





        loginAccountLst.put("dan", ac1);

        Documentation doc = new Documentation();


        Encryption enc = new Encryption();
        Decryption dec = new Decryption();

        ExistingAccounts existingAccounts = new ExistingAccounts(loginsPasswords, loginAccountLst, enc, dec);



        Registration registration = new Registration(enc, dec, existingAccounts);

        Identification id = new Identification(enc, dec, existingAccounts);

        Connections connection = new Connections();


        SignIn signIn = new SignIn(id, registration);


        BlackList bList = new BlackList();



        connection.setNumOfConnected(190);

        Location location = new Location();


        Disconnection disconnection = new Disconnection(id, connection);




        while (true) {
            startFunction(signIn, location, bList, connection, disconnection);
        }

    }




    public static void startFunction(SignIn signIn, Location location, BlackList bList, Connections con,
                                    Disconnection disconnection) throws Exception {

        signIn.setWantToEnter(false);

         try {
             writeToFile();
         } catch (Exception e) {
             e.printStackTrace();
         }

         signIn.ifWantToEnterCheck();

         if(!signIn.getWantToEnter()){
             System.out.println("Goodbye");
             System.exit(0);
        }

       // if there is cookie
        else{

            if(signIn.getWithoutIdentification()) {

                Account ac = Account.getCurrentAccount();

                processForExistingAccount(location, bList, con, disconnection, ac);
        }
           // if there is no cookie
        else{
                signIn.welcomeFunc();

                Account ac = Account.getCurrentAccount();

                boolean ifNew = ac.getIfNewAccount();

                if (ifNew) {

                    location.ifLocationLegal();
                    if (!location.getIfLegalConnection()) {
                        return;
                    }

                    con.startConnection(ac);

                    disconnection.signOut(ac);
                }

                // existing account
                else {

                    processForExistingAccount(location, bList, con, disconnection, ac);

                }
            }
        }

    }







    public static void writeToFile() throws Exception{
        String content = "\n\n**NEW CONNECTION**";

        FileOutputStream file = new FileOutputStream("Documentation.txt", true);
        file.write(content.getBytes());

    }



    public static void processForExistingAccount(Location location, BlackList bList, Connections con,
                                          Disconnection disconnection, Account ac){
        location.ifLocationLegal();
        if (!location.getIfLegalConnection()) {
            return;
        }

        bList.takeCurrentTime();

        if (BlackList.getUsersBlackList().contains(ac)) {
           // System.out.println("Access denied, your account is blocked");
            return;
        }

        con.startConnection(ac);

        disconnection.signOut(ac);

    }








}
