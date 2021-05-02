package BlackList;


import Account.Account;
import BlackList.BlackListAspect;
import OperationsDocumentation.Documentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BlackList {

    private Date curConnectionDate;

    //private long legalTimeBetweenConnections;


    Documentation doc = new Documentation();

    private static ArrayList<Account> usersBlackList = new ArrayList<>();



    public BlackList(){

       // this.legalTimeBetweenConnections = legalTime;
    }


    public static ArrayList<Account> getUsersBlackList(){
        return usersBlackList;
    }

    public Documentation getDoc(){
        return doc;
    }

    public Date getCurConnectionDate(){
        return curConnectionDate;
    }

    public void setCurConnectionDate(Date date){
        this.curConnectionDate = date;
    }

//    public long getLegalTimeBetweenConnections(){
//        return legalTimeBetweenConnections;
//    }






// aspect calls it and passes curAccount
    public void blockAccount(Account account){

        if(!usersBlackList.contains(account)){    // if account isn't already in black list
            System.out.println("Your account is blocked, time between connections is too short");
            usersBlackList.add(account);
        }

    }




    public void takeCurrentTime(){
        long curConnectionTime = System.currentTimeMillis();
        Date curDate = new Date(curConnectionTime);
        setCurConnectionDate(curDate);
    }







}


