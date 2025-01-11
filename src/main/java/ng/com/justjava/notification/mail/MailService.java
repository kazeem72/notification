package ng.com.justjava.notification.mail;

import java.io.IOException;
import java.util.List;
import ng.com.justjava.notification.util.NotFoundException;
import ng.com.justjava.notification.util.SendGridMailHandler;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    private final MailRepository mailRepository;
    private final SendGridMailHandler sendGridMailHandler;

    public MailService(final MailRepository mailRepository, SendGridMailHandler sendGridMailHandler) {
        this.mailRepository = mailRepository;
        this.sendGridMailHandler = sendGridMailHandler;
    }

    public List<MailDTO> findAll() {
        final List<Mail> mails = mailRepository.findAll(Sort.by("id"));
        return mails.stream()
                .map(mail -> mapToDTO(mail, new MailDTO()))
                .toList();
    }

    public MailDTO get(final Long id) {
        return mailRepository.findById(id)
                .map(mail -> mapToDTO(mail, new MailDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Async
    public Long create(final MailDTO mailDTO) {
        final Mail mail = new Mail();
        mapToEntity(mailDTO, mail);
        try {
            sendGridMailHandler.sendMail(mailDTO);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return mailRepository.save(mail).getId();
    }

    public void update(final Long id, final MailDTO mailDTO) {
        final Mail mail = mailRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(mailDTO, mail);
        mailRepository.save(mail);
    }

    public void delete(final Long id) {
        mailRepository.deleteById(id);
    }

    private MailDTO mapToDTO(final Mail mail, final MailDTO mailDTO) {
        mailDTO.setId(mail.getId());
        mailDTO.setRemark(mail.getRemark());
        mailDTO.setMailFrom(mail.getMailFrom());
        mailDTO.setMailTo(mail.getMailTo());
        mailDTO.setMailContent(mail.getMailContent());
        mailDTO.setStatus(mail.getStatus());
        return mailDTO;
    }

    private Mail mapToEntity(final MailDTO mailDTO, final Mail mail) {
        mail.setRemark(mailDTO.getRemark());
        mail.setMailFrom(mailDTO.getMailFrom());
        mail.setMailTo(mailDTO.getMailTo());
        mail.setMailContent(mailDTO.getMailContent());
        mail.setStatus(mailDTO.getStatus());
        return mail;
    }

}
