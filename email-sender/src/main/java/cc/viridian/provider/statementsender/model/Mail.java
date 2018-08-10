package cc.viridian.provider.statementsender.model;

import lombok.*;

/**
 * Class that defines mail structure
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;

}
