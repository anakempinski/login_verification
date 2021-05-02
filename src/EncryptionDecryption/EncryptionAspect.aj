package EncryptionDecryption;

import java.util.HashMap;
import java.util.Map;

import EncryptionDecryption.Encryption;

public aspect EncryptionAspect {


    Map<String, String> encryptionMap = new HashMap<String, String>() {{
        put("a", "b"); put("b", "c"); put("c", "d"); put("d", "e"); put("e", "f"); put("f", "g"); put("g", "h");
        put("h", "i"); put("i", "j"); put("j", "k"); put("k", "l"); put("l", "m"); put("m", "n"); put("n", "o");
        put("o", "p"); put("p", "q"); put("q", "r"); put("r", "s"); put("s", "t"); put("t", "u"); put("u", "v");
        put("v", "w"); put("w", "x"); put("x", "y"); put("y", "z"); put("z", "a");
        put("0", "1"); put("1", "2"); put("2", "3"); put("3", "4"); put("4", "5"); put("5", "6"); put("6", "7");
        put("7", "8"); put("8", "9"); put("9", "0");

    }};




    pointcut encryptLogin(Encryption enc, String login) :
            call(public void Encryption.setLoginToEnc(String)) && target(enc) && args(login);

    after(Encryption enc, String login) : encryptLogin(enc, login){

        enc.setEncLogin(encryption(login));
    }


     pointcut encryptPassword(Encryption enc, String password) :
        call(public void Encryption.setPasswordToEnc(String)) && target(enc) && args(password);

    after(Encryption enc, String password) : encryptPassword(enc, password){

        enc.setEncPassword(encryption(password));
    }



    private String encryption(String input) {
        StringBuilder sbInput = new StringBuilder(input.length());
        for(int i = 0; i < input.length(); i++){
            char curChar = input.charAt(i);
            for(String key : encryptionMap.keySet()){
                if(String.valueOf(curChar).equals(key)){
                    sbInput.append(encryptionMap.get(key));
                    break;
                }
            }
        }
        return sbInput.toString();
    }




    pointcut loginEncDocumentation(Encryption enc, String login) :
            call(public void Encryption.setLoginToEnc(String)) && target(enc) && args(login);

    after(Encryption enc, String login) : loginEncDocumentation(enc, login){

    String curTime = enc.getDoc().convertion(System.currentTimeMillis());

    String content = "\n" + curTime +  "    Login after encryption: " + enc.getEncLogin();

    enc.getDoc().setContentForDocumentation(content);

    }


    pointcut passwordEncDocumentation(Encryption enc, String password) :
            call(public void Encryption.setPasswordToEnc(String)) && target(enc) && args(password);

    after(Encryption enc, String login) : passwordEncDocumentation(enc, login){

        String curTime = enc.getDoc().convertion(System.currentTimeMillis());

        String content = "\n" + curTime +  "    Password after encryption: " + enc.getEncPassword();

        enc.getDoc().setContentForDocumentation(content);
    }

}
