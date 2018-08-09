package cc.viridian.provider.model;


import java.util.Arrays;

public class StatementDocument {
    private byte[] document;

    public StatementDocument() {
    }

    public StatementDocument(byte[] document) {
        this.document = document;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "StatementDocument{" +
                "document=" + Arrays.toString(document) +
                '}';
    }
}
