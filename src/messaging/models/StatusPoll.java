package messaging.models;

import java.util.Date;

public class StatusPoll {
    private final Date date;
    private final String message;

    public StatusPoll() {
        this.date = new Date();
        this.message = "RequestDeviceStatus";
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "StatusPoll{" +
                "date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
