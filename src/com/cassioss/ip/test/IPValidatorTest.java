package com.cassioss.ip.test;

import com.cassioss.ip.validator.IPValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IPValidatorTest {

    @Test
    public void testNullAndEmpty() {
        String ip_null = null;
        String ip_empty = "";
        assertFalse(IPValidator.validateIP(ip_null));
        assertFalse(IPValidator.validateIP(ip_empty));
    }

    @Test
    public void testIFLocalhost() {
        String ip_home = "127.0.0.1";
        assertFalse(IPValidator.validateIP(ip_home));
    }

    @Test
    public void testIPsForValidCharacters() {
        String ip_one = "1.#.4.6";
        String ip_two = "1,.2.%6.7";
        String ip_three = "A.B.C.D";
        assertFalse(IPValidator.validateIP(ip_one));
        assertFalse(IPValidator.validateIP(ip_two));
        assertFalse(IPValidator.validateIP(ip_three));
    }

    @Test
    public void testNotFourParts() {
        String ip_one = "127001";
        String ip_two = "127.001";
        String ip_three = "127.0.01";
        String ip_five = "200.200.200.200.200";
        assertFalse(IPValidator.validateIP(ip_one));
        assertFalse(IPValidator.validateIP(ip_two));
        assertFalse(IPValidator.validateIP(ip_three));
        assertFalse(IPValidator.validateIP(ip_five));
    }

    @Test
    public void testEmptySubstrings() {
        String ip_1 = "..1.1";
        String ip_2 = "1.1.1..";
        String ip_3 = "...1";
        assertFalse(IPValidator.validateIP(ip_1));
        assertFalse(IPValidator.validateIP(ip_2));
        assertFalse(IPValidator.validateIP(ip_3));
    }

    @Test
    public void testZerosToTheLeft() {
        String ip_1 = "0.0.00.0";
        String ip_2 = "000.0.0.0";
        String ip_3 = "0.0.0.01";
        assertFalse(IPValidator.validateIP(ip_1));
        assertFalse(IPValidator.validateIP(ip_2));
        assertFalse(IPValidator.validateIP(ip_3));
    }

    @Test
    public void testIPsOutOfBounds() {
        String ip_1 = "0.0.0.1000";
        String ip_2 = "0.1.2.256";
        assertFalse(IPValidator.validateIP(ip_1));
        assertFalse(IPValidator.validateIP(ip_2));
    }

    @Test
    public void testForbiddenZones() {
        String ip1 = "10.0.0.1";
        String ip2 = "192.168.0.1";
        String ip3 = "172.16.0.1";
        String ip4 = "172.31.0.1";
        assertFalse(IPValidator.validateIP(ip1));
        assertFalse(IPValidator.validateIP(ip2));
        assertFalse(IPValidator.validateIP(ip3));
        assertFalse(IPValidator.validateIP(ip4));
    }

    @Test
    public void testValidIPs() {
        String ip1 = "200.200.200.200";
        String ip2 = "192.167.1.1";
        String ip3 = "0.0.0.0";
        String ip4 = "127.1.1.1";
        String ip5 = "255.255.255.255";
        String ip6 = "172.15.0.1";
        String ip7 = "172.32.0.1";
        assertTrue(IPValidator.validateIP(ip1));
        assertTrue(IPValidator.validateIP(ip2));
        assertTrue(IPValidator.validateIP(ip3));
        assertTrue(IPValidator.validateIP(ip4));
        assertTrue(IPValidator.validateIP(ip5));
        assertTrue(IPValidator.validateIP(ip6));
        assertTrue(IPValidator.validateIP(ip7));
    }
}