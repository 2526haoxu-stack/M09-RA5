package iticbcn.xifratge;
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

    public static void main(String[] args) {
        XifradorPolialfabetic xifradorPolialfabetic = new XifradorPolialfabetic();
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            xifradorPolialfabetic.initRandom(clauSecret);
            msgsXifrats[i] = xifradorPolialfabetic.xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n",msgs[i],msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            xifradorPolialfabetic.initRandom(clauSecret);
            String msg = xifradorPolialfabetic.desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n",msgsXifrats[i],msg);
        }
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
