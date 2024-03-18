package org.example.domain.model;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.ApiKeys;
import org.example.domain.customInterface.ShoesInterface;

import java.io.IOException;
import java.util.HashMap;

public class ChinaShoes implements ShoesInterface {
    private Email from = new Email("zubanyszarylkasyn@gmail.com");
    ApiKeys SEND_GRID_API_KEY = new ApiKeys();
    private SendGrid sg = new SendGrid(SEND_GRID_API_KEY.getApiKeySendgrid());
    private Request request = new Request();


    @Override
    public void orderToShoes(String linkToFile, String email) {
        try{
            Email to = new Email(email);
            String subject = "Order To Shoes";
            Content content = new Content("text/plain", "尊敬的供应商，\n" +
                    "\n" +
                    "我叫 Zhubanysh，我代表哈萨克斯坦的 Men's Shop 公司。 我们有兴趣订购某些产品，并愿意与您讨论合作的可能性。\n" +
                    "\n" +
                    "请在此电子邮件中附上我们希望向您订购的产品设计文件。 该文件包含必要的技术和设计规范。 我们相信，您的经验和专业精神将帮助我们获得满足我们期望的高质量产品。\n" +
                    "\n" +
                    "我们计划订购的货物数量已在文件中注明。 请向我们提供报价单，其中详细说明价格、付款条件和交货时间。 您还对有关最小订购量和大批量可能的折扣的信息感兴趣。\n" +
                    "\n" +
                    "我们希望合作成功，并等待您的回复。 如果您有任何疑问或需要更多信息，请随时通过@zubanyszarylkassyn@gmail.com 或87783500809 与我联系。\n" +
                    "\n" +
                    "感谢您关注我们的要求，我们期待与贵公司合作。\n" +
                    "\n" +
                    "真挚地，\n" +
                    "\n" +
                    "朱巴内什\n" +
                    "行政人员\n" +
                    "男装店");
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
}
