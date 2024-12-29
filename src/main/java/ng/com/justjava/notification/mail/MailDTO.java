package ng.com.justjava.notification.mail;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;


@Getter
@Setter
public class MailDTO {

    private Long id;
    @Size(max = 255)
    private String remark;
    private Email mailFrom;
    @NotNull
    @Valid
    private Email mailTo;
    @NotNull
    @Valid
    private Content mailContent;
    @NotNull
    @Valid
    private String subject;
    private SendStatus status;

    private Map<String, Object> personalization;

    private String templateID;

}
