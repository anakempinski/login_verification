package Disconnection;


import Account.Account;
import SignIn.Identification;
import Connections.Connections;
import OperationsDocumentation.Documentation;
import java.util.Scanner;

public class Disconnection {

    private Identification id;  // need
    private Connections connections;  // need
   // private Cookies cookie;

    boolean forcedDisconnection;

    Documentation doc = new Documentation();

    public Disconnection(Identification id, Connections con){
        this.id = id;
        this.connections = con;
    }



//    public Cookies getCookie(){
//        return cookie;
//    }

    public Documentation getDoc(){
        return doc;
    }

    public Identification getId(){
        return id;
    }

    public Connections getConnections(){
        return connections;
    }

    public boolean getForcedDisconnection(){
        return forcedDisconnection;
    }

    public void setForcedDisconnection(boolean forced){
        this.forcedDisconnection = forced;
    }



    public void decreaseConnections(){
        int newNumOfConnected = connections.getNumOfConnected() - 1;
        connections.setNumOfConnected(newNumOfConnected);
    }




    public void signOut(Account account) {

        Scanner sc = new Scanner(System.in);
        System.out.println("For disconnection press 'q', to stay connected press 'c'");
        String keyPressed = sc.nextLine();
        if (keyPressed.equals("q")) {
            System.out.println(account.getLogin() + ", you decided to disconnect");
            disconnection(account);
        }
    }



    public void disconnection(Account account){
        connections.getConnectedUsers().remove(account);
        decreaseConnections();
        account.setConnected(false);
        //id.setIfIdentificationPassed(false);   // because of cookie, enough to pass id one time
        //setForcedDisconnection(false);   // ????
    }









}
