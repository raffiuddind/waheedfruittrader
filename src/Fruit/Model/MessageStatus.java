/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.Model;

/**
 *
 * @author Raffiuddin
 */
public class MessageStatus {
    
    private String Id;
    private String messageType;
    private String fromNo;
    private String toNo;
    private String status;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getFromNo() {
        return fromNo;
    }

    public void setFromNo(String fromNo) {
        this.fromNo = fromNo;
    }

    public String getToNo() {
        return toNo;
    }

    public void setToNo(String toNo) {
        this.toNo = toNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MessageStatus{" + "Id=" + Id + ", messageType=" + messageType + ", fromNo=" + fromNo + ", toNo=" + toNo + ", status=" + status + '}';
    }
    
}
