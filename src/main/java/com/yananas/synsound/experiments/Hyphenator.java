package com.yananas.synsound.experiments;

import java.util.regex.Pattern;

public class Hyphenator {

	private final static String vowels = "аиуэоыяюеёАИУЭОЫЯЮЕЁ";
	private final static String consonants = "^аиуэоыяюеёАИУЭОЫЯЮЕЁ";

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
		Pattern p = Pattern.compile("([" + consonants + "])([" + consonants + "])([" + vowels + "])([" + consonants
				+ "])([" + consonants + "])");
		text = text.replaceAll(p.toString(), "$1-$2$3-$3$4-$5");
		System.out.println(text);
	}

	public static void main(String[] args) {
		Hyphenator.firstRegexp("123абв");
		Hyphenator.firstRegexp("абв123");
		twoVowels("поезд");
		twoVowels("радио");
		oneVowelTwoCons("шторм");
		oneVowelTwoCons("сад");
		twoCons("шторм");
		hyphen("шторм");
		hyphen("гвоздь");
	}
}
