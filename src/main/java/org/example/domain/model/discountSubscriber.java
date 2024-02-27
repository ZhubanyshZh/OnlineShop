package org.example.domain.model;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.domain.customInterface.Subscriber;

import java.io.IOException;

public class discountSubscriber implements Subscriber {
    private String name;
    private String email;
    private Email from = new Email("Mens_Shop@official");

    private SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
    private Request request = new Request();
    private int pr;

    public discountSubscriber(String name, String email, int pr){
        this.name = name;
        this.email = email;
        this.pr = pr;
    }
    public discountSubscriber(){
        this.name = "";
        this.email = "";
        this.pr = 0;
    }
    @Override
    public void notifySubscriber() {
        try{
            Email to = new Email(this.email);
            String header = "Скидка для наших подписчиков";
            Content content = new Content("text/plain", "\"Уважаемый,"+this.name+", \n" +
                    "получите скидку "+this.pr+"  на всю весеннюю коллекцию! \n" +
                    "Акция действует до 22 марта\"");
            Mail mail = new Mail(from,header,to,content);
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response r = sg.api(request);
            System.out.println(r.getStatusCode());
            System.out.println(r.getBody());
            System.out.println(r.getHeaders());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }
}
