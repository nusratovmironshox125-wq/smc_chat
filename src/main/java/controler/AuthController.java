package controler;

import dto.LoginDTO;
import dto.UserDTO;
import enums.UserRole;
import service.AuthService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sound.midi.MidiMessage;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static utils.Utils.*;


public class AuthController {

    private final AuthService authService =AuthService.getAuthService();



    public  void  authMenu(){
        while (true){
            System.out.println("""
                    1.Login
                    2.Registration
                    3.Exit
                    """);
            int menu=getNum("Choos One");
            switch (menu){
                case 1->login();
                case 2->registration();
                case 0->{return;}
            }
        }
    }

    public void login(){
        String email=getStr("Enter email");
        String password=getStr("Enter password");
        LoginDTO dto = new LoginDTO(email , password);

//       Optional<UserRole>optional = authService.login(dto);

//       29

    }

    public void registration(){
        String fullName=getStr("Enter full name");
        String email=getStr("Enter email");
        String password=getStr("Enter password");

        int randomNumber = new Random().nextInt(10000, 100000);
        sendMessege(email,randomNumber);


        LocalTime localTime = LocalTime.now().plusMinutes(1);

        int number = getNum("Enter cod");

        if (localTime.isAfter(LocalTime.now()) && number==randomNumber){
            System.out.println("OK");
            UserDTO dto = new UserDTO(fullName,email,password);
          boolean res = authService.registration(dto);
          if (res){
              System.out.println("Success");
          }else {
              System.out.println("Registration error");
          }
        }else {
            System.out.println("Error");
        }


    }


/***
 *  metod 1 (uzim yozganim)
 */
   /* private void  sendMassage(String email,int num){

        Properties properties = new Properties();
        properties.put("mail.smtp.host","sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.post","2525");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.auth","true");

        String  userName="f8c688858f2883";
        String  password="a19bae63f94fda";

        Session session = getSession(properties , userName , password);
        Message message = new MidiMessage(session);

        try {
            message.setSubject("This is subject");
            message.setContent("<h1 style=\"color:red;\"> Body of mail here: "+num+" "+email+" ");

            message.setFrom( num InternetAddress());

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email) )

        }


    }*/




    /***
     * metod 2
     *
     *  i qizil metodlarni import qilish kerak
     */
    //++++++++++++++++++++++++++++++++++++++++++++
    private  void  sendMessege(String email,int num){

    Properties properties=new Properties();
    properties.put("mail.smtp.host","sandbox.smtp.mailtrap.io");
    properties.put("mail.smtp.port","2525");
    properties.put("mail.smtp.starttls.enable","true");
    properties.put("mail.smtp.auth","true");

    String  userName="9e27be4a8a4d56";
    String password="fa4c55f9783d72";
    Session session=getSession(properties,userName,password);

    Message message=new MimeMessage(session);

    try {
        message.setSubject("This is subject");
        message.setContent("<h1 style=\"color:red;\"> Body of mail here: "+num+"</h1>","text/html");
        message.setFrom(new InternetAddress("akmalrajabov017@mail.com"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }

    CompletableFuture.runAsync(()->{
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    });
    System.out.println("Messege send successfuly");

}

    private static  Session getSession(Properties properties,String userName,String password){
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return  new PasswordAuthentication(userName,password);
            }
        });
    }
//+++++++++++++++++++++++++++++++++++














}
