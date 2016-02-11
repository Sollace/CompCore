package net.acomputerdog.core.java;

import java.util.regex.Pattern;

/**
 * Utility class that contains pre-calculated Regexes for common strings
 */
public class Patterns {
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    public static final String G = "G";
    public static final String H = "H";
    public static final String I = "I";
    public static final String J = "J";
    public static final String K = "K";
    public static final String L = "L";
    public static final String M = "M";
    public static final String N = "N";
    public static final String O = "O";
    public static final String P = "P";
    public static final String Q = "Q";
    public static final String R = "R";
    public static final String S = "S";
    public static final String T = "T";
    public static final String U = "U";
    public static final String V = "V";
    public static final String W = "W";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String Z = "Z";

    public static final String a = "a";
    public static final String b = "b";
    public static final String c = "c";
    public static final String d = "d";
    public static final String e = "e";
    public static final String f = "f";
    public static final String g = "g";
    public static final String h = "h";
    public static final String i = "i";
    public static final String j = "j";
    public static final String k = "k";
    public static final String l = "l";
    public static final String m = "m";
    public static final String n = "n";
    public static final String o = "o";
    public static final String p = "p";
    public static final String q = "q";
    public static final String r = "r";
    public static final String s = "s";
    public static final String t = "t";
    public static final String u = "u";
    public static final String v = "v";
    public static final String w = "w";
    public static final String x = "x";
    public static final String y = "y";
    public static final String z = "z";

    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String SEVEN = "7";
    public static final String EIGHT = "8";
    public static final String NINE = "9";

    public static final String PERIOD = quote(".");
    public static final String COMMA = quote(",");
    public static final String SPACE = quote(" ");
    public static final String COLON = quote(":");
    public static final String MINUS = quote("-");
    public static final String PLUS = quote("+");
    public static final String EQUALS = quote("=");
    public static final String CAP = quote("^");
    public static final String LEFT_PAREN = quote("(");
    public static final String RIGHT_PAREN = quote(")");
    public static final String SEMICOLON = quote(";");
    public static final String QUOTES = quote("\"");
    public static final String AT = quote("@");
    public static final String EXCLAMATION = quote("!");
    public static final String NUMBERSIGN = quote("#");
    public static final String DOLLARSIGN = quote("$");
    public static final String GREATER_THAN = quote(">");
    public static final String LESS_THAN = quote("<");
    public static final String OPEN_CURLY_BRACKETS = quote("{");
    public static final String CLOSE_CURLY_BRACKETS = quote("}");
    public static final String FORWARD_SLASH = "/";
    public static final String BACK_SLASH = "\\";

    public static final String SECTONSIGN = "\u0167";
    
    public static final String NEWLINE = "\n";
    public static final String CARRIAG_RETURN = "\r";
    public static final String TAB = "\t";
    
    /**
     * A pattern that will match any line break, whether it uses 
     */
    public static final String LINE_DELIMITER = "\n|\r\n|\r";
    /**
     * A pattern that will match url/directory delimiters regardless of direction.
     */
    public static final String DIRECTORY_DELIMETER = "/|\\";
    /**
     * A patter that will match method parameters from a descriptor.
     * <p>
     * Source:
     * <a href="https://gist.github.com/VijayKrishna/6160036">https://gist.github.com/VijayKrishna/6160036</a>
     */
    public static final String DESCRIPTOR_PARAMETER = "\\[*L[^;]+;|\\[[ZBCSIFDJ]|[ZBCSIFDJ]";
    
    /**
     * Gets the regex that will quote the specified string, creating it if necessary.
     *
     * @param str The string to quote
     * @return Return the regex that will quote the string
     */
    /*
     * Quoting is only needed for character that might have another meaning in regex.
     */
    public static String quote(String str) {
        return Pattern.quote(str);
        //return "\\Q" + str + "\\E";
    }
}
