package com.cassioss.ip.console;

/**
 * Created by Cassio dos Santos Sousa on 05/02/2015.
 */

import java.io.IOException;
import java.util.Scanner;

import static com.cassioss.ip.validator.IPValidator.validateIP;

public class IPConsole {

    public static void main(String[] args) throws IOException {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Enter your IP candidate and press Enter:");
        String ipCandidate = userInputScanner.nextLine();
        System.out.println(ipCandidate + ipCandidate + ipCandidate);
        if (validateIP("127.0.0.1"))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    private static void verifyIP(String ip) {
        System.out.println(ip);
        if (validateIP(ip))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    private static void ipIsValid() {
        System.out.println("This ip is valid.");
    }

    private static void showError(String error) {
        System.out.println("This IP is not valid. " + error);
    }
}