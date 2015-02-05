package com.cassioss.ip.console;

/**
 * Created by Cassio dos Santos Sousa on 05/02/2015.
 */

import com.cassioss.ip.validator.IPValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPConsole {

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your IP candidate and press Enter: ");
        String ipCandidate = buffer.readLine();
        verifyIP(ipCandidate);
    }

    private static void verifyIP(String ip) {
        IPValidator validator = null;
        if (validator.validateIP(ip))
            ipIsValid();
        else
            showError(validator.getError());
    }

    private static void ipIsValid() {
        System.out.println("This ip is valid.");
    }

    private static void showError(String error) {
        System.out.println("This IP is not valid. " + error);
    }
}