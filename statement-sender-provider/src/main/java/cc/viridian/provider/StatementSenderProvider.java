package cc.viridian.provider;

import cc.viridian.provider.model.ResponseSendStatement;
import cc.viridian.provider.model.StatementDocument;
import cc.viridian.provider.spi.StatementSender;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class StatementSenderProvider {

    private static StatementSenderProvider service;
    private ServiceLoader<StatementSender> loader;

    private StatementSenderProvider() {
        loader = ServiceLoader.load(StatementSender.class);
    }

    public static synchronized StatementSenderProvider getInstance() {
        if (service == null) {
            service = new StatementSenderProvider();
        }
        return service;
    }

    public ResponseSendStatement sendStatement(StatementDocument statementDocument, String mimeType, String sendService){
        ResponseSendStatement responseSendStatement = null;
        boolean processed = false;

        try {
            Iterator<StatementSender> statementSenders = loader.iterator();
            while (processed == false && statementSenders.hasNext()){
                StatementSender ss= statementSenders.next();
                responseSendStatement = ss.sendStatement(statementDocument, mimeType, sendService );
                if(responseSendStatement != null){
                    processed = true;
                }
            }

        }catch (ServiceConfigurationError serviceError) {
            responseSendStatement = null;
            serviceError.printStackTrace();

        } catch (Exception e) {
            responseSendStatement = null;
            e.printStackTrace();
        }

        return responseSendStatement;
    }

}
