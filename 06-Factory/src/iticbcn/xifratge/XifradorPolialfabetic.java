package iticbcn.xifratge;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    private static final int clauSecret = 5;
    private Random r;
    static final String s = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static final char[] array = s.toUpperCase().toCharArray();
    char[] arrayPermutado = new char[array.length];

    public TextXifrat xifra(String text, String clau) throws ClauNoSuportada {
        long semilla = convertirClau(clau);
        initRandom(semilla);   // el random s'inicialitza abans de xifrar
        return new TextXifrat(xifraPoliAlfa(text).getBytes(StandardCharsets.UTF_8));
    }
 
    @Override
    public String desxifra(TextXifrat textXifrat, String clau) throws ClauNoSuportada {
        long semilla = convertirClau(clau);
        initRandom(semilla);   // i també abans de desxifrar, perquè surti la mateixa seqüència
        return desxifraPoliAlfa(new String(textXifrat.getBytes(), StandardCharsets.UTF_8));
    }
 
    // Converteix la clau (String) a long; si no es pot, llança ClauNoSuportada
    private long convertirClau(String clau) throws ClauNoSuportada {
        try {
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de Polialfabètic ha de ser un String convertible a long");
        }
    }
 
    public void initRandom(long clauSecret) {
        r = new Random(clauSecret);
    }

    public String xifraPoliAlfa(String phrase) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++) {
            permutaAlfabet(r, array);
            char c = phrase.charAt(i);
            int p = 0;
            if (Character.isUpperCase(c)) {
                if (exist(c)) {
                    p = getPosition(c);
                    stringBuilder.append(arrayPermutado[p]);
                } else stringBuilder.append(c);
            } else {
                c = Character.toUpperCase(c);
                if (exist(c)) {
                    p = getPosition(c);
                    stringBuilder.append(Character.toLowerCase(arrayPermutado[p]));
                } else stringBuilder.append(c);
            }
        } return stringBuilder.toString();
    }

    public String desxifraPoliAlfa(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            permutaAlfabet(r, array);
            char c = s.charAt(i);
            int p = 0;
            if (Character.isUpperCase(c)) {
                if (exist(c)) {
                    p = getPositionArrayPermutado(c);
                    stringBuilder.append(array[p]);
                } else stringBuilder.append(c);
            } else {
                c = Character.toUpperCase(c);
                if (exist(c)) {
                    p = getPositionArrayPermutado(c);
                    stringBuilder.append(Character.toLowerCase(array[p]));
                } else stringBuilder.append(c);
            }
        } return stringBuilder.toString();
    }

    public void initRandom(int clauSecret) { r = new Random(clauSecret); }

    public void permutaAlfabet(Random r, char[] array) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) { list.add(array[i]); }
        Collections.shuffle(list, r);

        for (int i = 0; i < array.length; i++) { arrayPermutado[i] = list.get(i); }
    }

    public boolean exist(char character) {
        for (char c : array) { if (character == c) return true; }
        return false;
    }

    public int getPosition(char c) {
        int p = 0;
        for (int i = 0; i < array.length; i++) { if (c == array[i]) p = i; }
        return p;
    }

    public int getPositionArrayPermutado(char c) {
        int p = 0;
        for (int i = 0; i < arrayPermutado.length; i++) { if (c == arrayPermutado[i]) p = i; }
        return p;
    }

    public void fill(List<Character> list) {
        for (int i = 0; i < list.size(); i++) { arrayPermutado[i] = list.get(i); }
    }
}
