package ng.com.justjava.notification.mail;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface MailRepository extends MongoRepository<Mail, Long> {
}
