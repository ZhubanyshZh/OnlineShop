package org.example.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.example.dto.NotificationDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final LoggedUserManagementService loggedUserManagementService;
    private Email from = new Email("zubanyszarylkasyn@gmail.com");
    private SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
    private Request request = new Request();

    public boolean notifyUsers(NotificationDto notificationDto){
        List<User> newsNotifySubscribers = userRepository.findUsersByNewsNotification("true");

        String Header = notificationDto.getHeader();
        String value = notificationDto.getContent();
        Content content = new Content("text/plain", value);


        for(User u : newsNotifySubscribers){
            try{
                Email to = new Email(u.getEmail());
                Mail mail = new Mail(from, Header, to, content);
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            }catch(IOException ex){
                ex.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public boolean sendCodeToEmail(String email) {
        try{
            User user = userRepository.findUsersByEmail(email);

            if(user!=null){
                Email to = new Email(user.getEmail());
                Long randomLongCode = (long) (Math.random()*1000000);
                loggedUserManagementService.setCodeToConfirmEmail(randomLongCode);
                loggedUserManagementService.setEmail(email);
                String value = "Code for confirm email \nCode: "+randomLongCode;
                Content content = new Content("text/plain" ,value);
                Mail mail = new Mail(from, "Change Password", to, content);
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            }else return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
