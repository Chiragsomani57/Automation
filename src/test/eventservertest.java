package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.eventserverpageobject;
import ipcamera.testcomponent.basetest;

public class eventservertest extends basetest {

    eventserverpageobject eventpage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\eventserver.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate to Event Servers ────────
    public void launchandopen() {
        loginpage.logintocamera();
        eventpage = new eventserverpageobject(driver);
        eventpage.clickconfiguration();
        eventpage.switchtopanelframe();
        eventpage.clickevent();
        eventpage.clickeventserver();
    }

    // ─────────────────────────────────────────────
    // TC01 — FTP Test Connection (fully automated)
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc01_ftp_testconnection() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc01_ftp_testconnection");

        launchandopen();
        eventpage.clickftptab();
        eventpage.switchtoconfigframe();

        eventpage.enableftp();
        eventpage.setftpserveraddress(data.get("ftpserver"));
        eventpage.setftpport(data.get("ftpport"));
        eventpage.setftpusername(data.get("ftpuser"));
        eventpage.setftppassword(data.get("ftppassword"));
        eventpage.setftpfoldername(data.get("ftpfolder"));
        eventpage.clickftptestconnection();

        soft.assertEquals(eventpage.getconnectionmessage(), data.get("connectionmessage"));

        System.out.println("=== TC01 FTP Test Connection ===");
        System.out.println("Server  : " + eventpage.getftpserveraddress());
        System.out.println("Message : " + eventpage.getconnectionmessage());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC02 — FTP Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc02_ftp_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_ftp_default");

        launchandopen();
        eventpage.clickftptab();
        eventpage.switchtoconfigframe();
        eventpage.clickdefaultandconfirm();
        soft.assertEquals(String.valueOf(eventpage.isftpenabled()),
            data.get("ftpenable"), "FTP Enable default mismatch!");
        soft.assertEquals(eventpage.getftpserveraddress(),
            data.get("ftpserver"), "FTP Server Address default mismatch!");
        soft.assertEquals(eventpage.getftpport(),
            data.get("ftpport"), "FTP Port default mismatch!");
        soft.assertEquals(eventpage.getftpusername(),
            data.get("ftpuser"), "FTP Username default mismatch!");
        soft.assertEquals(eventpage.getftppassword(),
            data.get("ftppassword"), "FTP Password default mismatch!");
        soft.assertEquals(eventpage.getftpfoldername(),
            data.get("ftpfolder"), "FTP Folder Name default mismatch!");

        System.out.println("=== TC02 FTP Default ===");
        System.out.println("Enable  : " + eventpage.isftpenabled());
        System.out.println("Server  : " + eventpage.getftpserveraddress());
        System.out.println("Port    : " + eventpage.getftpport());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC03 — Email Test Connection (message verification only)
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc03_email_testconnection() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc03_email_testconnection");

        launchandopen();
        eventpage.clickemailtab();
        eventpage.switchtoconfigframe();

        eventpage.enableemail();
        eventpage.setemailserveraddress(data.get("emailserver"));
        eventpage.setemailport(data.get("emailport"));
        eventpage.setemailusername(data.get("emailuser"));
        eventpage.setemailpassword(data.get("emailpassword"));
        eventpage.setsenderemailid(data.get("sendermail"));
        eventpage.setencryption(data.get("encryption"));
        eventpage.setreceivermail(data.get("receivermail"));
        eventpage.setmailtransmissiontimeout(data.get("mailtimeout"));
        eventpage.clickemailtestconnection();

        soft.assertEquals(eventpage.getconnectionmessage(),
            data.get("connectionmessage"));

        System.out.println("=== TC03 Email Test Connection ===");
        System.out.println("Server  : " + eventpage.getemailserveraddress());
        System.out.println("Message : " + eventpage.getconnectionmessage());
        System.out.println("Note    : Actual email receipt must be verified manually");

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC04 — Email Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc04_email_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_email_default");

        launchandopen();
        eventpage.clickemailtab();
        eventpage.switchtoconfigframe();
        eventpage.clickdefaultandconfirm();


