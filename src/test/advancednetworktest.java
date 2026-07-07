package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.advancednetworkpageobject;
import ipcamera.testcomponent.basetest;

public class advancednetworktest extends basetest {

    advancednetworkpageobject advnetpage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\advancednetwork.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate to advanced settings ────
    public void launchandopen() {
        loginpage.logintocamera();
        advnetpage = new advancednetworkpageobject(driver);
        advnetpage.clickconfiguration();
        advnetpage.switchtopanelframe();
        advnetpage.clicknetworksetting();
        advnetpage.clickadvancedsettingtab();
    }

    // ─── default + reopen same tab ────────────────
    public void opentab(String tab) {

        switch (tab) {
            case "upnp":  advnetpage.clickupnptab();  switchtoconfigframe();  break;
            case "ddns":  advnetpage.clickddnstab();  switchtoconfigframe(); break;
            case "mxdns": advnetpage.clickmxdnstab(); switchtoconfigframe(); break;
            case "dot1x": advnetpage.clickdot1xtab(); switchtoconfigframe(); break;
        }
    }

    // ─────────────────────────────────────────────
    // TC02 — UPnP and Bonjour Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc02_upnp_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_upnp_default");

        launchandopen();
        opentab("upnp");
        advnetpage.clickdefaultandconfirm();

        soft.assertEquals(String.valueOf(advnetpage.isupnpselected()),
            data.get("upnp"), "UPnP default mismatch!");
        soft.assertEquals(advnetpage.getupnpname(),
            data.get("upnpname"), "UPnP Friendly Name default mismatch!");
        soft.assertEquals(String.valueOf(advnetpage.isupnpportforwardingselected()),
            data.get("upnpfwd"), "UPnP Port Forwarding default mismatch!");
        soft.assertEquals(advnetpage.getexternalhttpsport(),
            data.get("externalhttps"), "External HTTPS Port default mismatch!");
        soft.assertEquals(advnetpage.getexternalrtspport(),
            data.get("externalrtsp"), "External RTSP Port default mismatch!");
        soft.assertEquals(String.valueOf(advnetpage.isbonjourselected()),
            data.get("bonjour"), "Bonjour default mismatch!");
        soft.assertEquals(advnetpage.getbonjourname(),
            data.get("bonjourname"), "Bonjour Friendly Name default mismatch!");

        System.out.println("=== TC02 UPnP and Bonjour Default ===");
        System.out.println("UPnP             : " + advnetpage.isupnpselected());
        System.out.println("UPnP Name        : " + advnetpage.getupnpname());
        System.out.println("UPnP Forwarding  : " + advnetpage.isupnpportforwardingselected());
        System.out.println("External HTTPS   : " + advnetpage.getexternalhttpsport());
        System.out.println("External RTSP    : " + advnetpage.getexternalrtspport());
        System.out.println("Bonjour          : " + advnetpage.isbonjourselected());
        System.out.println("Bonjour Name     : " + advnetpage.getbonjourname());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC05 — DDNS Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc05_ddns_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_ddns_default");

        launchandopen();
        opentab("ddns");

        soft.assertEquals(String.valueOf(advnetpage.isddnsenabled()),
            data.get("ddns"), "DDNS Enable default mismatch!");
        soft.assertEquals(advnetpage.getddnsusername(),
            data.get("ddnsuser"), "DDNS Username default mismatch!");
        soft.assertEquals(advnetpage.getddnspassword(),
            data.get("ddnspassword"), "DDNS Password default mismatch!");
        soft.assertEquals(advnetpage.getddnshostname(),
            data.get("ddnshost"), "DDNS Hostname default mismatch!");
        soft.assertEquals(advnetpage.getddnsupdateinterval(),
            data.get("ddnsupdate"), "DDNS Update Interval default mismatch!");

        System.out.println("=== TC05 DDNS Default ===");
        System.out.println("DDNS Enable     : " + advnetpage.isddnsenabled());
        System.out.println("DDNS Username   : " + advnetpage.getddnsusername());
        System.out.println("DDNS Hostname   : " + advnetpage.getddnshostname());
        System.out.println("Update Interval : " + advnetpage.getddnsupdateinterval());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC08 — Matrix DNS Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc08_mxdns_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_mxdns_default");

        launchandopen();
        opentab("mxdns");


        soft.assertEquals(String.valueOf(advnetpage.ismxdnsenabled()),
            data.get("mxdns"), "Matrix DNS Enable default mismatch!");
        soft.assertEquals(advnetpage.getmxdnshostname(),
            data.get("mxdnshost"), "Matrix DNS Hostname default mismatch!");
        soft.assertEquals(advnetpage.getmxdnsfwdport(),
            data.get("mxdnsport"), "Matrix DNS Forwarded Port default mismatch!");

        System.out.println("=== TC08 Matrix DNS Default ===");
        System.out.println("Matrix DNS Enable : " + advnetpage.ismxdnsenabled());
        System.out.println("Hostname          : " + advnetpage.getmxdnshostname());
        System.out.println("Forwarded Port    : " + advnetpage.getmxdnsfwdport());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC10 — 802.1X Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc10_dot1x_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc10_dot1x_default");

        launchandopen();


        soft.assertEquals(String.valueOf(advnetpage.isdot1xenabled()),
            data.get("dot1x"), "802.1X Enable default mismatch!");
        soft.assertEquals(advnetpage.getdot1xeapmethod(),
            data.get("eapmethod"), "802.1X EAP Method default mismatch!");
        soft.assertEquals(advnetpage.getdot1xusername(),
            data.get("dot1xuser"), "802.1X Username default mismatch!");
        soft.assertEquals(advnetpage.getdot1xpassword(),
            data.get("dot1xpassword"), "802.1X Password default mismatch!");
        soft.assertEquals(advnetpage.getdot1xconfirmpassword(),
            data.get("dot1xconfirmpassword"), "802.1X Confirm Password default mismatch!");

        System.out.println("=== TC10 802.1X Default ===");
        System.out.println("802.1X Enable : " + advnetpage.isdot1xenabled());
        System.out.println("EAP Method    : " + advnetpage.getdot1xeapmethod());
        System.out.println("Username      : " + advnetpage.getdot1xusername());

        soft.assertAll();
    }
}
