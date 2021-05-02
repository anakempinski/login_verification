package EncryptionDecryption;


public class Decryption {

    private String loginToDec;
    private String passwordToDec;

    private String decLogin;
    private String decPassword;

    public Decryption(){

    }

    public void setLoginToDec(String login){
        this.loginToDec = login;
    }

    public void setPasswordToDec(String password){
        this.passwordToDec = password;
    }

    public void setDecLogin(String login){
        this.decLogin = login;
    }

    public void setDecPassword(String password){
        this.decPassword = password;
    }

    public String getDecLogin(){
        return decLogin;
    }

    public String getDecPassword(){
        return decPassword;
    }
}
