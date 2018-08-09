package cc.viridian.provider.statementsender.service;

import cc.viridian.provider.statementsender.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;


    public void sendPdfAttached(Mail mail, byte[] bytesfile) throws MessagingException {

       this.sendPdfAttached(mail, bytesfile,"pdf-statement-document");

    }

    public void sendPdfAttached(Mail mail, byte[] bytesfile, String nameAttachedDoc) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());

        helper.addAttachment(nameAttachedDoc+".pdf",new ByteArrayResource(bytesfile), MediaType.APPLICATION_PDF.toString());
        emailSender.send(message);

    }


    public void sendCsvAttached(Mail mail, byte[] bytesfile) throws MessagingException {

        this.sendCsvAttached(mail, bytesfile, "csv-statement-document");

    }

    public void sendCsvAttached(Mail mail, byte[] bytesfile, String nameAttachedDoc) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());

        helper.addAttachment(nameAttachedDoc+".csv",new ByteArrayResource(bytesfile),"text/csv");
        emailSender.send(message);

    }

}
