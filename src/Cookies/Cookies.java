//package Cookies;
//
//
//import Account.Account;
//import OperationsDocumentation.Documentation;
//import SignIn.*;
//
//import java.io.*;
//import java.util.HashMap;
//
//public class Cookies {
//
//    // after first idenification programm will create file with session id (will create it randomly)
//    // the idea of cookies is to keep us authenticated, so after first identification we'll get cookie
//    // which contains session id. Next time when we'll want to enter site that sent us cookie --> there is
//    // a check if our computer contains cookie that he sent us before. If we have this cookie --> we'll
//    // connect to site without identification
//
//
//
//
//    private String sessionID;
//    private String ipAddress;
//
//    Documentation doc = new Documentation();
//
//   // HashMap<String, Account> ipAccountList = new HashMap<String, Account>();
//
//    public Cookies(){
//
//    }
//
//
//
//    public void setSessionID(String id){
//        this.sessionID = id;
//    }
//
//    public Documentation getDoc(){
//        return doc;
//    }
//
//    public String getSessionID(){
//        return sessionID;
//    }
//
//    public String getIpAddress(){
//        return ipAddress;
//    }
//
//    public void setIpAddress(String address){
//        this.ipAddress = address;
//    }
//
//    public HashMap<String, Account> getIpAccountList(){
//        return ipAccountList;
//    }
//
//
//    // !!!!!
//    public void createCookie(){
//
//    }
//
//
//
//    public boolean checkIfAccountHasCookie(String ipAddress) throws IOException {
//
//        if(ipAccountList.containsKey(ipAddress)){
//            return true;
//        }
//        return false;
//    }
//
//
//
//}
