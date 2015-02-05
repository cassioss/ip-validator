package com.cassioss.ip.validator;

import java.util.Arrays;
import java.util.HashSet;

import static com.cassioss.ip.validator.Constants.*;

/**
 * Created by Cassio dos Santos Sousa on 05/02/2015.
 */

public class IPValidator {

    private static String ipError;

    public String getError() {
        return ipError;
    }

    private static void setError(String errorDetailed) {
        ipError = errorDetailed;
    }

    public static boolean validateIP(String ip) {

        if (isEmpty(ip)) {
            setError(IP_IS_EMPTY);
            return false;
        }

        if (isHomeAddress(ip)) {
            setError(IP_IS_HOME);
            return false;
        }

		/* First, we will divide our IP by its characters */

        String[] division_by_characters = divided_by_character(ip);

		/* Blocks if there is a non-valid character */

        if (not_number_or_dot(division_by_characters))
            return false;

		/* From now on, the string is divided by its dots, if any */

        String[] ip_divided = divided_by_dots(ip);

		/* Blocks ip_divided if its length is not 4 */

        if (notFourParts(ip_divided))
            return false;

		/* Blocks if any of the four strings is null, like in 1..1.1 */

        if (emptyDivision(ip_divided))
            return false;

		/* Blocks if there are any unnecessary zeros to the left */

        if (zerosToTheLeft(ip_divided))
            return false;

		/* From now on, we will use the IP as an array of integers */

        Integer[] ip_integer = turn_to_Integer(ip_divided);

		/* Blocks if any of the numbers are bigger than 255 */

        if (number_out_of_bounds(ip_integer))
            return false;

		/* Blocks if the IP is in a forbidden zone */

        if (numbers_in_forbidden_zone(ip_integer))
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
        if (ipIsNull(ip))
            return true;
        return ip == "";
    }

	/* Verifies if the string is home, 127.0.0.1 */

    private static boolean isHomeAddress(String ip) {
        return ip == "127.0.0.1";
    }

	/* Divides a string by its characters */

    private static String[] divided_by_character(String ip) {
        char[] by_char = ip.toCharArray();
        String[] by_String = new String[by_char.length];

        for (int i = 0; i < by_char.length; i++)
            by_String[i] = Character.toString(by_char[i]);

        return by_String;
    }

	/* Verifies if a character of the string is not number or dot */

    private static boolean not_number_or_dot(String[] division_by_characters) {
        String[] valid_chars = {".", "0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9"};
        HashSet<String> valid_set = new HashSet<String>(
                Arrays.asList(valid_chars));

        for (int i = 0; i < division_by_characters.length; i++) {
            if (!valid_set.contains(division_by_characters[i]))
                return true;
        }

        return false;
    }

	/* Divides a string by its dots */

    private static String[] divided_by_dots(String ip) {
        return ip.split("\\.");
    }

	/* Verifies if a divided string does not have four parts */

    private static boolean notFourParts(String[] divided) {
        return divided.length != 4;
    }

	/* Verifies if a divided string has an empty part */

    private static boolean emptyDivision(String[] ip_divided) {
        for (int i = 0; i < ip_divided.length; i++)
            if (ip_divided[i].equals(""))
                return true;

        return false;
    }

	/* Verifies if any part of the divided string has zeros to the left */

    private static boolean zerosToTheLeft(String[] ip_divided) {
        for (int i = 0; i < ip_divided.length; i++)
            if (!ip_divided[i].equals("0") && ip_divided[i].startsWith("0"))
                return true;

        return false;
    }

	/* Turns an array of Strings into an array of Integers */

    private static Integer[] turn_to_Integer(String[] ip_divided) {
        Integer[] ip_integer = new Integer[ip_divided.length];

        for (int i = 0; i < ip_divided.length; i++)
            ip_integer[i] = Integer.parseInt(ip_divided[i]);
        return ip_integer;
    }

	/* Verifies if the numbers of an IP are out of bounds */

    private static boolean number_out_of_bounds(Integer[] ip_integer) {
        for (int i = 0; i < ip_integer.length; i++)
            if (ip_integer[i] > 255)
                return true;

        return false;
    }

	/* Verifies if the numbers of an IP are in a forbidden zone */

    private static boolean numbers_in_forbidden_zone(Integer[] ip_integer) {
        int first = ip_integer[0];
        int second = ip_integer[1];

        return (first == 172) || (first == 10)
                || (first == 192 && second == 168);
    }
}