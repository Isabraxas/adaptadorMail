package cc.viridian.provider.model;

public class ResponseSendStatement {
    private boolean isSend;
    
    private String status;
    private String description;

    public ResponseSendStatement() {
    }

    public ResponseSendStatement(boolean isSend, String status, String description) {
        this.isSend = isSend;
        this.status = status;
        this.description = description;
    }


    public boolean getSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResponseSendStatement{" +
                "isSend=" + isSend +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
