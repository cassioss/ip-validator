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
 * Since 127.0.0.1 is the home address, it will not be accepted either.
 *
 * @author Cassio dos Santos Sousa
 * @version 1.0
 * @see <a href="http://support.microsoft.com/kb/142863">Valid IP Addressing for a Private Network</a>*
 * @since 2015-02-05
 */

public class IPValidator {

    /**
     * The validateIP method returns true if the is valid for a private network.
     *
     * @return true if ip is a valid IP for a private network
     * @value ip - a string to be verified
     */

    public static boolean validateIP(String ip) {

        if (isEmpty(ip))
            return false;

        if (isHomeAddress(ip))
            return false;

        String[] ipDividedByCharacters = divideByCharacters(ip);

		/* Blocks if there is a non-valid character */

        if (hasNoNumbersOrDots(ipDividedByCharacters))
            return false;

		/* From now on, the string is divided by its dots, if any */

        String[] ipDividedByDots = divideByDots(ip);

		/* Blocks ipDividedByDots if its length is not 4 */

        if (notFourParts(ipDividedByDots))
            return false;

		/* Blocks if any of the four strings is null, like in 1..1.1 */

        if (emptyDivision(ipDividedByDots))
            return false;

		/* Blocks if there are any unnecessary zeros to the left */

        if (zerosToTheLeft(ipDividedByDots))
            return false;

		/* From now on, we will use the IP as an array of integers */

        Integer[] ipOfIntegers = turnToInteger(ipDividedByDots);

		/* Blocks if any of the numbers are bigger than 255 */

        if (numberOutOfBounds(ipOfIntegers))
            return false;

		/* Blocks if the IP is in a forbidden zone */

        if (numbersInForbiddenZones(ipOfIntegers))
            return false;

		/* The IP is OK if otherwise */

        return true;
    }

	/* Verifies if a String is null */

    private static boolean ipIsNull(String ip) {
        return ip == null;
    }

	/* Verifies if the string, if not null, is empty */

    private static boolean isEmpty(String ip) {
        return ipIsNull(ip) || ip.equals("");
    }

	/* Verifies if the string is the home address, 127.0.0.1 */

    private static boolean isHomeAddress(String ip) {
        return ip.equals("127.0.0.1");
    }

	/* Divides a string by its characters */

    private static String[] divideByCharacters(String ip) {
        char[] ipCharArray = ip.toCharArray();
        String[] ipCharToString = new String[ipCharArray.length];

        for (int i = 0; i < ipCharArray.length; i++)
            ipCharToString[i] = Character.toString(ipCharArray[i]);

        return ipCharToString;
    }

	/* Verifies if a character of the string is not number or dot */

    private static boolean hasNoNumbersOrDots(String[] ipDividedByCharacters) {
        String[] validCharacters = {".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        HashSet<String> validSet = new HashSet<String>(Arrays.asList(validCharacters));

        for (String character : ipDividedByCharacters)
            if (!validSet.contains(character))
                return true;

        return false;
    }

	/* Divides a string by its dots */

    private static String[] divideByDots(String ip) {
        return ip.split("\\.");
    }

	/* Verifies if a divided string does not have four parts */

    private static boolean notFourParts(String[] ipDividedByDots) {
        return ipDividedByDots.length != 4;
    }

	/* Verifies if a divided string has an empty part */

    private static boolean emptyDivision(String[] ipDividedByDots) {
        for (String ipSection : ipDividedByDots)
            if (ipSection.equals(""))
                return true;

        return false;
    }

	/* Verifies if any part of the divided string has zeros to the left */

    private static boolean zerosToTheLeft(String[] ipDividedByDots) {
        for (String ipSection : ipDividedByDots)
            if (!ipSection.equals("0") && ipSection.startsWith("0"))
                return true;

        return false;
    }

	/* Turns an array of Strings into an array of Integers */

    private static Integer[] turnToInteger(String[] ipDividedByDots) {
        Integer[] ipOfIntegers = new Integer[ipDividedByDots.length];

        for (int i = 0; i < ipDividedByDots.length; i++)
            ipOfIntegers[i] = Integer.parseInt(ipDividedByDots[i]);
        return ipOfIntegers;
    }

	/* Verifies if the numbers of an IP are out of bounds */

    private static boolean numberOutOfBounds(Integer[] ipOfIntegers) {
        for (Integer number : ipOfIntegers)
            if (number > 255)
                return true;

        return false;
    }

	/* Verifies if the numbers of an IP are in a forbidden zone */

    private static boolean numbersInForbiddenZones(Integer[] ipOfIntegers) {
        int firstNumber = ipOfIntegers[0];
        int secondNumber = ipOfIntegers[1];

        return (firstNumber == 172 && (secondNumber >= 16 && secondNumber <= 31)) ||
                (firstNumber == 10) || (firstNumber == 192 && secondNumber == 168);
    }
}