package com.cassioss.ip.test;

import com.cassioss.ip.validator.IPValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IPValidatorTest {

    @Test
    public void testNullAndEmpty() {
        String ipEmpty = "";
        assertFalse(IPValidator.validateIP(null));
        assertFalse(IPValidator.validateIP(ipEmpty));
    }

    @Test
    public void testForLocalhost() {
        String ipHome = "127.0.0.1";
        assertFalse(IPValidator.validateIP(ipHome));
    }

    @Test
    public void testIPsForInvalidCharacters() {
        String ipInvalid1 = "1.#.4.6";
        String ipInvalid2 = "1,.2.%6.7";
        String ipInvalid3 = "A.B.C.D";
        assertFalse(IPValidator.validateIP(ipInvalid1));
        assertFalse(IPValidator.validateIP(ipInvalid2));
        assertFalse(IPValidator.validateIP(ipInvalid3));
    }

    @Test
    public void testIPsWithoutFourParts() {
        String ipOnePart = "127001";
        String ipTwoParts = "127.001";
        String ipThreeParts = "127.0.01";
        String ipFiveParts = "200.200.200.200.200";
        assertFalse(IPValidator.validateIP(ipOnePart));
        assertFalse(IPValidator.validateIP(ipTwoParts));
        assertFalse(IPValidator.validateIP(ipThreeParts));
        assertFalse(IPValidator.validateIP(ipFiveParts));
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
        String ipTwoZeros = "0.0.00.0";
        String ipThreeZeros = "000.0.0.0";
        String ipZeroBeforeOne = "0.0.0.01";
        assertFalse(IPValidator.validateIP(ipTwoZeros));
        assertFalse(IPValidator.validateIP(ipThreeZeros));
        assertFalse(IPValidator.validateIP(ipZeroBeforeOne));
    }

    @Test
    public void testIPsOutOfBounds() {
        String ip1000 = "0.0.0.1000";
        String ip256 = "0.1.2.256";
        assertFalse(IPValidator.validateIP(ip1000));
        assertFalse(IPValidator.validateIP(ip256));
    }

    @Test
    public void testForbiddenZones() {
        String ip10 = "10.0.0.1";
        String ip192 = "192.168.0.1";
        String ip172Low = "172.16.0.1";
        String ip172High = "172.31.0.1";
        assertFalse(IPValidator.validateIP(ip10));
        assertFalse(IPValidator.validateIP(ip192));
        assertFalse(IPValidator.validateIP(ip172Low));
        assertFalse(IPValidator.validateIP(ip172High));
    }

    @Test
    public void testValidIPs() {
        String ipValid1 = "200.200.200.200";
        String ipValid2 = "192.167.1.1";
        String ipValid3 = "0.0.0.0";
        String ipValid4 = "127.1.1.1";
        String ipValid5 = "255.255.255.255";
        String ipValid6 = "172.15.0.1";
        String ipValid7 = "172.32.0.1";
        assertTrue(IPValidator.validateIP(ipValid1));
        assertTrue(IPValidator.validateIP(ipValid2));
        assertTrue(IPValidator.validateIP(ipValid3));
        assertTrue(IPValidator.validateIP(ipValid4));
        assertTrue(IPValidator.validateIP(ipValid5));
        assertTrue(IPValidator.validateIP(ipValid6));
        assertTrue(IPValidator.validateIP(ipValid7));
    }
}