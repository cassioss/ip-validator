package com.cassioss.ip.console;

/**
 * Created by Cassio dos Santos Sousa on 05/02/2015.
 */

import com.cassioss.ip.validator.IPvalidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPconsole {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter IP candidate and press Enter: ");
        String ip = br.readLine();
        if (IPvalidator.validate_IP(ip))
            System.out.println("This IP is valid");
        else
            System.out.print("This IP is not valid");
    }
}
