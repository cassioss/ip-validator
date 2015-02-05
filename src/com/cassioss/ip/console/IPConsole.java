package com.cassioss.ip.console;

import com.cassioss.ip.validator.IPValidator;

import java.io.IOException;
import java.util.Scanner;

/**
 * The IPConsole class has a Main method that requests an IP and prints if it can be used for a Private Network
 *
 * @author Cassio dos Santos Sousa
 * @version 1.0
 * @see <a href="http://support.microsoft.com/kb/142863">Valid IP Addressing for a Private Network</a>*
 * @since 2015-02-05
 */

public class IPConsole {

    // Main method. Calls scanner to read IP

    public static void main(String[] args) throws IOException {
        beginConsole();
        String ipCandidate = scanIP();
        verifyIP(ipCandidate);
    }

    // Prints initial message

    private static void beginConsole() {
        System.out.println("Enter your IP candidate and press Enter:");
    }

    // Scans new line for an IP address

    private static String scanIP() {
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        scanner.close();
        return ip;
    }

    // Verifies IP by using IPValidator methods

    private static void verifyIP(String ipCandidate) {
        IPValidator validator = new IPValidator();
        if (IPValidator.validateIP(ipCandidate))
            ipIsValid();
        else
            ipHasError(validator.getIpError());
    }

    // Prints message when IP is valid. If there is an error, prints the error

    private static void ipIsValid() {
        System.out.println("This IP can be used for a private network.");
    }

    private static void ipHasError(String error) {
        System.out.println("This IP cannot be used for a private network. " + error);
    }
}