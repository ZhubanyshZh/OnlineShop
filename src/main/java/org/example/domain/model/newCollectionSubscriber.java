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

public class newCollectionSubscriber implements Subscriber {
    private String name;
    private String email;
    private Email from = new Email("zubanyszarylkasyn@gmail.com");
    private SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
    private Request request = new Request();
    private String value = "Дорогой ," + this.name + "\n" +
            "\n" +
            "У НАС ВЕЛИКОЛЕПНЫЕ НОВОСТИ\n" +
            "Свежая волна стиля пришла в наш онлайн магазин! Очаровывайтесь последними трендами, погружайтесь в океан уникальных дизайнов и создавайте свою неповторимую историю стиля.\n" +
            "\n" +
            "Экспериментируйте с разнообразием стилей, играйте цветами, подчеркивайте индивидуальность.\n" +
            "\n" +
            "\n" +
            "Благодарим за вашу страсть к моде!\n" +
            "\n" +
            "С любовью и стилем,\n" +
            "Men’s shop.";

    public newCollectionSubscriber(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public newCollectionSubscriber() {
        this.name = "";
        this.email = "";
    }

    @Override
    public void notifySubscriber() {
        try{
            Email to = new Email(this.email);
            String subject = "New Collection";
            Content content = new Content("text/plain", this.value);
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

    public static void main(String[] args) {
        System.out.println(System.getenv("SEND_GRID_API_KEY"));
    }
}
