package com.yananas.synsound.experiments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hyphenator {
    public static void firstRegexp(String text) {
        text = text.replaceAll("([0-9]+)([^0-9]+)", "$1-$2");
        text = text.replaceAll("([^0-9]+)([0-9]+)", "$1-$2");
        System.out.println(text);
    }

    public static void twoVowels(String text) {
        text = text.replaceAll("([аиуэоыяюеё])([аиуэоыяюеё])", "$1-$2");
        System.out.println(text);
    }

    public static void oneVowelTwoCons(String text) {
        Pattern p = Pattern.compile("([^аиуэоыяюеё])([аиуэоыяюеё])([^аиуэоыяюеё])");
        Matcher m = p.matcher(text);
        while (m.find()) {
            text = text.substring(m.start(), m.end()) + "-";
        }
        System.out.println(text);
    }

    public static void main(String[] args) {
        Hyphenator.firstRegexp("123абв");
        Hyphenator.firstRegexp("абв123");
        twoVowels("поезд");
        twoVowels("радио");
        oneVowelTwoCons("шторм");
    }
}
