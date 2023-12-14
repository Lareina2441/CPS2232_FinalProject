package backend;

import java.util.Date;

public class Log {
    private String boatMessage;
    private Date date;

    public Log(String boatMessage, Date date) {
        this.boatMessage = boatMessage;
        this.date = date;
    }

    public Log() {
    }

    public String getBoatMessage() {
        return boatMessage;
    }

    public void setBoatMessage(String boatMessage) {
        this.boatMessage = boatMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
