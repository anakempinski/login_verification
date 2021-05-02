package EncryptionDecryption;


import OperationsDocumentation.Documentation;

public class Encryption {

    private String loginToEnc;
    private String passwordToEnc;

    private String encLogin;
    private String encPassword;

    Documentation doc = new Documentation();

    public Encryption(){

    }

    public void setLoginToEnc(String login){
        this.loginToEnc = login;
    }

    public void setPasswordToEnc(String password){
        this.passwordToEnc = password;
    }

    public void setEncLogin(String login){
        this.encLogin = login;
    }

    public void setEncPassword(String password){
        this.encPassword = password;
    }

    public String getEncLogin(){
        return encLogin;
    }

    public String getEncPassword(){
        return encPassword;
    }

    public Documentation getDoc(){
        return doc;
    }


}
