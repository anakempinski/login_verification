package OperationsDocumentation;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Documentation {

    private String content;


    public Documentation(){

    }


    public String getContent(){
        return content;
    }


    public void setContentForDocumentation(String content){
        this.content = content;
    }



    public String convertion(long date){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resDate = new Date(date);
        return sdf.format(resDate);
    }



    }
