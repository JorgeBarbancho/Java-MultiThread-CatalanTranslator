package ioc.dam.m9.uf3.eac2.b1;

/**
 *
 * @author Usuari
 */

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

public class EnviarMail {

    public static void main(String[] args) {
        String destinatari =  "jbarbancho@gmail.com"; //A qui vols escriure.
        String asumpte = "Correu de prova enviat desde Java";
        String cos = "Això és una prova de correu...";

        enviarAmbGMail(destinatari, asumpte, cos);
    }

    private static void enviarAmbGMail(String destinatari, String asumpte, String cos) {

        Properties props = System.getProperties();

        //És el que va davant @gmail.com en el teu compte de correu. És el remitent també.
        props.setProperty("mail.smtp.user", "jorge.dam.test@gmail.com");

        //Para la direcció nomcompte@gmail.com
        props.setProperty("mail.smtp.to", destinatari);    

        //El servidor SMTP de Google
        props.setProperty("mail.smtp.host", "smtp.Gmail.com");

        //La clau del compte
        props.setProperty("mail.smtp.password", "correoprueba");

        //Utilitzar autenticació mitjançant usuari i clau
        props.setProperty("mail.smtp.auth", "true");

        //Per connectar de manera segura al servidor SMTP
        props.setProperty("mail.smtp.starttls.enable", "true");

        //El port SMTP segur de Google
        props.setProperty("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props);
       
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(session.getProperty("mail.smtp.user")));
            message.setRecipients(Message.RecipientType.TO, session.getProperty("mail.smtp.to"));
            message.setSubject(asumpte, "UTF-8");
            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(cos, "text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            message.setContent(mp);
            
            Transport transport = session.getTransport("smtp");
            transport.connect(session.getProperty("mail.smtp.user"), session.getProperty("mail.smtp.password"));
            Address [] addresses = new Address[1];
            addresses[0] = new InternetAddress(session.getProperty("mail.smtp.to"));
            transport.sendMessage(message, addresses);
            transport.close();
            
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}

