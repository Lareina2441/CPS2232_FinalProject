package backend;

import exceptions.InvalidEmailAddress;
import interfaces.Person;
import person.Client;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CompanyEmailSender{
    final String username = "seacraft2018@outlook.com";
    final String password ="cps2232finalproject";


    public CompanyEmailSender() throws MessagingException {
    }

    public void sent(String string,Person person) throws InvalidEmailAddress {
        if (isValidEmail(person.getName())==-1){
            throw new InvalidEmailAddress("Invalid email address");
        }
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(person.getName()));
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

            System.out.println("Email sent successfully!");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static int isValidEmail(String address) {
        int returnValue = 1;//this is for end output
        //idea: if invalid then give return value -1 else valid.

        //first:find out the place of "@"
        int placeOfAt = address.indexOf('@');
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
