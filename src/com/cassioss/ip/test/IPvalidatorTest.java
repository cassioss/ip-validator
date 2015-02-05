package com.cassioss.ip.test;

import com.cassioss.ip.validator.IPvalidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IPvalidatorTest {

    @Test
    public void testNullAndEmpty() {
        String ip_null = null;
        String ip_empty = "";
        assertFalse(IPvalidator.validate_IP(ip_null));
        assertFalse(IPvalidator.validate_IP(ip_empty));
    }

    @Test
    public void testIFLocalhost() {
        String ip_home = "127.0.0.1";
        assertFalse(IPvalidator.validate_IP(ip_home));
    }

    @Test
    public void testIPsForValidCharacters() {
        String ip_one = "1.#.4.6";
        String ip_two = "1,.2.%6.7";
        String ip_three = "A.B.C.D";
        assertFalse(IPvalidator.validate_IP(ip_one));
        assertFalse(IPvalidator.validate_IP(ip_two));
        assertFalse(IPvalidator.validate_IP(ip_three));
    }

    @Test
    public void testNotFourParts() {
        String ip_one = "127001";
        String ip_two = "127.001";
        String ip_three = "127.0.01";
        String ip_five = "200.200.200.200.200";
        assertFalse(IPvalidator.validate_IP(ip_one));
        assertFalse(IPvalidator.validate_IP(ip_two));
        assertFalse(IPvalidator.validate_IP(ip_three));
        assertFalse(IPvalidator.validate_IP(ip_five));
    }

    @Test
    public void testEmptySubstrings() {
        String ip_1 = "..1.1";
        String ip_2 = "1.1.1..";
        String ip_3 = "...1";
        assertFalse(IPvalidator.validate_IP(ip_1));
        assertFalse(IPvalidator.validate_IP(ip_2));
        assertFalse(IPvalidator.validate_IP(ip_3));
    }

    @Test
    public void testZerosToTheLeft() {
        String ip_1 = "0.0.00.0";
        String ip_2 = "000.0.0.0";
        String ip_3 = "0.0.0.01";
        assertFalse(IPvalidator.validate_IP(ip_1));
        assertFalse(IPvalidator.validate_IP(ip_2));
        assertFalse(IPvalidator.validate_IP(ip_3));
    }

    @Test
    public void testIPsOutOfBounds() {
        String ip_1 = "0.0.0.1000";
        String ip_2 = "0.1.2.256";
        assertFalse(IPvalidator.validate_IP(ip_1));
        assertFalse(IPvalidator.validate_IP(ip_2));
    }

    @Test
    public void testForbiddenZones() {
        String ip_1 = "172.0.0.1";
        String ip_2 = "10.0.0.1";
        String ip_3 = "192.168.0.1";
        assertFalse(IPvalidator.validate_IP(ip_1));
        assertFalse(IPvalidator.validate_IP(ip_2));
        assertFalse(IPvalidator.validate_IP(ip_3));
    }

    @Test
    public void testValidIPs() {
        String ip1 = "200.200.200.200";
        String ip2 = "192.167.1.1";
        String ip3 = "0.0.0.0";
        String ip4 = "127.1.1.1";
        String ip5 = "255.255.255.255";
        assertTrue(IPvalidator.validate_IP(ip1));
        assertTrue(IPvalidator.validate_IP(ip2));
        assertTrue(IPvalidator.validate_IP(ip3));
        assertTrue(IPvalidator.validate_IP(ip4));
        assertTrue(IPvalidator.validate_IP(ip5));
    }
}