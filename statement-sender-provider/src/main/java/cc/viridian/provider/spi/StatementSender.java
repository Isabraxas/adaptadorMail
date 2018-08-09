package cc.viridian.provider.spi;

import cc.viridian.provider.model.ResponseSendStatement;
import cc.viridian.provider.model.StatementDocument;

public interface StatementSender {

    public ResponseSendStatement sendStatement (
            StatementDocument statementDocument,
            String mimeType,
            String sendService
    ) throws Exception;
}
