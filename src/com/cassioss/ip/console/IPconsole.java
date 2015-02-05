package com.cassioss.ip.console;

/**
 * Created by Cassio dos Santos Sousa on 05/02/2015.
 */

import com.cassioss.ip.validator.IPValidator;

import java.io.Console;

public class IPConsole {

    public static void main(String[] args) {
        Console console = System.console();
        console.printf("Enter your IP and press Enter: ");
        String ip = console.readLine();
        if (IPValidator.validateIP(ip))
            System.out.println("This IP is valid");
        else
            System.out.print("This IP is not valid");
    }

    private void showError(String error){

    }
}