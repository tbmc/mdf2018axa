package com.isograd.test;

import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.isograd.exercise.IsoContest;

public class TestRunner {

	@Test
	public void runTest() {

		int maxFileNumber = 6;
		for (int i = 1; i <= maxFileNumber; i++) {

			final String inputFileName = "/input" + i + ".txt";
			final String outputFileName = "/output" + i + ".txt";

			try (final InputStream isInput = getClass().getResourceAsStream(inputFileName);
					final InputStream isOutput = getClass().getResourceAsStream(outputFileName);
					final InputStream input1 = new BufferedInputStream(isOutput);

					final OutputStream outputResult = new ByteArrayOutputStream();
					final PrintStream psOut = new PrintStream(outputResult);) {

				if (isInput == null && isOutput == null) {
					break;
				}

				System.setIn(isInput);
				System.setOut(psOut);

				IsoContest.main(null);

				final InputStream input2 = new ByteArrayInputStream(
						((ByteArrayOutputStream) outputResult).toByteArray());

				compareStreams(input1, input2);

			} catch (final Exception e) {
				e.printStackTrace();
				fail("Erreur");
			}

		}

	}

	private void compareStreams(final InputStream input1, final InputStream input2) throws IOException {
		int line = 1;
		int ch = input1.read();
		while (-1 != ch) {
			int ch2 = input2.read();
			if (ch != ch2) {
				fail("Line " + line + " Expected [" + (char) ch + "] but found [" + (char) ch2 + "]");
			}
			ch = input1.read();
			line++;
		}
		int ch2 = input2.read();
		// gestion du saut de ligne en fin de fichier
		while (10 == ch2) {
			ch2 = input2.read();
		}
		if (ch2 != -1) {
			fail("Expected End Of File but found [" + (char) ch2 + "]");
		}
	}

}
