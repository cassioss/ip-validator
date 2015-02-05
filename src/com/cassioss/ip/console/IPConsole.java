package com.cassioss.ip.console;

/**
 * Created by Cassio dos Santos Sousa on 05/02/2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.cassioss.ip.validator.IPValidator.validateIP;

public class IPConsole {

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your IP candidate and press Enter:");
        String ipCandidate = buffer.readLine();
        verifyIP(ipCandidate);
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