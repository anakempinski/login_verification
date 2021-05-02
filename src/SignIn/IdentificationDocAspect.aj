package SignIn;


public aspect IdentificationDocAspect {

    pointcut documentation(Identification id) : call(public void Identification.identification())
            && target(id);

    after(Identification id) : documentation(id){

        String content = " ";
        String idDate = id.getDoc().convertion(System.currentTimeMillis());

        if(id.getIfIdentificationPassed()){

            content = "\n" + idDate + "    Identification was passed,    login: " + id.getInputLogin() +
                 ",    password: " + id.getInputPassword();
        }
        else{
            content = "\n" + idDate + " Identification failed";
        }

        id.getDoc().setContentForDocumentation(content);


    }

}
