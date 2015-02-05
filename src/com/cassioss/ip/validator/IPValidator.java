package com.cassioss.ip.validator;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The IPValidator class has methods to evaluate and parse a String to verify if it is a valid IP address for a private
 * network. A valid IP address has the form "NNN.NNN.NNN.NNN", where each NNN is an integer from 0 to 255. Reserved
 * value ranges, which will not be accepted by the program, are:
 * <p/>
 * 10.0.0.0 - 10.255.255.255
 * <p/>
 * 172.16.0.0 - 172.31.255.255
 * <p/>
 * 192.168.0.0 - 192.168.255.255
 * <p/>
 * Since 127.0.0.1 is the home address, it cannot be accepted as well.
 *
 * @author Cassio dos Santos Sousa
 * @version 1.0
 * @see <a href="http://support.microsoft.com/kb/142863">Valid IP Addressing for a Private Network</a>*
 * @since 2015-02-05
 */

public class IPValidator {

    // Error messages to be used by IPConsole

    private static final String IP_IS_EMPTY = "The IP you wrote is null or empty.";
    private static final String IP_IS_HOME = "The IP you wrote is the home address.";
    private static final String IP_HAS_INVALID_CHARACTER = "One of the characters you typed is not a number or dot.";
    private static final String IP_WITHOUT_FOUR_PARTS = "Your IP does not have four numbers.";
    private static final String IP_HAS_EMPTY_SECTION = "One of the numbers of your IP is empty.";
    private static final String IP_HAS_ZEROS_TO_THE_LEFT = "One of the numbers has zeros to the left.";
    private static final String IP_NUMBER_OUT_OF_BOUNDS = "One of the numbers is out of bounds";
    private static final String IP_IN_FORBIDDEN_ZONE = "Your IP is in a forbidden zone.";

    // String ipError that returns the proper error message, along with getter and setter

    private static String ipError;

    public String getIpError() {
        return ipError;
    }

    private static void setError(String error) {
        ipError = error;
    }

    // Receives a String and returns True if it is a valid IP character

    public static boolean validateIP(String ip) {

        // Verifies IP as its original String

        if (isEmpty(ip)) {
            setError(IP_IS_EMPTY);
            return false;
        }

        if (isHomeAddress(ip)) {
            setError(IP_IS_HOME);
            return false;
        }

        // Verifies IP character by character

        if (isNotNumberOrDot(ip)) {
            setError(IP_HAS_INVALID_CHARACTER);
            return false;
        }

        // Divides IP by dots and verifies division

        String[] ipDividedByDots = divideByDots(ip);

        if (notFourParts(ipDividedByDots)) {
            setError(IP_WITHOUT_FOUR_PARTS);
            return false;
        }

        if (emptyDivision(ipDividedByDots)) {
            setError(IP_HAS_EMPTY_SECTION);
            return false;
        }

        if (zerosToTheLeft(ipDividedByDots)) {
            setError(IP_HAS_ZEROS_TO_THE_LEFT);
            return false;
        }

        // Transforms IP sections into Integers, verifying boundaries

        Integer[] ipOfIntegers = turnToInteger(ipDividedByDots);

        if (numberOutOfBounds(ipOfIntegers)) {
            setError(IP_NUMBER_OUT_OF_BOUNDS);
            return false;
        }

        if (numbersInForbiddenZones(ipOfIntegers)) {
            setError(IP_IN_FORBIDDEN_ZONE);
            return false;
        }

        return true;
    }

    // Verifies if the IP, if not null, is empty

    private static boolean isEmpty(String ip) {
        return ip == null || ip.equals("");
    }

    // Verifies if the IP is the home address, 127.0.0.1

    private static boolean isHomeAddress(String ip) {
        return ip.equals("127.0.0.1");
    }

    // Divides an IP character by character as a String array

    private static String[] divideByCharacters(String ip) {
        char[] ipCharArray = ip.toCharArray();
        String[] ipCharToString = new String[ipCharArray.length];

        for (int i = 0; i < ipCharArray.length; i++)
            ipCharToString[i] = Character.toString(ipCharArray[i]);

        return ipCharToString;
    }

    // Verifies if a character of the IP is not a number or a dot

    private static boolean isNotNumberOrDot(String ip) {
        String[] ipDividedByCharacters = divideByCharacters(ip);

        String[] validCharacters = {".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        HashSet<String> validSet = new HashSet<String>(Arrays.asList(validCharacters));

        for (String character : ipDividedByCharacters)
            if (!validSet.contains(character))
                return true;

        return false;
    }

    // Divides an IP by its dots

    private static String[] divideByDots(String ip) {
        return ip.split("\\.");
    }

    // Verifies if a divided IP does not have four parts

    private static boolean notFourParts(String[] ipDividedByDots) {
        return ipDividedByDots.length != 4;
    }

    // Verifies if a divided IP has empty divisions

    private static boolean emptyDivision(String[] ipDividedByDots) {
        for (String ipSection : ipDividedByDots)
            if (ipSection.equals(""))
                return true;

        return false;
    }

    // Verifies if any section of the IP has zeros to the left, if it is not a zero

    private static boolean zerosToTheLeft(String[] ipDividedByDots) {
        for (String ipSection : ipDividedByDots)
            if (!ipSection.equals("0") && ipSection.startsWith("0"))
                return true;

        return false;
    }

    // Turns an array of Strings into an array of Integers

    private static Integer[] turnToInteger(String[] ipDividedByDots) {
        Integer[] ipOfIntegers = new Integer[ipDividedByDots.length];

        for (int i = 0; i < ipDividedByDots.length; i++)
            ipOfIntegers[i] = Integer.parseInt(ipDividedByDots[i]);
        return ipOfIntegers;
    }

    // Verifies if any section of the IP has a number out of bounds

    private static boolean numberOutOfBounds(Integer[] ipOfIntegers) {
        for (Integer number : ipOfIntegers)
            if (number > 255)
                return true;

        return false;
    }

    // Verifies if the numbers of an IP are in a forbidden zone

    private static boolean numbersInForbiddenZones(Integer[] ipOfIntegers) {
        int firstNumber = ipOfIntegers[0];
        int secondNumber = ipOfIntegers[1];

        return (firstNumber == 172 && (secondNumber >= 16 && secondNumber <= 31)) ||
                (firstNumber == 10) || (firstNumber == 192 && secondNumber == 168);
    }
}