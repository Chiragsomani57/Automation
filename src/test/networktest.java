package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.networkpageobject;
import ipcamera.testcomponent.basetest;

import javax.naming.NamingEnumeration;

public class networktest extends basetest {

    networkpageobject networkbasic;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\network.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + open network settings ───────────
    public void
    launchandopen() throws IOException {
        loginpage.logintocamera();
        networkbasic = new networkpageobject(driver);
        networkbasic.clickconfiguration();
        networkbasic.switchtopanelframe();
        networkbasic.clicknetworksetting();
        networkbasic.clickbasicsettab();
    }

    // ─── navigate to IP address settings ──────────
    public void openipaddress() {
        networkbasic.clickipaddresssetting();
        networkbasic.switchtoconfigframe();
    }

    // ─── navigate to ports ────────────────────────
    public void openports() {
        networkbasic.clickports();
        networkbasic.switchtoconfigframe();
    }

    // ─── navigate to NAS ──────────────────────────
    public void opennas() {
        networkbasic.clicknassetting();
        networkbasic.switchtoconfigframe();
    }

    // ─── navigate to multicast ────────────────────
    public void openmulticast() {
        networkbasic.clickmulticast();
        networkbasic.switchtoconfigframe();
    }

    // ─── navigate to IP filter ────────────────────
    public void openipfilter() {
        networkbasic.clickipfilter();
        networkbasic.switchtoconfigframe();
    }

    // ─── save + reopen same page ──────────────────
    public void saveandreopen(String page) {
        networkbasic.clicksave();
        networkbasic.switchtopanelframe();
        networkbasic.clickbasicsettab();
        switch (page) {
            case "ip":        openipaddress(); break;
            case "ports":     openports();     break;
            case "nas":       opennas();       break;
            case "multicast": openmulticast(); break;
            case "ipfilter":  openipfilter();  break;
        }
    }

    // ─── assert invalid value not accepted ────────
    public void assertnotaccepted(String actual, String invalid,
                                   String field, SoftAssert soft) {
        soft.assertNotEquals(actual, invalid,
            field + " should NOT accept: " + invalid);
    }

    // ══════════════════════════════════════════════
    // SECTION 1 — IP ADDRESS SETTINGS
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC01 — Default Values
    // ─────────────────────────────────────────────
    @Test
    public void tc01_ipaddress_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc01_ipaddress_default");

        launchandopen();
        networkbasic.clickdefaultandconfirm();

        // ─── reconnect to default IP after reset ──
        try { Thread.sleep(5000); } catch (InterruptedException e) {}
        driver.get("http://" + data.get("ipaddress") + "/html/index.html");
        loginpage.logintocamera();
        networkbasic = new networkpageobject(driver);
        networkbasic.clickconfiguration();
        networkbasic.switchtopanelframe();
        networkbasic.clickbasicsettab();
        openipaddress();

        soft.assertEquals(networkbasic.gethostname(),        data.get("hostname"),     "Hostname default mismatch!");
        soft.assertEquals(networkbasic.getipv4configuration(),data.get("ipconfig"),    "IP Config default mismatch!");
        soft.assertEquals(networkbasic.getipv4address(),     data.get("ipaddress"),    "IP Address default mismatch!");
        soft.assertEquals(networkbasic.getsubnetmask(),      data.get("subnetmask"),   "Subnet Mask default mismatch!");
        soft.assertEquals(networkbasic.getdefaultgateway(),  data.get("gateway"),      "Gateway default mismatch!");
        soft.assertEquals(networkbasic.getpreferreddns(),    data.get("preferreddns"), "Preferred DNS default mismatch!");

        System.out.println("=== TC01 IP Address Default ===");
        System.out.println("Hostname   : " + networkbasic.gethostname());
        System.out.println("IP Config  : " + networkbasic.getipv4configuration());
        System.out.println("IP Address : " + networkbasic.getipv4address());
        System.out.println("Subnet     : " + networkbasic.getsubnetmask());
        System.out.println("Gateway    : " + networkbasic.getdefaultgateway());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC02 — Change IP Address (Static)
    // ─────────────────────────────────────────────
    @Test(groups="sanity")
    public void tc02_ipchange_static() throws IOException, InterruptedException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_ipchange_static");

        launchandopen();
        openipaddress();

