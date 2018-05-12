package com.yananas.synsound.experiments;

import java.util.regex.Pattern;

public class Hyphenator {

	private final static String vowels = "аиуэоыяюеё";
	private final static String ioVowels = "яиюеё";
	private final static String consonants = "бвгджзклмнпрстфхцчшщй";

	public static String textToSamples(String text) {
		Pattern p1 = Pattern.compile("([" + "ъ" + "])([" + ioVowels + "])");
		Pattern p2 = Pattern.compile("([" + consonants + "])([" + consonants + "])");
		Pattern p3 = Pattern.compile("([" + vowels + "])([" + consonants + "])([" + vowels + "])");
		Pattern p4 = Pattern.compile("([" + consonants + "])([" + vowels + "])([" + consonants + "])");
		Pattern p5 = Pattern.compile("([" + consonants + "])([" + ioVowels + "])");
		Pattern p6 = Pattern.compile("([" + vowels + "])([" + consonants + "])([" + " " + "])([" + consonants + "])(["
				+ "'" + "])([" + ioVowels + "])");
		Pattern p7 = Pattern.compile("([" + consonants + "])([" + "ь" + "])");
		text = text.replaceAll(p1.toString(), "- -$2");
		text = text.replaceAll(p2.toString(), "$1 $2");
		text = text.replaceAll(p3.toString(), "$1$2 $2$3");
		text = text.replaceAll(p2.toString(), "$1 $2");
		text = text.replaceAll(p4.toString(), "$1$2 $2$3");
		text = text.replaceAll(p3.toString(), "$1$2 $2$3");
		text = text.replaceAll(p4.toString(), "$1$2 $2$3");
		text = text.replaceAll(p5.toString(), "$1'$2");
		text = text.replaceAll(p6.toString(), "$1$2'$3$4$5$6");
		text = text.replaceAll(p7.toString(), "$1'-");
		return text;
	}

	public static void main(String[] args) {
		System.out.println(textToSamples("параллелепипед"));
		System.out.println(textToSamples("подъезд"));
		System.out.println(textToSamples("взглянуть"));
		System.out.println(textToSamples("спокойно"));
		TextTransformer toLatin = new TextTransformer();
		System.out.println(toLatin.transform(textToSamples("спокойно")));
	}
}
