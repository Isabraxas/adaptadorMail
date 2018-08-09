package cc.viridian.provider.statementsender;

import cc.viridian.provider.model.StatementDocument;
import cc.viridian.provider.statementsender.byTest.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class mailController {

    @Autowired
    private EmailSender emailSender;

    @RequestMapping("/sendPdf")
    public String sendPdfTest() throws Exception {
        // 1. tomar un archivo pdf y csv
        String filePath= new ClassPathResource("testPdf.pdf").getFile().toString();
        byte[] document= Convert.readBytesFromFile(filePath);

        // 2. convertir el documento a un Statement document
        StatementDocument statementDocument = new StatementDocument();
        statementDocument.setDocument(document);


        // 3. enviar el documento adjunto por correo electronico
        emailSender.sendStatement(statementDocument,".pdf","Self");

        return "Mensaje enviado";
    }

    @RequestMapping("/sendCsv")
    public String sendCsvTest() throws Exception{
        String response= null;
        try {
            // 1. tomar un archivo pdf y csv
            String filePath= new ClassPathResource("testCsv.csv").getFile().toString();
            byte[] document= Convert.readBytesFromFile(filePath);

            // 2. convertir el documento a un Statement document
            StatementDocument statementDocument = new StatementDocument();
            statementDocument.setDocument(document);


            // 3. enviar el documento adjunto por correo electronico
            emailSender.sendStatement(statementDocument,".csv","Self");

            response="Correo enviado satisfactoriamente";

        }catch (Exception e){
            response = "Error no se pudo enviar el correo";
            e.getStackTrace();
        }

        return response;
    }

}
