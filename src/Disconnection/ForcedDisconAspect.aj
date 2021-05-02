package Disconnection;


import Account.Account;

import java.util.concurrent.TimeUnit;
import java.util.Date;


public aspect ForcedDisconAspect{

    private static final int LEGAL_CON_TIME = 60;

    pointcut connectionDuration(Disconnection disconnection, Account account) :
            call(public void Disconnection.signOut(Account)) && target(disconnection) && args(account);

    after(Disconnection disconnection, Account account) : connectionDuration(disconnection, account){

            long startTime = disconnection.getConnections().getStartConnectionTime();
            Date startConnectionTime = new Date(startTime);

            boolean legalDuration = true;

            if (account.getConnected()) {

                while (legalDuration) {

                    Date curDate = new Date(System.currentTimeMillis());

                    long diff = curDate.getTime() - startConnectionTime.getTime();
                    long inSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);

                    if (inSeconds >= LEGAL_CON_TIME) {

                        legalDuration = false;

                        System.out.println(account.getLogin() + ", you're disconnected due to too long connection time");

                        disconnection.setForcedDisconnection(true);

                        disconnection.disconnection(account);
                    }
                }
             }

             legalDuration = true;

    }




    pointcut forcedDisconnectionDoc(Disconnection disconnection) :
            call(public void Disconnection.decreaseConnections()) && target(disconnection) ;

    after(Disconnection disconnection) : forcedDisconnectionDoc(disconnection){

        String content = " ";
        String idDate = disconnection.getDoc().convertion(System.currentTimeMillis());

        if(disconnection.getForcedDisconnection()){
            content = "\n" + idDate + "    User was disconnected due to long connection time";
        }
        else{
            content = "\n" + idDate + "    User disconnected";
        }

        disconnection.getDoc().setContentForDocumentation(content);

    }

}





