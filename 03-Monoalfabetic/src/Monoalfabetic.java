import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    static final String s = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static final char[] array = s.toUpperCase().toCharArray();
    static char[] arrayPermutado = new char[array.length];
    static final String[] phrases = {"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
    static String[] phrasesPermutat = new String[phrases.length];

    public static void main(String[] args) {
        Monoalfabetic m = new Monoalfabetic();
        List<Character> list = m.permutaAlfabet(array);
        m.fill(list);

        m.mostrarArrayNormal(array);
        System.out.println();
        m.mostrarArrayPermutado(arrayPermutado);
        System.out.println("\n");

        System.out.println("Xifratge:");
        for (int i = 0; i < phrases.length; i++) {
            String result = m.xifraMonoAlfa(phrases[i]);
            phrasesPermutat[i] = result;
            System.out.printf("%-35s -> %s%n", phrases[i], phrasesPermutat[i]);
        }

        System.out.println("\nDesxifratge:");
        for (int i = 0; i < phrases.length; i++) {
            String result = m.desxifraMonoAlfa(phrases[i]);
            phrasesPermutat[i] = result;
            System.out.printf("%-35s -> %s%n", phrasesPermutat[i], phrases[i]);
        }
    }

    public List<Character> permutaAlfabet(char[] array) {
        List<Character> list = new ArrayList<>();

        for (char n : array) { list.add(n); }
        Collections.shuffle(list);
        return list;
    }

    public String xifraMonoAlfa(String phrase) {
        StringBuilder stringBuilder = new StringBuilder();
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

    public String desxifraMonoAlfa(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
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

    public void mostrarArrayNormal(char[] array) {
        for (int i = 0; i < array.length; i++) { System.out.print((i == 0) ? array[i] : " " + array[i]); }
    }

    public void mostrarArrayPermutado(char[] array) {
        for (int i = 0; i < array.length; i++) { System.out.print((i == 0) ? array[i] : " " + array[i]); }
    }

    public void fill(List<Character> list) {
        for (int i = 0; i < list.size(); i++) { arrayPermutado[i] = list.get(i); }
    }

    public int getPosition(char c) {
        int p = 0;
        for (int i = 0; i < array.length; i++) { if (c == array[i]) p = i; }
        return p;
    }

    public boolean exist(char character) {
        for (char c : array) { if (character == c) return true; }
        return false;
    }
}