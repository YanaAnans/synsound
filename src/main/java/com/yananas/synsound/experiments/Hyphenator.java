package com.yananas.synsound.experiments;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Hyphenator {

    private final static String vowels = "АаИиУуЭэОоЫыЯяЮюЕеЁё";
    private final static String y = "ЯяЮюЕеЁё";
    private final static String consonants = "БбВвГгДдЖжЗзКкЛлМмНнПпРрСсТтФфХхЦцЧчШшЩщЙй";
    private final static String x = "ЙйЪъЬь";

    public static void firstRegexp(String text) {
        text = text.replaceAll("([0-9]+)([^0-9]+)", "$1-$2");
        text = text.replaceAll("([^0-9]+)([0-9]+)", "$1-$2");
        System.out.println(text);
    }

    public static void twoVowels(String text) {
        text = text.replaceAll("([" + vowels + "])([" + vowels + "])", "$1-$2");
        System.out.println(text);
    }

    public static void oneVowelTwoCons(String text) {
        Pattern p = Pattern.compile("([" + consonants + "])([" + vowels + "])([" + consonants + "])");
        text = text.replaceAll(p.toString(), "$1$2-$2$3");
        System.out.println(text);
    }

    public static void twoCons(String text) {
        Pattern p = Pattern.compile("([" + consonants + "])([" + consonants + "])");
        text = text.replaceAll(p.toString(), "$1-$2");
        System.out.println(text);
    }

    public static void hyphen(String text) {
        Pattern p1 = Pattern.compile("([" + consonants + "])([" + consonants + "])");
        text = text.replaceAll(p1.toString(), "$1-$2");
        Pattern p2 = Pattern.compile("([" + consonants + "])([" + vowels + "])([" + consonants + "])");
        text = text.replaceAll(p2.toString(), "$1$2-$2$3");
        System.out.println(text);
    }

    public static void hyphens(String text) {
        ArrayList<String> patterns = new ArrayList<String>();
        patterns.add("([" + x + "])([" + y + "])");
        patterns.add("([" + vowels + "])([" + vowels + "])");
        patterns.add("([" + vowels + consonants + "])([" + consonants + "][" + vowels + "])");
        patterns.add("([" + consonants + "][" + vowels + "])([" + consonants + "][" + vowels + "])");
        patterns.add(
                "([" + vowels + "][" + consonants + "])([" + consonants + "][" + consonants + "][" + vowels + "])");
        patterns.add("([" + vowels + "][" + consonants + "][" + consonants + "])([" + consonants + "][" + consonants
                + "][" + vowels + "])");

        for (int i = 0; i < patterns.size(); i++) {
            Pattern p = Pattern.compile(patterns.get(i));
            text = text.replaceAll(p.toString(), "$1-$2");
        }
        System.out.println(text);
    }

    public static void main(String[] args) {
        hyphens("параллелепипед");
        hyphens("йогурт");
        hyphens("покой");
        hyphens("строка");
        hyphens("подъезд");
        hyphens("вьюн");
    }
}
