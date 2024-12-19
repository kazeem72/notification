package ng.com.justjava.notification.mail;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("mails")
@Getter
@Setter
public class Mail {

    @Id
    private Long id;

    @Size(max = 255)
    private String remark;

    @NotNull
    @Valid
    private Email mailFrom;

    @NotNull
    @Valid
    private Email mailTo;

    @NotNull
    @Valid
    private Content mailContent;

    private SendStatus status;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
