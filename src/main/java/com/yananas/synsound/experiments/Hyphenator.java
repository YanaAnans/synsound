package com.yananas.synsound.experiments;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Hyphenator {

    private final static String vowels = "аиуэоыяюеё";
    private final static String ioVowels = "яюеё";
    private final static String consonants = "бвгджзклмнпрстфхцчшщй";
    private final static String endOfSyl = "йъь";

    private HashMap<String, String> rules = new HashMap<String, String>();

    public Hyphenator() {
        rules.put("([" + endOfSyl + "])([" + ioVowels + "])", "$1-$2");
        rules.put("([" + vowels + "])([" + vowels + "])", "$1-$2");
        rules.put("([" + vowels + consonants + "])([" + consonants + "][" + vowels + "])", "$1-$2");
        rules.put("([" + consonants + "][" + vowels + "])([" + consonants + "][" + vowels + "])", "$1-$2");
        rules.put("([" + vowels + "][" + consonants + "])([" + consonants + "][" + consonants + "][" + vowels + "])", "$1-$2");
        rules.put("([" + vowels + "][" + consonants + "][" + consonants + "])([" + consonants + "][" + consonants
                + "][" + vowels + "])", "$1-$2");
    }

    public String hyphen(String text) {
        Pattern p1 = Pattern.compile("([" + consonants + "])([" + consonants + "])");
        text = text.replaceAll(p1.toString(), "$1-$2");
        Pattern p2 = Pattern.compile("([" + consonants + "])([" + vowels + "])([" + consonants + "])");
        text = text.replaceAll(p2.toString(), "$1$2-$2$3");
        return text;
    }

    public String hyphens(String in) {
        String res = in.toLowerCase();
        for (Entry<String, String> rule : rules.entrySet()) {
            res = res.replaceAll(rule.getKey(), rule.getValue());
        }
        return res;
    }

    public static void main(String[] args) {
        Hyphenator hyphenator = new Hyphenator();
        System.out.println(hyphenator.hyphen("шторм"));
        System.out.println(hyphenator.hyphen("принц"));
        System.out.println(hyphenator.hyphens("параллелепипед"));
        System.out.println(hyphenator.hyphens("подъезд"));
        System.out.println(hyphenator.hyphens("СНЕГ"));
        System.out.println(hyphenator.hyphens("овраг"));
        System.out.println(hyphenator.hyphens("ВьЮн"));
        System.out.println(hyphenator.hyphens("строка"));
    }
}
