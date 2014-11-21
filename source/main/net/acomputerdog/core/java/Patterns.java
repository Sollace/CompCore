package net.acomputerdog.core.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class Patterns {
    private static final Map<String, String> patternMap = new ConcurrentHashMap<String, String>();

    public static final String A = quote("A");
    public static final String B = quote("B");
    public static final String C = quote("C");
    public static final String D = quote("D");
    public static final String E = quote("E");
    public static final String F = quote("F");
    public static final String G = quote("G");
    public static final String H = quote("H");
    public static final String I = quote("I");
    public static final String J = quote("J");
    public static final String K = quote("K");
    public static final String L = quote("L");
    public static final String M = quote("M");
    public static final String N = quote("N");
    public static final String O = quote("O");
    public static final String P = quote("P");
    public static final String Q = quote("Q");
    public static final String R = quote("R");
    public static final String S = quote("S");
    public static final String T = quote("T");
    public static final String U = quote("U");
    public static final String V = quote("V");
    public static final String W = quote("W");
    public static final String X = quote("X");
    public static final String Y = quote("Y");
    public static final String Z = quote("Z");

    public static final String a = quote("a");
    public static final String b = quote("b");
    public static final String c = quote("c");
    public static final String d = quote("d");
    public static final String e = quote("e");
    public static final String f = quote("f");
    public static final String g = quote("g");
    public static final String h = quote("h");
    public static final String i = quote("i");
    public static final String j = quote("j");
    public static final String k = quote("k");
    public static final String l = quote("l");
    public static final String m = quote("m");
    public static final String n = quote("n");
    public static final String o = quote("o");
    public static final String p = quote("p");
    public static final String q = quote("q");
    public static final String r = quote("r");
    public static final String s = quote("s");
    public static final String t = quote("t");
    public static final String u = quote("u");
    public static final String v = quote("v");
    public static final String w = quote("w");
    public static final String x = quote("x");
    public static final String y = quote("y");
    public static final String z = quote("z");

    public static final String ZERO = quote("0");
    public static final String ONE = quote("1");
    public static final String TWO = quote("2");
    public static final String THREE = quote("3");
    public static final String FOUR = quote("4");
    public static final String FIVE = quote("5");
    public static final String SIX = quote("6");
    public static final String SEVEN = quote("7");
    public static final String EIGHT = quote("8");
    public static final String NINE = quote("9");

    public static final String PERIOD = quote(".");
    public static final String COMMA = quote(",");
    public static final String SPACE = quote(" ");
    public static final String COLON = quote(":");
    public static final String MINUS = quote("-");
    public static final String PLUS = quote("+");
    public static final String EQUALS = quote("=");
    public static final String LEFT_PAREN = quote("(");
    public static final String RIGHT_PAREN = quote(")");
    public static final String SEMICOLON = quote(";");
    public static final String QUOTES = quote("\"");
    public static final String AT = quote("@");
    public static final String EXCLAMATION = quote("!");
    public static final String NUMBERSIGN = quote("#");
    public static final String DOLLARSIGN = quote("$");

    public static final String SECTONSIGN = quote("\u0167");

    public static String quote(String str) {
        String regex = patternMap.get(str);
        if (regex == null) {
            regex = Pattern.quote(str);
            patternMap.put(str, regex);
        }
        return regex;
    }
}
