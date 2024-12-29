package ng.com.justjava.notification.util;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import ng.com.justjava.notification.mail.MailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class SendGridMailHandler {

    @Value("${spring.sendgrid.api-key}")
    private String SENDGRID_API_KEY;

    @Value("${ng.com.justjava.sendgrid.fromName}")
    private String FROM_NAME;

    @Value("${ng.com.justjava.sendgrid.fromMail}")
    private String FROM_MAIL;
    public void sendMail2(MailDTO mailDTO) throws IOException{
        Email from = new Email(FROM_MAIL, FROM_NAME);
        String subject = mailDTO.getSubject();
        Email to = new Email(mailDTO.getMailTo().getAddress());
        Content content = new Content("text/plain", mailDTO.getMailContent().getBody());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
    public void sendMail(MailDTO mailDTO) throws IOException{
        Email from = new Email(FROM_MAIL, FROM_NAME);
        String subject = mailDTO.getSubject();
        Email to = new Email(mailDTO.getMailTo().getAddress());
        Content content = new Content("text/html", mailDTO.getMailContent().getBody());
        Mail mail = new Mail(from, subject, to, content);
        Personalization personalization = new Personalization();
        personalization.addTo(to);

        if(mailDTO.getPersonalization()!=null) {
            mailDTO.getPersonalization().forEach((key, value) -> {
                personalization.addDynamicTemplateData(key, value);
                System.out.println(" The key ===" + key + " value===" + value);
            });

            if (!mailDTO.getPersonalization().isEmpty())
                mail.addPersonalization(personalization);
        }
        if(mailDTO.getTemplateID()!=null)
            mail.setTemplateId(mailDTO.getTemplateID());

        mail.setFrom(new Email("kazeem.akinrinde72@gmail.com"));
        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
