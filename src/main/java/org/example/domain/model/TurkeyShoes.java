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

public class TurkeyShoes implements ShoesInterface {
    private Email from = new Email("zubanyszarylkasyn@gmail.com");
    ApiKeys SEND_GRID_API_KEY = new ApiKeys();
    private SendGrid sg = new SendGrid(SEND_GRID_API_KEY.getApiKeySendgrid());
    private Request request = new Request();

    @Override
    public void orderToShoes(String link, String email) {
        try{
            Email to = new Email(email);
            String subject = "Order To Shoes";
            Content content = new Content("text/plain", "Sevgili tedarikçi,\n" +
                    "\n" +
                    "Adım Zhubanysh ve Kazakistan'daki Men's Shop şirketini temsil ediyorum. Belirli ürünleri sipariş etmek istiyoruz ve sizinle işbirliği olasılığını görüşmek istiyoruz.\n" +
                    "\n" +
                    "Lütfen bu e-postaya sizden sipariş etmek istediğimiz ürün tasarımlarını içeren bir dosya ekleyin. Dosya gerekli teknik ve tasarım özelliklerini içermektedir. Deneyiminizin ve profesyonelliğinizin, beklentilerimizi karşılayan yüksek kaliteli ürünler elde etmemize yardımcı olacağından eminiz.\n" +
                    "\n" +
                    "Sipariş etmeyi planladığımız malların miktarı belgede belirtilmiştir. Lütfen bize fiyatların, ödeme koşullarının ve teslimat sürelerinin ayrıntılı bir dökümünü içeren bir teklif verin. Ayrıca minimum sipariş miktarı ve büyük miktarlar için olası indirimler hakkındaki bilgiler de ilginizi çekiyor.\n" +
                    "\n" +
                    "Başarılı bir işbirliği umuyoruz ve yanıtınızı bekliyoruz. Herhangi bir sorunuz varsa veya ek bilgiye ihtiyaç duyarsanız benimle @zubanyszarylkassyn@gmail.com veya 87783500809 adresinden iletişime geçmekten çekinmeyin.\n" +
                    "\n" +
                    "Talebimize gösterdiğiniz ilgi için teşekkür ederiz ve şirketinizle çalışmayı sabırsızlıkla bekliyoruz.\n" +
                    "\n" +
                    "Samimi olarak,\n" +
                    "\n" +
                    "Zubanış\n" +
                    "Yönetici\n" +
                    "Erkek Mağazası");
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
