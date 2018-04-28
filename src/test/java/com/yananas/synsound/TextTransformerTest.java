package com.yananas.synsound;

import org.junit.Assert;
import org.junit.Test;

public class TextTransformerTest {
	@Test
	public void testSimpleWord() {
		TextTransformer transformer = new TextTransformer();
		String transformed = transformer.transform("мама");
		Assert.assertEquals(transformed, "mama");
	}

	@Test
	public void testSimpleWord2() {
		TextTransformer transformer = new TextTransformer();
		String transformed = transformer.transform("кот");
		Assert.assertEquals(transformed, "kot");
	}

	@Test
	public void testSimpleWord3() {
		TextTransformer transformer = new TextTransformer();
		String transformed = transformer.transform("конь");
		Assert.assertEquals(transformed, "kon'");
	}

	@Test
	public void testSimpleWord4() {
		TextTransformer transformer = new TextTransformer();
		String transformed = transformer.transform("въезд");
		Assert.assertEquals(transformed, "v~ezd");
	}
}
