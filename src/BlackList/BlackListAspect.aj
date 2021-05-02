package BlackList;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import Account.Account;
import BlackList.BlackList;


public aspect BlackListAspect {

    private static final int LEGAL_TIME_BETWEEN_CONNECTIONS = 5;

    long timeBetweenConnections;

    pointcut blockAccount(BlackList bList) : call(public void BlackList.takeCurrentTime()) && target(bList);

    after(BlackList bList) : blockAccount(bList){

        Date lastConnectionDate = new Date(System.currentTimeMillis());
        Account curAccount = Account.getCurrentAccount();

        if (!curAccount.getTimeOfConnectionsList().isEmpty()) {    // if it's not user's first connection
            int connectionListSize = curAccount.getTimeOfConnectionsList().size();
            lastConnectionDate = curAccount.getTimeOfConnectionsList().get(connectionListSize - 1);

            long diff = bList.getCurConnectionDate().getTime() - lastConnectionDate.getTime();
            timeBetweenConnections = TimeUnit.MILLISECONDS.toSeconds(diff);

            if (timeBetweenConnections <= LEGAL_TIME_BETWEEN_CONNECTIONS) {

                bList.blockAccount(curAccount);
            }
        }
    }




    pointcut documentation(BlackList bList, Account account) :
            call(public void BlackList.blockAccount(Account)) && target(bList) && args(account);

    after(BlackList bList, Account account) : documentation(bList, account){

        String curTime = bList.getDoc().convertion(System.currentTimeMillis());

        String content = "\n" + curTime +  "    Time between current and previous connections is " +
                timeBetweenConnections + " seconds. Due to frequent logins your account is blocked ";

        if(BlackList.getUsersBlackList().contains(account)){
            bList.getDoc().setContentForDocumentation(content);
          }
      }
    }












