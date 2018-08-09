package cc.viridian.provider.statementsender;

import cc.viridian.provider.model.ResponseSendStatement;
import cc.viridian.provider.model.StatementDocument;
import cc.viridian.provider.spi.StatementSender;
import cc.viridian.provider.statementsender.model.Mail;
import cc.viridian.provider.statementsender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailSender implements StatementSender {

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.to}")
    private String emailTo;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseSendStatement sendStatement(StatementDocument statementDocument, String mimeType, String sendService){

        ResponseSendStatement responseSendStatement = new ResponseSendStatement();
        Mail mail = new Mail();
        try {
            mail.setFrom(emailFrom);
            mail.setTo(emailTo);
            mail.setSubject("Statement XX/XX/XX to XX/XX/XX");
            mail.setContent("Extracto bancario de DumyBank y documento adjunto");

            if(mimeType.contains("pdf")) {
                emailService.sendPdfAttached(mail, statementDocument.getDocument());
            }else {
                emailService.sendCsvAttached(mail, statementDocument.getDocument());
            }

            responseSendStatement.setSend(true);
            responseSendStatement.setStatus("successful");
            responseSendStatement.setDescription("El correo con el archivo adjunto fuen enviado satisfactoriamente");

        } catch (MessagingException me){
            responseSendStatement.setSend(false);
            responseSendStatement.setStatus("error");
            responseSendStatement.setDescription("El envio del correo tuvo un error: "+me.getMessage());
            me.printStackTrace();

        } catch (Exception e){
            responseSendStatement.setSend(false);
            responseSendStatement.setStatus("error");
            responseSendStatement.setDescription(e.getMessage());
            e.printStackTrace();
        }

        return responseSendStatement;
    }
}
