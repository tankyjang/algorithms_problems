package com.codingtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * This program calculate the sum of Roman numbers from the given expression
 * and write out in Roman number.
 * 
 * Input file format (roman_in.txt):
 * File contains N lines of Roman number expression, 
 * Each line has following format
 * <roman number 1>+<roman number 2>
 * 
 * Output file format (roman_out.txt):
 * Output file contains N lines of the Roman number, 
 *    which is equal to the sum of the Roman number 1 and Roman number 2 from corresponding input line
 * 
 * @author Prakit Engkakitti (Tank)
 *
 */
public class RomanCalculator {

	public static final String INPUT = "roman_in.txt";
	public static final String OUTPUT = "roman_out.txt";

	public static final String[] romanSymbols = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	public static final int[] decimals = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader( new FileReader( INPUT ) );
		PrintWriter writer = new PrintWriter( OUTPUT );

		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] romanNumbers = line.split( "\\+" );

				if (romanNumbers != null && romanNumbers.length > 0) {
					int sum = 0;
					for (int i = 0; i < romanNumbers.length; i++) {
						sum += romanToInteger( romanNumbers[i] );
					}
					writer.println( integerToRoman( sum ) );
				}
			}
		} finally {
			reader.close();
			writer.close();
		}
	}

	/**
	 * Convert a Roman number to integer
	 * 
	 * @param romanNumber
	 * @return integer
	 * @throws Exception
	 */
	public static int romanToInteger(String romanNumber) throws Exception {
		int startIndex = 0;
		int sum = 0;
		while (startIndex < romanNumber.length()) {
			int symbolIndex = -1;
			for (int i = 0; i < romanSymbols.length; i++) {
				if (romanNumber.startsWith( romanSymbols[i], startIndex )) {
					symbolIndex = i;
					break;
				}
			}
			if (symbolIndex != -1) {
				sum += decimals[symbolIndex];
				startIndex += romanSymbols[symbolIndex].length();
			} else {
				throw new Exception(
						"Invalid Roman Number, got unknown roman expression: "
								+ romanNumber.substring( startIndex ) );
			}
		}

		return sum;
	}

	/**
	 * Convert an integer to Roman number
	 * 
	 * @param number
	 * @return Roman number
	 * @throws Exception
	 */
	public static String integerToRoman(int number) throws Exception {
		StringBuilder romanNumber = new StringBuilder();
		while (number > 0) {
			int symbolIndex = -1;
			for (int i = 0; i < decimals.length; i++) {
				if (number >= decimals[i]) {
					symbolIndex = i;
					break;
				}
			}
			if (symbolIndex != -1) {
				romanNumber.append( romanSymbols[symbolIndex] );
				number -= decimals[symbolIndex];
			} else {
				throw new Exception( "Impossible" );
			}
		}

		return romanNumber.toString();
	}
}
