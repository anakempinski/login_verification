package Cookies;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import Account.Account;
import SignIn.*;

public aspect CookieCreationAspect {

    private String ip = "";

    pointcut cookieCreation(Identification id, boolean ifPassed):
            call(public void Identification.setIfIdentificationPassed(boolean)) && target(id) && args(ifPassed);

    after(Identification id, boolean ifPassed): cookieCreation(id, ifPassed) {

        try {
             ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("Host not found error");
            System.exit(1);
        }

        try {
            FileOutputStream file = new FileOutputStream(ip + ".txt", true);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File creation error");
            System.exit(1);
        }
        Account.getCurrentAccount().setHasCookie(true);
        Account.getAccountWithCookieList().put(ip, Account.getCurrentAccount());

    }



    pointcut cookieCreationDoc(Identification id, boolean ifPassed):
            call(public void Identification.setIfIdentificationPassed(boolean)) && target(id) && args(ifPassed);

    after(Identification id, boolean ifPassed): cookieCreationDoc(id, ifPassed) {

        String curTime = id.getDoc().convertion(System.currentTimeMillis());

        String content = "\n" + curTime +  "    Cookie creation for ip address: " + ip;

        id.getDoc().setContentForDocumentation(content);
    }

    }




