package Disconnection;


import Account.Account;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public aspect CookieUpdateAspect {

    private static final int BOUND = 1000000;

    private String updatedSessionId;

    private String ip = "";

    pointcut cookieUpdate(Disconnection disconnection, Account account) : call(public void Disconnection.disconnection(Account))
            && target(disconnection) && args(account);

    before(Disconnection disconnection,  Account account)  : cookieUpdate(disconnection, account) {

        updatedSessionId = getSessionID();

        if(account.getHasCookie()){

            File currentDirectory = new File("");
            String directoryPath = currentDirectory.getAbsolutePath();


            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.out.println("Host not found error");
                System.exit(1);
            }

            String filePath = directoryPath + "/" + ip + ".txt";

            File f = new File(filePath);

            f.delete();

            try {
                FileOutputStream file = new FileOutputStream(ip + ".txt", true);
                file.write(updatedSessionId.getBytes());
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found error");
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Writing to file error");
                System.exit(1);
            }
        }
    }


    private String getSessionID(){
        Random rand = new Random();
        int sessionID = rand.nextInt(BOUND);
        return String.valueOf(sessionID);
    }




    pointcut cookieUpdateDoc(Disconnection disconnection, Account account) : call(public void Disconnection.disconnection(Account))
            && target(disconnection) && args(account);

    after(Disconnection disconnection,  Account account)  : cookieUpdateDoc(disconnection, account) {

        if (account.getHasCookie()) {

            String curTime = disconnection.getDoc().convertion(System.currentTimeMillis());

            String content = "\n" + curTime + "    Session id for ip address " + ip +
                    " was updated to: " + updatedSessionId;

            disconnection.getDoc().setContentForDocumentation(content);
        }
    }

}





