package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {
    static final String s = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static final char[] arrayMin = s.toCharArray();
    static final char[] arrayMay = s.toUpperCase().toCharArray();
    static final String[] wordsXifra = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
    static final String[] wordsDesxifra = new String[wordsXifra.length];
    static final int[] jumps = {0,2,4,6};

    @Override
    public TextXifrat xifra(String text, String clau) throws ClauNoSuportada {
        try {
            Integer.parseInt(clau);
            if (Integer.parseInt(clau) < 0 || Integer.parseInt(clau) > 40) throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } return new TextXifrat(xifraRotX(text, Integer.parseInt(clau)).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat textXifrat, String clau) throws ClauNoSuportada {
        try {
            Integer.parseInt(clau);
            if (Integer.parseInt(clau) < 0 || Integer.parseInt(clau) > 40) throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } return desxifraRotX(new String(textXifrat.getBytes(), StandardCharsets.UTF_8), Integer.parseInt(clau));
    }


    public String xifraRotX(String word, int jump) {
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

    public String desxifraRotX(String word, int jump) {
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

    public void forcaBrutaRotX(String word) {
        for (int i = 0; i < arrayMin.length; i++) {
            String s = desxifraRotX(word, i);
            System.out.printf("(%d)-%-23s%n", i, s);
        }
    }

    public int positionMay(char c) {
        int p = 0;
        for (int i = 0; i < arrayMay.length; i++) { if (c == arrayMay[i]) p = i; }
        return p;
    }

    public int positionMin(char c) {
        int p = 0;
        for (int i = 0; i < arrayMin.length; i++) { if (c == arrayMin[i]) p = i; }
        return p;
    }
}