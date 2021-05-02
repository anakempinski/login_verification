package SignIn;

import Account.Account;
import OperationsDocumentation.Documentation;
import EncryptionDecryption.*;



public class Identification extends UserInput{

    private static final int NUM_OF_TRYS = 3;

    private int numOfTrys = NUM_OF_TRYS;

    private boolean ifIdentificationPassed;

    //private Cookies cookie;



    public Identification(Encryption enc, Decryption dec, ExistingAccounts existingAccounts){
        super(enc, dec, existingAccounts);
        //this.cookie = cookie;
        this.ifIdentificationPassed = false;
    }



    public Documentation getDoc(){
        return doc;
    }

    public boolean getIfIdentificationPassed(){
        return ifIdentificationPassed;
    }

    public void setIfIdentificationPassed(boolean passed){
        this.ifIdentificationPassed = passed;
    }

    public void setNumOfTrys(){
        this.numOfTrys = NUM_OF_TRYS;
    }




    public void identification() {
        System.out.println("You need to pass identification");

        while (numOfTrys > 0) {

            numOfTrys--;

            getDataFromUser();

            existingAccounts.checkUserInput();

            if (existingAccounts.getIfExists()) {

                getSuitableAccount();

                setIfIdentificationPassed(true);   // for aspect

                setNumOfTrys();

                return;
            }

            System.out.println("At least one of the details that were entered is wrong, please try again");
        }

        existingAccounts.checkUserInput();
        if (!existingAccounts.getIfExists()) {

            System.out.println("Identification failed, you can't access");
        }
    }




// if account exists -- we get it according to login (login is unique)

    public void getSuitableAccount(){

        Account ac = existingAccounts.getLoginAccountList().get(getInputLogin());
        Account.setCurrentAccount(ac);   // we work with current account
        ac.setIfNewAccount(false);       // not new because after identification
    }









}
