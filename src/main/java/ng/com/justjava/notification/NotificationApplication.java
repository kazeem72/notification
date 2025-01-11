package ng.com.justjava.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class NotificationApplication {

    public static void main(final String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

}
