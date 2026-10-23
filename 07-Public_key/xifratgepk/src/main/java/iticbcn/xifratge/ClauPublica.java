package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

public class ClauPublica {
    public static void main(String[] args) {
        ClauPublica cp = new ClauPublica();

        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";

        KeyPair parellClaus;
        try {
            parellClaus = cp.generaParellClausRSA();
        } catch (Exception e) {
            System.out.println("Error generant el parell de claus: " + e.getMessage());
            return;
        }

        byte[] msgXifrat;
        try {
            msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());
        } catch (Exception e) {
            System.out.println("Error xifrant el missatge: " + e.getMessage());
            return;
        }

        System.out.println("===============================");
        System.out.print("Text xifrat: ");
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat));

        String msgDesxifrat;
        try {
            msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate());
        } catch (Exception e) {
            System.out.println("Error desxifrant el missatge: " + e.getMessage());
            return;
        }
        
        System.out.println("===============================");
        System.out.println("Text desxifrat: " + msgDesxifrat);

        String strClauPub = DatatypeConverter.printHexBinary(
            parellClaus.getPublic().getEncoded());

        String strClauPriv = DatatypeConverter.printHexBinary(
            parellClaus.getPrivate().getEncoded());

        System.out.println("===============================");
        System.out.println("Clau pública: " + strClauPub);
        System.out.println("===============================");
        System.out.println("Clau privada: " + strClauPriv);
    }

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048);
        return generador.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] bytesDesxifrats = cipher.doFinal(msgXifrat);
        return new String(bytesDesxifrats, StandardCharsets.UTF_8);
    }
}