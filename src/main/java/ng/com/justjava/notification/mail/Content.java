package ng.com.justjava.notification.mail;

import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class Content {

    @Size(max = 255)
    private String body;

}
