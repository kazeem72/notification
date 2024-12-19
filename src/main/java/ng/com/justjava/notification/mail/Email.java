package ng.com.justjava.notification.mail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class Email {

    @NotNull
    @Size(max = 255)
    private String address;

}
