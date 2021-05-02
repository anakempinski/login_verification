package SignIn;

import Account.*;
import EncryptionDecryption.*;
import OperationsDocumentation.Documentation;
import Cookies.*;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Registration extends UserInput{


    public Registration(Encryption enc, Decryption dec, ExistingAccounts existingAccounts){

        super(enc, dec, existingAccounts);
    }


    public Documentation getDoc(){
        return doc;
    }





    public void registration() throws UnknownHostException {

        System.out.println("You need to pass registration");

        boolean regFinished = false;

        while(!regFinished) {

            existingAccounts.setIfExists(false);  // important!! (because if we call it after regist/ident if exists is already true because we change it

            getDataFromUser();

              // after this function we have encrypted login and password

            existingAccounts.checkUserInput();

            if (!existingAccounts.getIfExists()) {    // account with chosen login and password doesn't exist -- > can be created
                accountCreation();
                System.out.println("Registration has finished successfully");
                regFinished = true;
            }
            else{
                System.out.println("User with chosen login/password already exists, please choose another ");
            }
        }
    }




    public void accountCreation() throws UnknownHostException {

        String ip = InetAddress.getLocalHost().getHostAddress();
        Account ac = new Account(getInputLogin(), getInputPassword(), ip);
        existingAccounts.getLoginAccountList().put(getInputLogin(), ac);
        existingAccounts.getLoginsPasswords().put(getInputLogin(), getInputPassword());
        Account.setCurrentAccount(ac);
        ac.setIfNewAccount(true);   // new right after registration
    }


//
//    public void documentation(){
//
//        String regDate = doc.convertion(System.currentTimeMillis());
//
//        String content = "\n" + regDate + "    Registration was passed,    chosen login: " + inputLogin +
//                ",    chosen password: " + inputPassword;
//
//        doc.setContentForDocumentation(content);
//    }




}
