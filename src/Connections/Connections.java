package Connections;

import Account.Account;
import OperationsDocumentation.Documentation;

import java.util.*;



public class Connections {

    private int numOfConnected;

    long startConnectionTime;

    //Cookies cookie;

   // String connectionStart;

    private  ArrayList<Account> connectedUsers = new ArrayList<Account>();


    Documentation doc = new Documentation();


    public Connections(){

    }


    public  int getNumOfConnected(){
        return numOfConnected;
    }

    public void setNumOfConnected(int num){
        numOfConnected = num;
    }

    public Documentation getDoc(){
        return doc;
    }

    public long getStartConnectionTime(){
        return startConnectionTime;
    }

    public void setStartConnectionTime(long time){
        this.startConnectionTime = time;
    }

    public void increaseConnections(){
        numOfConnected++;
    }

    public  ArrayList<Account> getConnectedUsers(){
        return connectedUsers;
    }





    public void startConnection(Account account){

        long connectionTime = System.currentTimeMillis();
        setStartConnectionTime(connectionTime);
        Date curDate = new Date(connectionTime);
        account.getTimeOfConnectionsList().add(curDate);
        account.setConnected(true);
        connectedUsers.add(account);
        increaseConnections();
        System.out.println("You are connected");
    }


}
