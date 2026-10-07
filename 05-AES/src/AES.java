import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];

    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau)
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
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] xifrat = cipher.doFinal(b);

        // Combinar IV i part xifrada.
        byte[] combinacion = new byte[iv.length+xifrat.length];

        for (int i = 0; i < combinacion.length; i++) {

        }

        // return iv+msgxifrat
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau)
            throws Exception {

        // Extreure l'IV.

        // Extreure la part xifrada.

        // Fer hash de la clau

        // Desxifrar.

        // return String desxifrat
    }
}