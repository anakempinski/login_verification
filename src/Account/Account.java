package Account;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Account {

    private String login;

    private String password;

    private boolean connected;

    private boolean hasCookie;

    private ArrayList<Date> accountConnectionTimes = new ArrayList<Date>();

    // add account to this list only of we created cookie for it
    private static HashMap<String, Account> accountWithCookieList = new HashMap<String, Account>();


    private static Account currentAccount;   // account after registration or after identification (account that we work with it) !!!!
    private boolean ifNewAccount;   // to distinguish between new account (after registration) and acoount that already existed  !!!!



    public Account(String login, String password, String ipAddress)  {
        this.login = login;
        this.password = password;
    }




    public String getLogin(){
        return login;
    }

    public void setConnected(boolean connected){
        this.connected = connected;
    }

    public boolean getConnected(){
        return connected;
    }

    public String getPassword(){
        return password;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public ArrayList<Date> getTimeOfConnectionsList(){
        return accountConnectionTimes;
    }

    public  static Account getCurrentAccount(){
        return currentAccount;
    }

    public static   void setCurrentAccount(Account account){
        currentAccount = account;
    }

    public  boolean getIfNewAccount(){
        return ifNewAccount;
    }

    public  void setIfNewAccount(boolean ifNew){
        ifNewAccount = ifNew;
    }

    public boolean getHasCookie(){
        return hasCookie;
    }

    public void setHasCookie(boolean hasCookie){
        this.hasCookie = hasCookie;
    }

    public static HashMap<String, Account> getAccountWithCookieList(){
        return accountWithCookieList;
    }

    public String toString(){
        return "login is " + login + ", password is " + password;
    }


}
