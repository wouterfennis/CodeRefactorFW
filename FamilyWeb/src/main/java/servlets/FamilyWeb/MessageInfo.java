package servlets.FamilyWeb;

public class MessageInfo {
    private final String messageType;
    private final String message;

    /**
     * @param messageType String type of message could be success, error or warning
     * @param message String the message to display
     */
    public MessageInfo(String messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }
}
