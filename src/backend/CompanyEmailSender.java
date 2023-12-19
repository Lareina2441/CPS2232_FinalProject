package backend;

import exceptions.InvalidEmailAddress;
import interfaces.Person;
import person.Client;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Properties;

/**
 * Purpose: This class is used to send email to the client or company administrator
 * @version 1.0
 * @since 2023-12-17
 */

public class CompanyEmailSender implements Serializable {
    final String username = "define your own outlook sender";
    final String password ="password";


    public CompanyEmailSender() throws MessagingException {
    }

    /**
     * This method is used to send email to the client or company administrator
     * @param string the content of the email
     * @param person the person who will receive the email
     * @throws InvalidEmailAddress if the email address is invalid
     */
    public void sent(String string,Person person) throws InvalidEmailAddress {
        if (isValidEmail(person.getEmail())==-1){
            throw new InvalidEmailAddress("Invalid email address");
        }
        printProgressBar();
        // Get the default Session object.
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(username));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(person.getEmail()));
            if (person instanceof Client){
                // Set Subject: header field
                message.setSubject("Notification from SeaCraft Company");

                // Now set the actual message
                message.setText(string);

                // Send message
                Transport.send(message);
            }
            else {
                message.setSubject("SeaCraft Company management logs");

                // Now set the actual message
                message.setText(string);

                // Send message
                Transport.send(message);
            }
            System.out.println(".......100% Task completed!");
            System.out.println("Email sent successfully!");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //fake progress bar to show the process of the program, stop at one point when finish print 100%
    public static void printProgressBar() {
        for (int i = 0; i <= 45; i++) {
            updateProgressBar(i, 45);
            sleep(10);
        }
    }
    //progress bar in the console, it is a fake progress bar, just for relax the user
    public static void updateProgressBar(int currentStep, int totalSteps) {
        int barLength = 66;
        int progress = (int) ((double) currentStep / totalSteps * barLength);

        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("] ");

        int percent = (int) (((double) currentStep / totalSteps) * 100);
        if (percent > 95) {
            return;
        }
        progressBar.append(percent).append("%");

        System.out.print("\r" + progressBar);
        System.out.flush();
    }

    //used for progress bar
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is used to check whether the email address is valid
     * @param address the email address
     * @return -1 if the email address is invalid, 1 if the email address is valid
     */
    public static int isValidEmail(String address) {
        int returnValue = 1;//this is for end output
        //idea: if invalid then give return value -1 else valid.

        //first:find out the place of "@"
        int placeOfAt = address.indexOf('@');
        if (placeOfAt == -1)
            return -1;//return -1 if invalid
        int lengthOfAddress = address.length();


        //substring 2 part
        String part1 = address.substring(0, placeOfAt);
        String part2 = address.substring(placeOfAt + 1);

        //judgement one: @ and '.' can't be begin or end
        //pickout 1st and end character check whether @or.
        char beginning = address.charAt(0);
        char ending = address.charAt(lengthOfAddress - 1);
        if (beginning == '.' || ending == '.' || beginning == '@' || ending == '@')
            return -1;


        //judgment two:all characters in part1/2 are digit,letter or '.'.
        //using for loop to check if the each are matched
        int lengthOfPart1 = part1.length();
        for (int begNum = 0; begNum < lengthOfPart1; begNum++) {
            char eachCharacter = part1.charAt(begNum);
            boolean digitJudge = Character.isDigit(eachCharacter);
            boolean letterJudge = Character.isLetter(eachCharacter);
            boolean dotJudge = (int) eachCharacter == 46;
            if (digitJudge && letterJudge && dotJudge == false) {
                begNum = lengthOfPart1 + 1;
                returnValue = -1;
            }
        }

        int lengthOfPart2 = part2.length();
        for (int begNum = 0; begNum < lengthOfPart2; begNum++) {
            char eachCharacter = part2.charAt(begNum);
            boolean digitJudge = Character.isDigit(eachCharacter);
            boolean letterJudge = Character.isLetter(eachCharacter);
            boolean dotJudge = (int) eachCharacter == 46;
            if (digitJudge && letterJudge && dotJudge == false) {
                begNum = lengthOfPart1 + 1;
                returnValue = -1;
            }
        }

        //judgement three: . cannot beside @
        //end of part1/begin of part2 not '.'
        char endOfPart1 = part1.charAt(lengthOfPart1 - 1);
        char beginOfPart2 = part2.charAt(0);

        if (endOfPart1 == '.' || beginOfPart2 == '.')
            returnValue = -1;


        if (returnValue == -1)
            return returnValue;//return -1 if invalid

        return returnValue;
    }


}
