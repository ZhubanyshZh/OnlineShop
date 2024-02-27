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
    private Email from = new Email("zubanyszarylkasyn@gmail.com");

    private SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
    private Request request = new Request();
    public discountSubscriber(String name, String email){
        this.name = name;
        this.email = email;
    }
    public discountSubscriber(){
        this.name = "";
        this.email = "";
    }
    @Override
    public void notifySubscriber() {
        try{
            Email to = new Email(this.email);
            String subject = "Discount For your Birthday!!!";
            Content content = new Content("text/plain", "Happy birthday " + this.name);
            Mail mail = new Mail(from, subject, to, content);
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
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
}
