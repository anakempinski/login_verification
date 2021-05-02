package SignIn;

import EncryptionDecryption.*;
import OperationsDocumentation.Documentation;

import java.util.Scanner;


public class UserInput {

    protected String inputLogin;
    protected String inputPassword;

    protected Encryption enc;
    protected Decryption dec;

    protected ExistingAccounts existingAccounts;
    Documentation doc = new Documentation();



    public UserInput(Encryption enc, Decryption dec, ExistingAccounts existingAccounts){
        this.enc = enc;
        this.dec = dec;
        this.existingAccounts = existingAccounts;
    }


    public Documentation getDoc(){
        return doc;
    }

    public String getInputLogin(){
        return inputLogin;
    }

    public String getInputPassword(){
        return inputPassword;
    }

    public void setInputLogin(String login){
        this.inputLogin = login;
    }

    public void setInputPassword(String password){
        this.inputPassword = password;
    }


    public void getDataFromUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter login, please");
        setInputLogin(sc.nextLine());
        enc.setLoginToEnc(getInputLogin());

        System.out.println("Enter password, please");
        setInputPassword(sc.nextLine());
        enc.setPasswordToEnc(getInputPassword());
    }


}
