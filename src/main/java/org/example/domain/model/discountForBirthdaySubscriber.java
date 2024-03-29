package org.example.domain.model;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.domain.customInterface.Subscriber;
import com.sendgrid.*;
import java.io.IOException;
import org.example.*;

public class discountForBirthdaySubscriber implements Subscriber {
    private String name;
    private String email;
    private Email from = new Email("zubanyszarylkasyn@gmail.com");
    private int percent;

    private SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
    private Request request = new Request();

    public discountForBirthdaySubscriber(){
        this.name = "";
        this.email = "";
    }

    public discountForBirthdaySubscriber(String name, String email, int percent){
        this.name = name;
        this.email = email;
        this.percent = percent;
    }
    @Override
    public void notifySubscriber() {
        try{
            Email to = new Email(this.email);
            String subject = "Дорогой клиент,\n" +
                    "\n" +
                    "Поздравляем вас с днем рождения! В этот особенный для вас день хотим подарить не только наши самые теплые пожелания, но и эксклюзивную скидку на ваш следующий шопинг в нашем магазине.\n" +
                    "\n" +
                    "Получите 20% скидку на весь ассортимент. Пусть этот день будет ярким и наполнен радостью, а наши стильные предложения добавят шарма вашему праздничному образу.\n" +
                    "\n" +
                    "С наилучшими пожеланиями,\n" +
                    "Men's shop";
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}