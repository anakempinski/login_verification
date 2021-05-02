package SignIn;

import Account.Account;
import EncryptionDecryption.*;


import java.util.HashMap;
import java.util.LinkedHashMap;


public class ExistingAccounts {

    private HashMap<String, String> existingAccounts;

    private HashMap<String, Account> loginAccountList;  // need for identification


    private Encryption enc;
    private Decryption dec;

    private boolean ifExists;


    public ExistingAccounts(HashMap<String, String> loginsPasswords, HashMap<String,Account> accounts,
                            Encryption enc, Decryption dec){
        this.existingAccounts = loginsPasswords;
        this.loginAccountList = accounts;
        this.enc = enc;
        this.dec = dec;
    }



    public HashMap<String, String> getLoginsPasswords(){
        return existingAccounts;
    }


    public boolean getIfExists(){
        return ifExists;
    }

    public void setIfExists(boolean ifExists){
        this.ifExists = ifExists;
    }

    public HashMap<String, Account> getLoginAccountList(){
        return loginAccountList;
    }

    public  void setLoginAccountList(LinkedHashMap<String, Account> lst){
        loginAccountList = lst;
    }


    public Encryption getEnc(){
        return enc;
    }

    public Decryption getDec(){
        return dec;
    }


    public void prepareData(){

        dec.setLoginToDec(enc.getEncLogin());     // decryption aspect is called

        dec.setPasswordToDec(enc.getEncPassword());
    }



    public void checkUserInput(){

        prepareData();

        String decLogin = dec.getDecLogin();

        String password = existingAccounts.get(decLogin); // check password that suits to input login according to list of logins passwords

        if(existingAccounts.containsKey(decLogin) && dec.getDecPassword().equals(password)) {
            // if map containes entry with decrypted login and password

            setIfExists(true);
        }


    }

}
