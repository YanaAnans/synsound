package com.yananas.synsound.experiments;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Hyphenator {

    private final static String allVowels = "аиуэоыяюеё";
    private final static String vowels = "аиуэоы";
    private final static String ioVowels = "яюеё";
    private final static String consonants = "бвгджзклмнпрстфхцчшщй";

    private static HashMap<String, String> pairConsonants = new HashMap<String, String>();

    public Hyphenator() {
        pairConsonants.put("п", "б");
        pairConsonants.put("ф", "в");
        pairConsonants.put("к", "г");
        pairConsonants.put("т", "д");
        pairConsonants.put("ш", "ж");
        pairConsonants.put("с", "з");
    }

    public static String textToSamples(String text) {
        text = text.replaceAll("10", "десять");
        text = text.replaceAll("11", "адинацать");
        text = text.replaceAll("12", "двинацать");
        text = text.replaceAll("13", "тринацать");
        text = text.replaceAll("14", "читырнацать");
        text = text.replaceAll("15", "питнацать");
        text = text.replaceAll("16", "шыснацать");
        text = text.replaceAll("17", "симнацать");
        text = text.replaceAll("18", "васимнацать");
        text = text.replaceAll("19", "дивятнацать");
        text = text.replaceAll("20", "двацать");
        text = text.replaceAll("0", "нуль");
        text = text.replaceAll("1", "адин");
        text = text.replaceAll("2", "два");
        text = text.replaceAll("3", "три");
        text = text.replaceAll("4", "читыре");
        text = text.replaceAll("5", "пять");
        text = text.replaceAll("6", "шэс' t'");
        text = text.replaceAll("7", "семь");
        text = text.replaceAll("8", "восемь");
        text = text.replaceAll("9", "девять");
        Pattern p1 = Pattern.compile("([" + consonants + "])([" + consonants + "])");
        Pattern p2 = Pattern.compile("([" + allVowels + "])([" + consonants + "])([" + allVowels + "])");
        Pattern p3 = Pattern.compile("([" + consonants + "])([" + allVowels + "])([" + consonants + "])");
        text = text.toLowerCase();
        text = text.replaceAll("[.,!?;:]", "");
        text = text.replaceAll(("(.)([ ])([" + consonants + "])([ ])(.)"), "$1$3$5");
        text = text.replaceAll(("([" + consonants + "])([ ])([" + consonants + "])([" + allVowels + "] || ([" + consonants + "]))"), "$1- -$3$4");
        text = text.replaceAll(("([" + consonants + "])([ ])([и])([ ])([" + consonants + "])"), "$1ы$5");
        text = text.replaceAll(("([" + consonants + "])([ ])([" + vowels + "])"), "$1$3");
        text = text.replaceAll(("([" + vowels + "])([ ])([" + consonants + "])"), "$1$3");
        text = text.replaceAll(("(.*)([ ])([" + ioVowels + "])($)"), "$1$3");
        text = text.replaceAll(("([" + consonants + "])([ ])([" + ioVowels + "])"), "$1- -й$3");
        text = text.replaceAll(("(^)([" + ioVowels + "])"), "-й$2");
        text = text.replaceAll(("([" + allVowels +"])([" + ioVowels + "])"), "$1й$2");
        text = text.replaceAll(("([ъ])([" + ioVowels + "])"), "й$2");
        text = text.replaceAll("([ж])([и])", "$1ы");
        text = text.replaceAll("([ш])([и])", "$1ы");
        text = text.replaceAll("([ц])([и])", "$1ы");
        text = text.replaceAll(("([" + allVowels + "])([" + consonants + "])($)"), "$1$2-");
        text = text.replaceAll("([" + allVowels + "])([" + allVowels + "])", "$1 $2");
        text = text.replaceAll(p1.toString(), "$1 $2");
        text = text.replaceAll(p2.toString(), "$1$2 $2$3");
        text = text.replaceAll(p1.toString(), "$1 $2");
        text = text.replaceAll(p3.toString(), "$1$2 $2$3");
        text = text.replaceAll(p2.toString(), "$1$2 $2$3");
        text = text.replaceAll(p3.toString(), "$1$2 $2$3");
        text = text.replaceAll(("([" + consonants + "])([и])"), "$1'$2");
        text = text.replaceAll("([" + allVowels + "])([" + allVowels + "])", "$1 $2");
        text = text.replaceAll("([щ])(.*)", "$1'$2");
        text = text.replaceAll("([ч])([" + consonants + "])", "4' $2");
        text = text.replaceAll("([" + consonants + "])([" + ioVowels + "и" + "])", "$1'$2");
        text = text.replaceAll("([" + allVowels + "])([" + consonants + "])([ ])([" + consonants + "])(['])(["
                + ioVowels + "[и]" + "])", "$1$2'$3$4$5$6");
        text = text.replaceAll("([ч])([ ])", "$1' ");
        text = text.replaceAll("([ч])($)", "$1'-");
        text = text.replaceAll("([" + consonants + "])([ь])([" + allVowels + "])", "$1' $1'$3 $3");
        text = text.replaceAll("([" + consonants + "])([ь])", "$1'- ");
        text = text.replaceAll("([й])(['])", "$1");
        text = text.replaceAll(("(.*)([" + consonants + "])($)"), "$1$2-");
        text = text.replaceAll(("(^)([" + consonants + "])(.*)"), "-$2$3");
        text = text.replaceAll(("([ж])(['])"), "$1");

        return text;
    }

    public static void main(String[] args) {
        TextTransformer toLatin = new TextTransformer();
        System.out.println(toLatin.transform(textToSamples("привет мир")));
        System.out.println(toLatin.transform(textToSamples("жирный жир")));
        System.out.println(toLatin.transform(textToSamples("подъезд")));
    }
}
