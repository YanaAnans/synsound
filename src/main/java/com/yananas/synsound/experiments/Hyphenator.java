package com.yananas.synsound.experiments;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Hyphenator {

    private final static String vowels = "аиуэоыяюеё";
    private final static String ioVowels = "яюеё";
    private final static String consonants = "бвгджзклмнпрстфхцчшщй";
    private final static String endOfSyl = "йъь";

    private static ArrayList<String> patterns = new ArrayList<String>();

    public Hyphenator() {
        patterns.add("([" + endOfSyl + "])([" + ioVowels + "])");
        patterns.add("([" + vowels + "])([" + vowels + "])");
        patterns.add("([" + vowels + consonants + "])([" + consonants + "][" + vowels + "])");
        patterns.add("([" + consonants + "][" + vowels + "])([" + consonants + "][" + vowels + "])");
        patterns.add(
                "([" + vowels + "][" + consonants + "])([" + consonants + "][" + consonants + "][" + vowels + "])");
        patterns.add("([" + vowels + "][" + consonants + "][" + consonants + "])([" + consonants + "][" + consonants
                + "][" + vowels + "])");
    }

    public static String hyphen(String text) {
        Pattern p1 = Pattern.compile("([" + consonants + "])([" + consonants + "])");
        text = text.replaceAll(p1.toString(), "$1-$2");
        Pattern p2 = Pattern.compile("([" + consonants + "])([" + vowels + "])([" + consonants + "])");
        text = text.replaceAll(p2.toString(), "$1$2-$2$3");
        return text;
    }

    public static String hyphens(String text) {
        for (int i = 0; i < patterns.size(); i++) {
            Pattern p = Pattern.compile(patterns.get(i));
            text = text.toLowerCase();
            text = text.replaceAll(p.toString(), "$1-$2");
        }
        return text;
    }

    public static void main(String[] args) {
        System.out.println(hyphens("параллелепипед"));
        System.out.println(hyphens("подъезд"));
        System.out.println(hyphens("СНЕГ"));
        System.out.println(hyphens("овраг"));
        System.out.println(hyphens("ВьЮн"));
        System.out.println(hyphens("строка"));
    }
}
