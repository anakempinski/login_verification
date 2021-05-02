package Connections;

import Account.Account;

import java.util.Date;

public aspect ConnectionsAspect {





    pointcut documentation(Connections connection, Account account) : call(public void Connections.startConnection(Account))
                                                       && target(connection) && args(account);

    after(Connections connection, Account account)  : documentation(connection, account) {


        String date = connection.getDoc().convertion(System.currentTimeMillis());

        String content = "\n" + date + "    Connection succeeded";

        connection.getDoc().setContentForDocumentation(content);
    }



}
