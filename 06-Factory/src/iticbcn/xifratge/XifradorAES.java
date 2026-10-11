package iticbcn.xifratge;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    private static final String CLAU = "LaClauSecretaQueVulguis";

    @Override
    public TextXifrat xifra(String text, String clau) throws ClauNoSuportada {
        try {
            return new TextXifrat(xifraAES(text, clau));
        } catch (Exception e) {
            throw new IllegalStateException("Error en xifrar el text", e);
        }
    }

    @Override
    public String desxifra(TextXifrat textXifrat, String clau)
            throws ClauNoSuportada {
        try {
            return desxifraAES(textXifrat.getBytes(), clau);
        } catch (Exception e) {
            throw new IllegalStateException("Error en desxifrar el text", e);
        }
    }

    public byte[] xifraAES(String msg, String clau)
            throws Exception {
        //Obtenir els bytes de l'String
        byte[] b = msg.getBytes("UTF-8");

        // Genera IvParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Genera hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashClau = md.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(hashClau, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] xifrat = cipher.doFinal(b);

        // Combinar IV i part xifrada.
        byte[] combinacion = new byte[iv.length+xifrat.length];

        System.arraycopy(iv, 0, combinacion, 0, iv.length);
        System.arraycopy(xifrat, 0, combinacion, iv.length, xifrat.length);

        // return iv+msgxifrat
        return combinacion;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau)
            throws Exception {

        // Extreure l'IV.
        byte[] ivExtret = new byte[iv.length];

        for (int i = 0; i < iv.length; i++) { ivExtret[i] = iv[i]; }

        // Extreure la part xifrada.
        byte[] xifratExtret = new byte[bIvIMsgXifrat.length-iv.length];

        for (int i = iv.length; i < bIvIMsgXifrat.length; i++) { xifratExtret[i-iv.length] = bIvIMsgXifrat[i]; }

        // Fer hash de la clau
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashClau = md.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(hashClau, ALGORISME_XIFRAT);

        // Desxifrar.
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] Desxifrat = cipher.doFinal(xifratExtret);

        // return String desxifrat
        return new String(Desxifrat, "UTF-8");
    }
}