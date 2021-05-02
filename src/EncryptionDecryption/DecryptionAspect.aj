package EncryptionDecryption;


import java.util.HashMap;
import java.util.Map;

public aspect DecryptionAspect {

    Map<String, String> decryptionMap = new HashMap<String, String>() {{
        put("a", "z"); put("b", "a"); put("c", "b"); put("d", "c"); put("e", "d"); put("f", "e"); put("g", "f");
        put("h", "g"); put("i", "h"); put("j", "i"); put("k", "j"); put("l", "k"); put("m", "l"); put("n", "m");
        put("o", "n"); put("p", "o"); put("q", "p"); put("r", "q"); put("s", "r"); put("t", "s"); put("u", "t");
        put("v", "u"); put("w", "v"); put("x", "w"); put("y", "x"); put("z", "y");
        put("0", "9"); put("1", "0"); put("2", "1"); put("3", "2"); put("4", "3"); put("5", "4"); put("6", "5");
        put("7", "6"); put("8", "7"); put("9", "8");

    }};




    pointcut decryptLogin(Decryption dec, String encLogin) : call(public void Decryption.setLoginToDec(String))
               && target(dec) && args(encLogin);

    after(Decryption dec, String encLogin) : decryptLogin(dec, encLogin){

        dec.setDecLogin(decryption(encLogin));

    }



    pointcut decryptPassword(Decryption dec, String encPassword) : call(public void Decryption.setPasswordToDec(String))
            && target(dec) && args(encPassword);

    after(Decryption dec, String encPassword) : decryptPassword(dec, encPassword){

        dec.setDecPassword(decryption(encPassword));

    }





    private String decryption(String input){

        StringBuilder sbInput = new StringBuilder(input.length());

        for(int i = 0; i < input.length(); i++){

            char curChar = input.charAt(i);

            for(String key : decryptionMap.keySet()){

                if(String.valueOf(curChar).equals(key)){

                    sbInput.append(decryptionMap.get(key));

                    break;

                }
            }
        }

        return sbInput.toString();
    }


}


