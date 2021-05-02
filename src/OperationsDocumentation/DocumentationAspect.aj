package OperationsDocumentation;

import java.io.*;



public aspect DocumentationAspect  {


    pointcut documentation(Documentation doc, String content) :
           call(public void Documentation.setContentForDocumentation(String)) && target(doc) && args(content);

    after(Documentation doc, String content): documentation(doc, content){

        try {
            write(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Writing to file error");
            System.exit(1);
        }
    }


    private void write(String content) throws Exception{
        FileOutputStream file = new FileOutputStream("Documentation.txt", true);
        file.write(content.getBytes());
    }


}





