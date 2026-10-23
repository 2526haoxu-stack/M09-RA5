import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public int npass = 0;
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
            System.out.printf("============================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("--------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("--------------------------\n\n");
        }
    }

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iteracions = 65536;
        int longitutClau = 512;
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iteracions, longitutClau);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] bytes = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;
        char[] array = "abcdefABCDEF1234567890!".toCharArray();
        boolean find = false;
        String otherHash = "";
        char[] chars = new char[6];
        for (int a = 0; a < array.length; a++) {
            chars[0] = array[a];
            for (int b = 0; b < array.length; b++) {
                chars[1] = array[b];
                for (int c = 0; c < array.length; c++) {
                    chars[2] = array[c];
                    for (int d = 0; d < array.length; d++) {
                        chars[3] = array[d];
                        for (int e = 0; e < array.length; e++) {
                            chars[4] = array[e];
                            for (int f = 0; f < array.length; f++) {
                                chars[5] = array[f];
                                String passwd = new String(chars);
                                otherHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(passwd, salt) : getPBKDF2AmbSalt(passwd, salt);
                                if (hash.equals(otherHash)) { find = true; break; }
                                else npass++;
                            } if (find) break;
                        } if (find) break;
                    } if (find) break;
                } if (find) break;
            } if (find) break;
        } return otherHash;
    }

    public String getInterval(long t1, long t2) {
        long res = t2 - t1;
        long millis = res % 1000;
        long segons = (res / 1000) % 60;
        long minuts = (res / (1000 * 60)) % 60;
        long hores  = (res / (1000 * 60 * 60)) % 24;
        long dies   = res / (1000 * 60 * 60 * 24);

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis);
    }
}
