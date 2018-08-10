package cc.viridian.provider.model;


import lombok.*;

import java.util.Arrays;

/**
 * Class to wrap the document to send
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatementDocument {
    private byte[] document;

}
