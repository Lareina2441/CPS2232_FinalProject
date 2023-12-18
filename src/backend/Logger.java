package backend;

import boat.Boat;
import exceptions.FailedTransactionException;
import exceptions.InvalidEmailAddress;
import interfaces.Person;
import person.Company;

import javax.mail.MessagingException;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.PriorityQueue;

/**
 * This class is used to write logs and send logs to the company or client
 *
 * @version 1.0
 * @since 2023-12-17
 *
 */

public class Logger implements Serializable {

    private PriorityQueue<LogMessage> logMessages = new PriorityQueue<>();
    CompanyEmailSender companyEmailSender = new CompanyEmailSender();
    Person person;

    public static void main(String[] args) throws MessagingException {
        Logger logger = new Logger(new Company(1,"CPS","2232"));
        logger.writeLog("test",1);
        logger.writeExceptionErrorAndTransLog(new Boat("make","variant",1,"region",1,1,1,1,1));
        logger.writeExceptionErrorAndTransLog(new FailedTransactionException("Failed Transaction"));
        logger.writeExceptionErrorAndTransLog(new Error("Error"));
        logger.writeLog("test",1);
        logger.writeExceptionErrorAndTransLog(new Boat("make","variant",1,"region",1,1,1,1,1));
        logger.writeExceptionErrorAndTransLog(new FailedTransactionException("Failed Transaction"));
        logger.writeExceptionErrorAndTransLog(new Error("Error"));
        logger.writeLog("test",1);
        logger.writeExceptionErrorAndTransLog(new Boat("make","variant",1,"region",1,1,1,1,1));
        logger.writeExceptionErrorAndTransLog(new FailedTransactionException("Failed Transaction"));
        logger.writeExceptionErrorAndTransLog(new Error("Error"));
        logger.writeLog("test",1);
        logger.writeExceptionErrorAndTransLog(new Boat("make","variant",1,"region",1,1,1,1,1));
        logger.writeExceptionErrorAndTransLog(new FailedTransactionException("Failed Transaction"));
        logger.writeExceptionErrorAndTransLog(new Error("Error"));
        logger.writeExceptionErrorAndTransLog(new Exception("Exception"));


    }

    public Logger(Person person) throws MessagingException {
        this.person = person;
    }

    public Logger() throws MessagingException {
    }

    public PriorityQueue<LogMessage> getLogMessages() {
        return logMessages;
    }

    public void writeLog(String message, int priority){
        LogMessage logMessage = new LogMessage();
        logMessage.setPriority(priority);
        logMessage.setMessage(message + "Time at" + LocalTime.now()+"\n");
        logMessages.add(logMessage);
        if (logMessages.size()>=10)
            try {
                sentLog();
            } catch (InvalidEmailAddress e) {
                writeExceptionErrorAndTransLog(e);
            }
    }

    /**
     *Error message: 5
     *FailedTransactionException: 4
     *Transaction succeed: 3
     *others: 2
     *own defined: 1
     */
    public void writeExceptionErrorAndTransLog(Object e){
        LogMessage logMessage = new LogMessage();
        if(e instanceof FailedTransactionException){
            logMessage.setPriority(4);
            logMessage.setMessage(((Exception)e).getMessage()+"Time at" + LocalTime.now()+"\n");
            logMessages.add(logMessage);
        }
        else if(e instanceof Error){
            logMessage.setPriority(5);
            logMessage.setMessage(e + "Time at" + LocalTime.now()+"\n");
            logMessages.add(logMessage);
        }
        else if (e instanceof Boat) {
            Boat boat = (Boat)e;
            logMessages.add(new LogMessage(" Make["+boat.getMake()+"] Variant["+boat.getVarient()+"] "
                    + "Year["+boat.getYear()+"] User[:"+boat.getUser()+"] Owner["+boat.getOwner()+"\n Transaction finished at " + LocalTime.now()+"\n",3));

            return;
        }
        else {
            logMessage.setPriority(2);
            logMessages.add(new LogMessage((e.toString()+"Time at" + LocalTime.now()+"\n"),2));
        }

        if (logMessages.size()>=10)
            try {
                sentLog();
            } catch (InvalidEmailAddress e1) {
                writeExceptionErrorAndTransLog(e1);
            }
    }

    public void sentLog() throws InvalidEmailAddress {
            String info = "";
            for (int i = 0; i < logMessages.size(); i++)
                info += logMessages.poll().getMessage()+"\n\n\n";

            companyEmailSender.sent(info,person);

    }


    class LogMessage implements Comparable<LogMessage>,Serializable{

        private int priority ;
        private String message;

        public LogMessage(String message, int priority) {
            this.message = message;
            this.priority = priority;
        }

        public LogMessage() {

        }

         public int getPriority(){
              return priority;
         }

         public String getMessage(){
              return message;
         }

         public void setMessage(String message){
              this.message = message;
         }

        public void setPriority(int priority){
            this.priority = priority;
        }

        /**
        * @param o the object to be compared.
        * @return
        */
        @Override
        public int compareTo(LogMessage o) {
           return this.priority - o.priority;
        }
    }


}
