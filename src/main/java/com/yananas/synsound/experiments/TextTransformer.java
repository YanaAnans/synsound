package com.yananas.synsound.experiments;

import java.util.HashMap;

public class TextTransformer {

	private HashMap<String, String> latinMap = new HashMap<String, String>();

	public TextTransformer() {
		latinMap.put("а", "a");
		latinMap.put("б", "b");
		latinMap.put("в", "v");
		latinMap.put("г", "g");
		latinMap.put("д", "d");
		latinMap.put("'е", "'e");
		latinMap.put("'ё", "'o");
		latinMap.put("ж", "j");
		latinMap.put("з", "z");
		latinMap.put("и", "i");
		latinMap.put("й", "~");
		latinMap.put("к", "k");
		latinMap.put("л", "l");
		latinMap.put("м", "m");
		latinMap.put("н", "n");
		latinMap.put("о", "o");
		latinMap.put("п", "p");
		latinMap.put("р", "r");
		latinMap.put("с", "s");
		latinMap.put("т", "t");
		latinMap.put("у", "u");
		latinMap.put("ф", "f");
		latinMap.put("х", "h");
		latinMap.put("ц", "c");
		latinMap.put("ч", "4'");
		latinMap.put("ш", "w");
		latinMap.put("щ", "w");
		latinMap.put("ы", "y");
		latinMap.put("э", "e");
		latinMap.put("'ю", "'u");
		latinMap.put("'я", "'a");
	}

	public String transform(String text) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			String cyrillicsLetter = String.valueOf(text.charAt(i));
			String latinLetter = latinMap.get(cyrillicsLetter);
			if (latinLetter == null) {
				builder.append(cyrillicsLetter);
			} else {
				builder.append(latinMap.get(cyrillicsLetter));
			}
		}
		return builder.toString();
	}
}
