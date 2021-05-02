package SignIn;

import Account.Account;
import Cookies.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;



public class SignIn {

    private Identification id;
    private Registration reg;

    private boolean wantToEnter;
    private boolean withoutIdentification;



    public SignIn(Identification id, Registration reg){
        this.id = id;
        this.reg = reg;
    }

    public Identification getId(){
        return id;
    }

    public Registration getReg(){
        return reg;
    }


    public boolean getWantToEnter(){
        return wantToEnter;
    }

    public void setWantToEnter(boolean enter){
        this.wantToEnter = enter;
    }

    public boolean getWithoutIdentification(){
        return withoutIdentification;
    }

    public void setWithoutIdentification(boolean withoutId){
        this.withoutIdentification = withoutId;
    }



    public void ifWantToEnterCheck() throws IOException {

        System.out.println("\nWelcome to our site");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 'y' if you want to sign in, otherwise enter 'n'");
        if (sc.nextLine().equals("y")) {

            setWantToEnter(true);
        }
        cookieCheck();
    }



    public void cookieCheck() throws IOException{

        String ip = InetAddress.getLocalHost().getHostAddress();

        if(Account.getAccountWithCookieList().containsKey(ip)){

            Account account = Account.getAccountWithCookieList().get(ip);

            Account.setCurrentAccount(account);

            setWithoutIdentification(true);
        }
    }





    public void welcomeFunc() throws UnknownHostException {
        boolean ifInputCorrect = false;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("If you're registered - please enter '1', else - please enter '2'");
        while (!ifInputCorrect) {
            input = sc.nextLine();
            if (input.equals("1")) {
                ifInputCorrect = true;
                id.identification();
            }
            else if (input.equals("2")) {
                ifInputCorrect = true;
                reg.registration();
            }
        }
    }



}
