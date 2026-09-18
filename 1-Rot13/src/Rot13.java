public class Rot13 {
    static final String s = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static final char[] arrayMin = s.toCharArray();
    static final char[] arrayMay = s.toUpperCase().toCharArray();
    static final String[] words = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?", "IÏJ", "FGH", "Òwúi, Ùá. jiúkwb", "Zmálx, zmá bc acñ nà?"};

    public static void main(String[] args) {
        System.out.println("\nXifrat\n------");

        for (int i = 0; i < words.length; i++) {
            String result = "";
            if (i < 4) {
                result = xifraRot13(words[i]);
                System.out.printf("%-23s => %s%n", words[i], result);
            } else {
                if (i == 4) System.out.println("\nDesxifrat\n------");
                result = desxifraRot13(words[i]);
                System.out.printf("%-23s => %s%n", words[i], result);
            }

        }
    }

    public static String xifraRot13(String word) {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int p = 0;
            boolean exist = false;
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < arrayMay.length; j++) { if (c == arrayMay[j]) exist = true; }

                if (exist) {
                    int v =
                    p = (positionMay(c)+13)%arrayMay.length;
                    result+=arrayMay[p];
                } else result+=c;
            } else {
                for (int j = 0; j < arrayMin.length; j++) { if (c == arrayMin[j]) exist = true; }

                if (exist) {
                    p = (positionMin(c)+13)%arrayMin.length;
                    result+=arrayMin[p];
                } else result+=c;
            }
        } return result;
    }

    public static String desxifraRot13(String word) {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int p = 0;
            boolean exist = false;
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < arrayMay.length; j++) { if (c == arrayMay[j]) exist = true; }
                if (exist) {
                    p = positionMay(c)-13;
                    result+= (p >= 0) ? arrayMay[p] : arrayMay[arrayMay.length+p];
                } else result+=c;
            } else {
                for (int j = 0; j < arrayMin.length; j++) { if (c == arrayMin[j]) exist = true; }

                if (exist) {
                    p = positionMin(c)-13;
                    result+= (p >= 0) ? arrayMin[p] : arrayMin[arrayMin.length+p];
                } else result+=c;
            }
        } return result;
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