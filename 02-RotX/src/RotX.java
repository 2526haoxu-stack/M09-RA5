public class RotX {
    static final String s = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static final char[] arrayMin = s.toCharArray();
    static final char[] arrayMay = s.toUpperCase().toCharArray();
    static final String[] wordsXifra = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
    static final String[] wordsDesxifra = new String[wordsXifra.length];
    static final int[] jumps = {0,2,4,6};

    public static void main(String[] args) {
        System.out.println("\nXifrat\n------");
        for (int i = 0; i < wordsXifra.length; i++) {
            String s = xifraRotX(wordsXifra[i], jumps[i]);
            wordsDesxifra[i] = s;
            System.out.printf("(%d)-%-23s => %s%n", jumps[i], wordsXifra[i], s);
        }

        System.out.println("\nDesxifrat\n------");
        for (int i = 0; i < wordsDesxifra.length; i++) { System.out.printf("(%d)-%-23s => %s%n", jumps[i], wordsDesxifra[i], desxifraRotX(wordsDesxifra[i], jumps[i])); }

        System.out.println("\nMissatge xifrat: Úiüht, úiü wx ùxì ív?\n------");
        forcaBrutaRotX(wordsDesxifra[3]);
    }

    public static String xifraRotX(String word, int jump) {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int p = 0;
            boolean exist = false;
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < arrayMay.length; j++) { if (c == arrayMay[j]) exist = true; }

                if (exist) {
                    p = (positionMay(c)+jump)%arrayMay.length;
                    result+=arrayMay[p];
                } else result+=c;
            } else {
                for (int j = 0; j < arrayMin.length; j++) { if (c == arrayMin[j]) exist = true; }

                if (exist) {
                    p = (positionMin(c)+jump)%arrayMin.length;
                    result+=arrayMin[p];
                } else result+=c;
            }
        } return result;
    }

    public static String desxifraRotX(String word, int jump) {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int p = 0;
            boolean exist = false;
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < arrayMay.length; j++) { if (c == arrayMay[j]) exist = true; }
                if (exist) {
                    p = positionMay(c)-jump;
                    result+= (p >= 0) ? arrayMay[p] : arrayMay[arrayMay.length+p];
                } else result+=c;
            } else {
                for (int j = 0; j < arrayMin.length; j++) { if (c == arrayMin[j]) exist = true; }

                if (exist) {
                    p = positionMin(c)-jump;
                    result+= (p >= 0) ? arrayMin[p] : arrayMin[arrayMin.length+p];
                } else result+=c;
            }
        } return result;
    }

    public static void forcaBrutaRotX(String word) {
        for (int i = 0; i < arrayMin.length; i++) {
            String s = desxifraRotX(word, i);
            System.out.printf("(%d)-%-23s%n", i, s);
        }
    }

    public static int positionMay(char c) {
        int p = 0;
        for (int i = 0; i < arrayMay.length; i++) { if (c == arrayMay[i]) p = i; }
        return p;
    }

    public static int positionMin(char c) {
        int p = 0;
        for (int i = 0; i < arrayMin.length; i++) { if (c == arrayMin[i]) p = i; }
        return p;
    }
}