        soft.assertEquals(String.valueOf(eventpage.isemailenabled()),
            data.get("emailenable"), "Email Enable default mismatch!");
        soft.assertEquals(eventpage.getemailserveraddress(),
            data.get("emailserver"), "Email Server Address default mismatch!");
        soft.assertEquals(eventpage.getemailport(),
            data.get("emailport"), "Email Port default mismatch!");
        soft.assertEquals(eventpage.getemailusername(),
            data.get("emailuser"), "Email Username default mismatch!");
        soft.assertEquals(eventpage.getsenderemailid(),
            data.get("sendermail"), "Sender Email default mismatch!");
        soft.assertEquals(eventpage.getencryption(),
            data.get("encryption"), "Encryption default mismatch!");
        soft.assertEquals(eventpage.getreceivermail(),
            data.get("receivermail"), "Receiver Email default mismatch!");
        soft.assertEquals(eventpage.getmailtransmissiontimeout(),
            data.get("mailtimeout"), "Mail Timeout default mismatch!");

        System.out.println("=== TC04 Email Default ===");
        System.out.println("Enable  : " + eventpage.isemailenabled());
        System.out.println("Server  : " + eventpage.getemailserveraddress());
        System.out.println("Port    : " + eventpage.getemailport());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC06 — TCP Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc06_tcp_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc06_tcp_default");

        launchandopen();
        eventpage.clicktcptab();
        eventpage.switchtoconfigframe();
        eventpage.clickdefaultandconfirm();


        soft.assertEquals(String.valueOf(eventpage.istcpenabled()),
            data.get("tcpenable"), "TCP Enable default mismatch!");
        soft.assertEquals(eventpage.gettcpserveraddress(),
            data.get("tcpserver"), "TCP Server Address default mismatch!");
        soft.assertEquals(eventpage.gettcpport(),
            data.get("tcpport"), "TCP Port default mismatch!");

        System.out.println("=== TC06 TCP Default ===");
        System.out.println("Enable : " + eventpage.istcpenabled());
        System.out.println("Server : " + eventpage.gettcpserveraddress());
        System.out.println("Port   : " + eventpage.gettcpport());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC10 — Download MIB
    // ─────────────────────────────────────────────



    // ─────────────────────────────────────────────
    // TC11 — SNMP Default
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void tc11_snmp_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc11_snmp_default");

        launchandopen();
        eventpage.clicksnmptab();
        eventpage.switchtoconfigframe();
        eventpage.clickdefaultandconfirm();

        soft.assertEquals(String.valueOf(eventpage.issnmpenabled()),
            data.get("snmpenable"), "SNMP Enable default mismatch!");
        soft.assertEquals(eventpage.getsnmpport(),
            data.get("snmpport"), "SNMP Port default mismatch!");
        soft.assertEquals(eventpage.getsnmpversion(),
            data.get("snmpversion"), "SNMP Version default mismatch!");
        soft.assertEquals(eventpage.getsysname(),
            data.get("sysname"), "System Name default mismatch!");
        soft.assertEquals(eventpage.getsyscontact(),
            data.get("syscontact"), "System Contact default mismatch!");
        soft.assertEquals(eventpage.getsyslocation(),
            data.get("syslocation"), "System Location default mismatch!");
        soft.assertEquals(eventpage.getaccesstype(),
            data.get("accesstype"), "Access Type default mismatch!");
        soft.assertEquals(eventpage.getcomname(),
            data.get("comname"), "Read Community Name default mismatch!");

        // ─── also verify notification tab defaults ─
        eventpage.clicksnmpnotificationlink();
        soft.assertEquals(String.valueOf(eventpage.istrapenabled()),
            data.get("trapenable"), "Trap Enable default mismatch!");
        soft.assertEquals(eventpage.gettrapipaddr(),
            data.get("trapipaddr"), "Trap IP Address default mismatch!");
        soft.assertEquals(eventpage.gettrapport(),
            data.get("trapport"), "Trap Port default mismatch!");
        soft.assertEquals(eventpage.getretryattempt(),
            data.get("retryattempt"), "Retry Attempt default mismatch!");
        soft.assertEquals(eventpage.getretryinterval(),
            data.get("retryinterval"), "Retry Interval default mismatch!");

//        System.out.println("=== TC11 SNMP Default ===");
//        System.out.println("SNMP Enable  : " + eventpage.issnmpenabled());
//        System.out.println("SNMP Port    : " + eventpage.getsnmpport());
//        System.out.println("SNMP Version : " + eventpage.getsnmpversion());
//        System.out.println("Trap Enable  : " + eventpage.istrapenabled());

        soft.assertAll();
    }
}
