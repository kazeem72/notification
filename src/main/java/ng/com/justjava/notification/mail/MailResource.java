package ng.com.justjava.notification.mail;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/mails", produces = MediaType.APPLICATION_JSON_VALUE)
public class MailResource {

    private final MailService mailService;

    public MailResource(final MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public ResponseEntity<List<MailDTO>> getAllMails() {
        return ResponseEntity.ok(mailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MailDTO> getMail(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(mailService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createMail(@RequestBody @Valid final MailDTO mailDTO) {
        final Long createdId = mailService.create(mailDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateMail(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final MailDTO mailDTO) {
        mailService.update(id, mailDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMail(@PathVariable(name = "id") final Long id) {
        mailService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
