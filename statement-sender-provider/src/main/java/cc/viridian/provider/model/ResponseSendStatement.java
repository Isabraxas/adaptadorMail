package cc.viridian.provider.model;

import lombok.*;

/**
 * Response of the bank statement message
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSendStatement {
    private boolean isSend;
    
    private String status;
    private String description;


}
