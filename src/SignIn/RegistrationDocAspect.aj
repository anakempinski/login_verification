package SignIn;


public aspect RegistrationDocAspect {

    pointcut regDocumentation(Registration reg) : call(public void Registration.registration()) && target(reg);

    after(Registration reg)  : regDocumentation(reg) {

        String regDate = reg.getDoc().convertion(System.currentTimeMillis());

        String content = "\n" + regDate + "    Registration was passed,    chosen login: " + reg.getInputLogin() +
                ",    chosen password: " + reg.getInputPassword();


        reg.getDoc().setContentForDocumentation(content);

    }


}
