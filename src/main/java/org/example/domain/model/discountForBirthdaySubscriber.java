package org.example.domain.model;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.domain.customInterface.Subscriber;
import com.sendgrid.*;
import java.io.IOException;

public class discountForBirthdaySubscriber implements Subscriber {
    private String name;
    private String email;
    private Mail mail;

    SendGrid sg = new SendGrid("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");
    Request request = new Request();
    public discountForBirthdaySubscriber(){
        this.name = "";
        this.email = "";
    }
    public discountForBirthdaySubscriber(String name, String email){
        this.name = name;
        this.email = email;
        Email from = new Email("zubanyszarylkasyn@gmail.com");
        Email to = new Email(this.email);
        String subject = "Sending Email with Twilio Sendgrid";
        Content content = new Content("text/plain", "Happy birthday");
        this.mail = new Mail(from, subject, to, content);
    }

    @Override
    public void notifySubscriber() {
        try{
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
// 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