        networkbasic.setipv4configuration(data.get("ipconfig"));
        networkbasic.setipv4address(data.get("ipaddress"));
        networkbasic.setsubnetmask(data.get("subnetmask"));
        networkbasic.setdefaultgateway(data.get("gateway"));
        networkbasic.clicksave();

        // ─── reconnect to new IP ───────────────────
        reconnectonip(data.get("ipaddress"));
        networkbasic.clickconfiguration();
        networkbasic.switchtopanelframe();
        networkbasic.clicknetworksetting();
        networkbasic.clickbasicsettab();
        openipaddress();

        soft.assertEquals(networkbasic.getipv4address(),      data.get("ipaddress"),  "IP Address mismatch!");
        soft.assertEquals(networkbasic.getsubnetmask(),       data.get("subnetmask"), "Subnet Mask mismatch!");
        soft.assertEquals(networkbasic.getdefaultgateway(),   data.get("gateway"),    "Gateway mismatch!");

        System.out.println("=== TC02 IP Change Static ===");
        System.out.println("New IP     : " + networkbasic.getipv4address());
        System.out.println("Subnet     : " + networkbasic.getsubnetmask());
        System.out.println("Gateway    : " + networkbasic.getdefaultgateway());

        soft.assertAll();
        networkbasic.setipv4address(data.get("backtooriginalip"));
        networkbasic.clicksave();
    }



    // ─────────────────────────────────────────────
    // TC04 — DHCP Mode
    // ─────────────────────────────────────────────
    @Test
    public void tc04_dhcp() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_dhcp");

        launchandopen();
        openipaddress();

        networkbasic.setipv4configuration(data.get("ipconfig"));
        saveandreopen("ip");

        soft.assertEquals(networkbasic.getipv4configuration(),
            data.get("ipconfig"), "DHCP mode mismatch!");

        System.out.println("=== TC04 DHCP Mode ===");
        System.out.println("IP Config : " + networkbasic.getipv4configuration());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC05 — DNS Specified
    // ─────────────────────────────────────────────
    @Test
    public void tc05_dns_specified() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_dns_specified");

        launchandopen();
        openipaddress();

        networkbasic.selectdnsspecified();
        networkbasic.setpreferreddns(data.get("preferreddns"));
        networkbasic.setalternatedns(data.get("alternatedns"));
        saveandreopen("ip");

        soft.assertTrue(networkbasic.isdnsspecified(), "DNS Specified not selected!");
        soft.assertEquals(networkbasic.getpreferreddns(), data.get("preferreddns"), "Preferred DNS mismatch!");
        soft.assertEquals(networkbasic.getalternatedns(), data.get("alternatedns"), "Alternate DNS mismatch!");

        System.out.println("=== TC05 DNS Specified ===");
        System.out.println("Preferred : " + networkbasic.getpreferreddns());
        System.out.println("Alternate : " + networkbasic.getalternatedns());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC06 — DNS Auto
    // ─────────────────────────────────────────────
    @Test
    public void tc06_dns_auto() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        openipaddress();

        networkbasic.selectdnsauto();
        saveandreopen("ip");

        soft.assertTrue(networkbasic.isdnsauto(), "DNS Auto not selected!");

        System.out.println("=== TC06 DNS Auto ===");
        System.out.println("DNS Auto : " + networkbasic.isdnsauto());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC07 — Hostname Change
    // ─────────────────────────────────────────────
    @Test
    public void tc07_hostname_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_hostname_set");

        launchandopen();
        openipaddress();

        networkbasic.sethostname(data.get("hostname"));
        saveandreopen("ip");

        soft.assertEquals(networkbasic.gethostname(),
            data.get("hostname"), "Hostname set mismatch!");

        System.out.println("=== TC07 Hostname Set ===");
        System.out.println("Hostname : " + networkbasic.gethostname());

        soft.assertAll();
    }

    @Test
    public void tc07_hostname_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_hostname_default");

        launchandopen();
        openipaddress();

        networkbasic.sethostname(data.get("hostname"));
        saveandreopen("ip");

        soft.assertEquals(networkbasic.gethostname(),
            data.get("hostname"), "Hostname default mismatch!");

        System.out.println("=== TC07 Hostname Default ===");
        System.out.println("Hostname : " + networkbasic.gethostname());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC08 — Hostname Negative
    // ─────────────────────────────────────────────
    @Test
    public void tc08_hostname_empty() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        openipaddress();
        networkbasic.sethostname("");

        soft.assertNotEquals(networkbasic.gethostname(), "", "Hostname should not accept empty!");

        System.out.println("=== TC08 Hostname Empty === " + networkbasic.gethostname());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC09 — IP Address Negative
    // ─────────────────────────────────────────────
    @Test
    public void tc09_ip_invalid_format() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_ip_invalid_format");

        launchandopen();
        openipaddress();
        networkbasic.setipv4address(data.get("ipaddress"));

        assertnotaccepted(networkbasic.getipv4address(),
            data.get("ipaddress"), "IP Address", soft);

        System.out.println("=== TC09 IP Invalid Format === " + networkbasic.getipv4address());
        soft.assertAll();
    }

    @Test
    public void tc09_ip_empty() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        openipaddress();
        networkbasic.setipv4address("");

        soft.assertNotEquals(networkbasic.getipv4address(), "", "IP should not accept empty!");

        System.out.println("=== TC09 IP Empty === " + networkbasic.getipv4address());
        soft.assertAll();
    }

    @Test
    public void tc09_ip_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_ip_alpha");

        launchandopen();
        openipaddress();
        networkbasic.setipv4address(data.get("ipaddress"));

        assertnotaccepted(networkbasic.getipv4address(),
            data.get("ipaddress"), "IP Address", soft);

        System.out.println("=== TC09 IP Alpha === " + networkbasic.getipv4address());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 2 — IP ADDRESS FILTERING
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC11 — IP Filter Enable + Allow
    // ─────────────────────────────────────────────
    @Test
    public void tc11_ipfilter_allow() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc11_ipfilter_allow");

        launchandopen();
        openipfilter();

        networkbasic.enableipfilter();
        networkbasic.selectfilterallow();
        networkbasic.setipv4from(data.get("ipfrom"));
        networkbasic.setipv4to(data.get("ipto"));
        networkbasic.clickaddipfilter();
        saveandreopen("ipfilter");

        soft.assertTrue(networkbasic.isipfilterenabled(), "IP Filter not enabled!");
        soft.assertTrue(networkbasic.isfilterallow(),     "Filter Allow not selected!");

        System.out.println("=== TC11 IP Filter Allow ===");
        System.out.println("Enable : " + networkbasic.isipfilterenabled());
        System.out.println("Allow  : " + networkbasic.isfilterallow());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC12 — IP Filter Enable + Deny
    // ─────────────────────────────────────────────
    @Test
    public void tc12_ipfilter_deny() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc12_ipfilter_deny");

        launchandopen();
        openipfilter();

        networkbasic.enableipfilter();
        networkbasic.selectfilterdeny();
        networkbasic.setipv4from(data.get("ipfrom"));
        networkbasic.setipv4to(data.get("ipto"));
        networkbasic.clickaddipfilter();
        saveandreopen("ipfilter");

        soft.assertTrue(networkbasic.isipfilterenabled(), "IP Filter not enabled!");
        soft.assertTrue(networkbasic.isfilterdeny(),      "Filter Deny not selected!");

        System.out.println("=== TC12 IP Filter Deny ===");
        System.out.println("Deny : " + networkbasic.isfilterdeny());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC13 — IP Filter Disable
    // ─────────────────────────────────────────────
    @Test
    public void tc13_ipfilter_disable() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        openipfilter();

        networkbasic.disableipfilter();
        saveandreopen("ipfilter");

        soft.assertFalse(networkbasic.isipfilterenabled(), "IP Filter should be disabled!");

        System.out.println("=== TC13 IP Filter Disable === " + networkbasic.isipfilterenabled());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 3 — NAS SETTINGS
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC15 — NAS CIFS
    // ─────────────────────────────────────────────
    @Test(groups="sanity")
    public void tc15_nas_cifs() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc15_nas_cifs");

        launchandopen();
        opennas();

        networkbasic.enablenas();
        networkbasic.setnasname(data.get("nasname"));
        networkbasic.setnasipaddress(data.get("nasip"));
        networkbasic.setnasusername(data.get("nasuser"));
        networkbasic.setnaspassword(data.get("naspassword"));
        networkbasic.setnasfileformat(data.get("nasformat"));
        networkbasic.setnasfoldername(data.get("nasfolder"));

        // ─── click test connection ─────────────────
        networkbasic.clicktestconnection();

        Assert.assertEquals(networkbasic.getvalidationmessage(),data.get("valdiationmessage"));

    }

    // ─────────────────────────────────────────────
    // TC16 — NAS NFS
    // ─────────────────────────────────────────────
    @Test(groups="sanity")
    public void tc16_nas_nfs() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc16_nas_nfs");

        launchandopen();
        opennas();

        networkbasic.enablenas();
        networkbasic.setnasname(data.get("nasname"));
        networkbasic.setnasipaddress(data.get("nasip"));
        networkbasic.setnasusername(data.get("nasuser"));
        networkbasic.setnaspassword(data.get("naspassword"));
        networkbasic.setnasfileformat(data.get("nasformat"));
        networkbasic.setnasfoldername(data.get("nasfolder"));
        networkbasic.clicktestconnection();

        networkbasic.clicktestconnection();

        Assert.assertEquals(networkbasic.getvalidationmessage(),data.get("valdiationmessage"));

    }

    // ─────────────────────────────────────────────
    // TC17 — NAS Disable
    // ─────────────────────────────────────────────
    @Test
    public void tc17_nas_disable() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        opennas();

        networkbasic.disablenas();
        saveandreopen("nas");

        soft.assertFalse(networkbasic.isnasdenabled(), "NAS should be disabled!");

        System.out.println("=== TC17 NAS Disable === " + networkbasic.isnasdenabled());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC18 — NAS Negative Empty IP
    // ─────────────────────────────────────────────
    @Test
    public void tc18_nas_empty_ip() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        opennas();

        networkbasic.enablenas();
        networkbasic.setnasipaddress("");

        soft.assertNotEquals(networkbasic.getnasipaddress(), "",
            "NAS IP should not accept empty!");

        System.out.println("=== TC18 NAS Empty IP === " + networkbasic.getnasipaddress());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 4 — PORTS
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC20 — Default Port Values
    // ─────────────────────────────────────────────
    @Test(groups="sanity")
    public void tc20_port_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc20_port_default");

        launchandopen();
        openports();

        networkbasic.clickdefaultandconfirm();

        soft.assertEquals(networkbasic.gethttpport(),      data.get("httpport"),    "HTTP Port default mismatch!");
        soft.assertEquals(networkbasic.getrtspport(),      data.get("rtspport"),    "RTSP Port default mismatch!");
        soft.assertEquals(networkbasic.gethttpsport(),     data.get("httpsport"),   "HTTPS Port default mismatch!");
        soft.assertEquals(networkbasic.getrtpvideoport(),  data.get("rtpport"),     "RTP Port default mismatch!");
        soft.assertEquals(networkbasic.getrtcpvideoport(), data.get("rtcpport"),    "RTCP Port default mismatch!");
        soft.assertEquals(networkbasic.getexternalrtspport(),data.get("externalrtsp"),"External RTSP default mismatch!");
        soft.assertEquals(String.valueOf(networkbasic.isportmappingenabled()),
            data.get("portmapping"), "Port Mapping default mismatch!");

        System.out.println("=== TC20 Default Ports ===");
        System.out.println("HTTP  : " + networkbasic.gethttpport());
        System.out.println("RTSP  : " + networkbasic.getrtspport());
        System.out.println("HTTPS : " + networkbasic.gethttpsport());
        System.out.println("RTP   : " + networkbasic.getrtpvideoport());
        System.out.println("RTCP  : " + networkbasic.getrtcpvideoport());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC21 — Change All Ports
    // ─────────────────────────────────────────────
    @Test(groups="sanity")
    public void tc21_port_changeall() throws IOException, InterruptedException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc21_port_changeall");

        launchandopen();
        openports();

        networkbasic.sethttpport(data.get("httpport"));
        networkbasic.setrtspport(data.get("rtspport"));
        networkbasic.sethttpsport(data.get("httpsport"));
        networkbasic.setrtpvideoport(data.get("rtpport"));
        networkbasic.enableportmapping();
        networkbasic.setexternalrtspport(data.get("externalrtsp"));
        networkbasic.clicksave();

        // ─── reconnect on new HTTP port ───────────
        reconnectonport(data.get("httpport"));
        networkbasic = new networkpageobject(driver);
        networkbasic.clickconfiguration();
        networkbasic.switchtopanelframe();
        networkbasic.clicknetworksetting();
        networkbasic.clickbasicsettab();
        openports();

        soft.assertEquals(networkbasic.gethttpport(),      data.get("httpport"),  "HTTP Port mismatch!");
        soft.assertEquals(networkbasic.getrtspport(),      data.get("rtspport"),  "RTSP Port mismatch!");
        soft.assertEquals(networkbasic.gethttpsport(),     data.get("httpsport"), "HTTPS Port mismatch!");
        soft.assertEquals(networkbasic.getrtpvideoport(),  data.get("rtpport"),   "RTP Port mismatch!");
        soft.assertEquals(networkbasic.getrtcpvideoport(), data.get("rtcpport"),  "RTCP Port mismatch!");

        System.out.println("=== TC21 Change All Ports ===");
        System.out.println("HTTP  : " + networkbasic.gethttpport());
        System.out.println("RTSP  : " + networkbasic.getrtspport());
        System.out.println("HTTPS : " + networkbasic.gethttpsport());
        System.out.println("RTP   : " + networkbasic.getrtpvideoport());
        System.out.println("RTCP  : " + networkbasic.getrtcpvideoport());

        soft.assertAll();

        // ─── IMPORTANT: restore to default port ───
       networkbasic.clickdefaultandconfirm();
    }

    // ─────────────────────────────────────────────
    // TC22 — Port Access Verification
    // ─────────────────────────────────────────────
    @Test
    public void tc22_port_access() throws IOException, InterruptedException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc22_port_access");

        launchandopen();
        openports();

        networkbasic.sethttpport(data.get("httpport"));
        networkbasic.clicksave();

        // ─── reconnect on new port ─────────────────
        reconnectonport(data.get("httpport"));

        // ─── verify camera accessible on new port ─
        soft.assertTrue(driver.getCurrentUrl().contains(data.get("httpport")),
            "Camera not accessible on port " + data.get("httpport"));

        System.out.println("=== TC22 Port Access ===");
        System.out.println("URL : " + driver.getCurrentUrl());

        soft.assertAll();

        // ─── restore default port ──────────────────
        networkbasic = new networkpageobject(driver);
        networkbasic.clickconfiguration();
        networkbasic.switchtopanelframe();
        networkbasic.clickbasicsettab();
        openports();
        networkbasic.sethttpport("80");
        networkbasic.clicksave();
        restoredefaultport();
    }

    // ─────────────────────────────────────────────
    // TC24 — Port Mapping
    // ─────────────────────────────────────────────
    @Test
    public void tc24_portmapping_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc24_portmapping_enable");

        launchandopen();
        openports();

        networkbasic.enableportmapping();
        networkbasic.setexternalrtspport(data.get("externalrtsp"));
        saveandreopen("ports");

        soft.assertTrue(networkbasic.isportmappingenabled(), "Port Mapping not enabled!");
        soft.assertEquals(networkbasic.getexternalrtspport(),
            data.get("externalrtsp"), "External RTSP mismatch!");

        System.out.println("=== TC24 Port Mapping Enable ===");
        System.out.println("Port Mapping    : " + networkbasic.isportmappingenabled());
        System.out.println("External RTSP   : " + networkbasic.getexternalrtspport());

        soft.assertAll();
    }

    @Test
    public void tc24_portmapping_disable() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        openports();

        networkbasic.disableportmapping();
        saveandreopen("ports");

        soft.assertFalse(networkbasic.isportmappingenabled(), "Port Mapping should be disabled!");

        System.out.println("=== TC24 Port Mapping Disable === " + networkbasic.isportmappingenabled());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC25 — HTTP Port Negative
    // ─────────────────────────────────────────────
    @Test
    public void tc25_httpport_zero() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc25_httpport_zero");

        launchandopen();
        openports();
        networkbasic.sethttpport(data.get("httpport"));

        assertnotaccepted(networkbasic.gethttpport(), data.get("httpport"), "HTTP Port", soft);
        System.out.println("=== TC25 HTTP Port Zero === " + networkbasic.gethttpport());
        soft.assertAll();
    }

    @Test
    public void tc25_httpport_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc25_httpport_above");

        launchandopen();
        openports();
        networkbasic.sethttpport(data.get("httpport"));

        assertnotaccepted(networkbasic.gethttpport(), data.get("httpport"), "HTTP Port", soft);
        System.out.println("=== TC25 HTTP Port Above Max === " + networkbasic.gethttpport());
        soft.assertAll();
    }

    @Test
    public void tc25_httpport_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc25_httpport_alpha");

        launchandopen();
        openports();
        networkbasic.sethttpport(data.get("httpport"));

        assertnotaccepted(networkbasic.gethttpport(), data.get("httpport"), "HTTP Port", soft);
        System.out.println("=== TC25 HTTP Port Alpha === " + networkbasic.gethttpport());
        soft.assertAll();
    }

    @Test
    public void tc25_httpport_empty() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        openports();
        networkbasic.sethttpport("");

        soft.assertNotEquals(networkbasic.gethttpport(), "", "HTTP Port should not accept empty!");
        System.out.println("=== TC25 HTTP Port Empty === " + networkbasic.gethttpport());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 5 — MULTICAST
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC27 — Enable Multicast
    // ─────────────────────────────────────────────
    @Test
    public void tc27_multicast_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc27_multicast_enable");

        launchandopen();
        openmulticast();

        networkbasic.enablemulticast();
        networkbasic.setmulticastaddress(data.get("multicastaddress"));
        networkbasic.setmulticastttl(data.get("ttl"));
        saveandreopen("multicast");

        soft.assertTrue(networkbasic.ismulticastenabled(), "Multicast not enabled!");
        soft.assertEquals(networkbasic.getmulticastaddress(),
            data.get("multicastaddress"), "Multicast Address mismatch!");
        soft.assertEquals(networkbasic.getmulticastttl(),
            data.get("ttl"), "TTL mismatch!");

        System.out.println("=== TC27 Multicast Enable ===");
        System.out.println("Address : " + networkbasic.getmulticastaddress());
        System.out.println("TTL     : " + networkbasic.getmulticastttl());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC28 — Disable Multicast
    // ─────────────────────────────────────────────
    @Test
    public void tc28_multicast_disable() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        openmulticast();

        networkbasic.disablemulticast();
        saveandreopen("multicast");

        soft.assertFalse(networkbasic.ismulticastenabled(), "Multicast should be disabled!");

        System.out.println("=== TC28 Multicast Disable === " + networkbasic.ismulticastenabled());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC30 — TTL Boundary
    // ─────────────────────────────────────────────
    @Test
    public void tc30_ttl_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc30_ttl_min");

        launchandopen();
        openmulticast();
        networkbasic.enablemulticast();
        networkbasic.setmulticastaddress(data.get("multicastaddress"));
        networkbasic.setmulticastttl(data.get("ttl"));
        saveandreopen("multicast");

        soft.assertEquals(networkbasic.getmulticastttl(), data.get("ttl"), "TTL min mismatch!");
        System.out.println("=== TC30 TTL Min === " + networkbasic.getmulticastttl());
        soft.assertAll();
    }

    @Test
    public void tc30_ttl_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc30_ttl_max");

        launchandopen();
        openmulticast();
        networkbasic.enablemulticast();
        networkbasic.setmulticastaddress(data.get("multicastaddress"));
        networkbasic.setmulticastttl(data.get("ttl"));
        saveandreopen("multicast");

        soft.assertEquals(networkbasic.getmulticastttl(), data.get("ttl"), "TTL max mismatch!");
        System.out.println("=== TC30 TTL Max === " + networkbasic.getmulticastttl());
        soft.assertAll();
    }

    @Test
    public void tc30_ttl_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc30_ttl_set");

        launchandopen();
        openmulticast();
        networkbasic.enablemulticast();
        networkbasic.setmulticastaddress(data.get("multicastaddress"));
        networkbasic.setmulticastttl(data.get("ttl"));
        saveandreopen("multicast");

        soft.assertEquals(networkbasic.getmulticastttl(), data.get("ttl"), "TTL set mismatch!");
        System.out.println("=== TC30 TTL Set === " + networkbasic.getmulticastttl());
        soft.assertAll();
    }

    @Test
    public void tc30_ttl_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc30_ttl_above");

        launchandopen();
        openmulticast();
        networkbasic.enablemulticast();
        networkbasic.setmulticastttl(data.get("ttl"));

        assertnotaccepted(networkbasic.getmulticastttl(), data.get("ttl"), "TTL", soft);
        System.out.println("=== TC30 TTL Above Max === " + networkbasic.getmulticastttl());
        soft.assertAll();
    }

    @Test
    public void tc30_ttl_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc30_ttl_alpha");

        launchandopen();
        openmulticast();
        networkbasic.enablemulticast();
        networkbasic.setmulticastttl(data.get("ttl"));

        assertnotaccepted(networkbasic.getmulticastttl(), data.get("ttl"), "TTL", soft);
        System.out.println("=== TC30 TTL Alpha === " + networkbasic.getmulticastttl());
        soft.assertAll();
    }
}
