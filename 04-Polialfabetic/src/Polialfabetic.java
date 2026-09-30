import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {
    private static final int clauSecret = 5;
    private static Random r;
    static final String s = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static final char[] array = s.toUpperCase().toCharArray();
    static char[] arrayPermutado = new char[array.length];

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecret);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n",msgs[i],msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecret);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n",msgsXifrats[i],msg);
        }
    }

    public static String xifraPoliAlfa(String phrase) {
        StringBuilder stringBuilder = new StringBuilder();
        permutaAlfabet(r, array);

        for (int i = 0; i < phrase.length(); i++) {
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

    public static String desxifraPoliAlfa(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        permutaAlfabet(r, array);

        for (int i = 0; i < s.length(); i++) {
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

    public static void initRandom(int clauSecret) { r = new Random(clauSecret); }

    public static void permutaAlfabet(Random r, char[] array) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) { list.add(array[i]); }
        Collections.shuffle(list, r);

        for (int i = 0; i < array.length; i++) { arrayPermutado[i] = list.get(i); }
    }

    public static boolean exist(char character) {
        for (char c : array) { if (character == c) return true; }
        return false;
    }

    public static int getPosition(char c) {
        int p = 0;
        for (int i = 0; i < array.length; i++) { if (c == array[i]) p = i; }
        return p;
    }

    public static int getPositionArrayPermutado(char c) {
        int p = 0;
        for (int i = 0; i < arrayPermutado.length; i++) { if (c == arrayPermutado[i]) p = i; }
        return p;
    }

    public static void fill(List<Character> list) {
        for (int i = 0; i < list.size(); i++) { arrayPermutado[i] = list.get(i); }
    }
}
