package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.datetimepageobject;
import ipcamera.testcomponent.basetest;

public class datetimetest extends basetest {

    datetimepageobject datetimepage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\datetime.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate to date and time page ───
    public void launchandopen() {
        loginpage.logintocamera();
        datetimepage = new datetimepageobject(driver);
        datetimepage.clickconfiguration();
        switchtopanelframe();
//        datetimepage.clickbasicsetting();
        datetimepage.clickdateandtime();
        switchtoconfigframe();
    }

    // ═══════════════════════════════════════════════
    // SECTION 1 — BASIC SETTINGS
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC01 — Verify Default Values
    // ─────────────────────────────────────────────
    @Test
    public void defaultdatetime() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultdatetime");

        launchandopen();
        datetimepage.clickdefaultandconfirm();  // click Default → Yes


        soft.assertEquals(
            String.valueOf(datetimepage.issetviaonvifselected()),
            data.get("setviaonvif"), "Set via ONVIF mismatch!");
        soft.assertEquals(
            String.valueOf(datetimepage.issetviaApiselected()),
            data.get("setviaapi"), "Set via API mismatch!");
        soft.assertEquals(
            String.valueOf(datetimepage.issynchronizeselected()),
            data.get("synchronize"), "NTP Sync mismatch!");
        soft.assertEquals(
            String.valueOf(datetimepage.isdstselected()),
            data.get("dst"), "DST mismatch!");

        System.out.println("=== TC01 Default DateTime ===");
        System.out.println("Set via ONVIF : " + datetimepage.issetviaonvifselected());
        System.out.println("Set via API   : " + datetimepage.issetviaApiselected());
        System.out.println("NTP Sync      : " + datetimepage.issynchronizeselected());
        System.out.println("DST           : " + datetimepage.isdstselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC02 — Set Timezone India
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void settimezone_india() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("settimezone_india");

        launchandopen();
        datetimepage.settimezone(data.get("timezone"));
        datetimepage.clicksave();


        soft.assertEquals(datetimepage.gettimezone(),
            data.get("timezone"), "Timezone India mismatch!");

        System.out.println("=== TC02 Set Timezone India ===");
        System.out.println("Timezone : " + datetimepage.gettimezone());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC03 — Set Timezone UTC
    // ─────────────────────────────────────────────
    @Test
    public void settimezone_utc() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("settimezone_utc");

        launchandopen();
        datetimepage.settimezone(data.get("timezone"));
        datetimepage.clicksave();


        soft.assertEquals(datetimepage.gettimezone(),
            data.get("timezone"), "Timezone UTC mismatch!");

        System.out.println("=== TC03 Set Timezone UTC ===");
        System.out.println("Timezone : " + datetimepage.gettimezone());

        soft.assertAll();
    }

    

    // ─────────────────────────────────────────────
    // TC05 — Set Date and Time Normal
    // ─────────────────────────────────────────────
    @Test
    public void setdatetime_normal() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setdatetime_normal");

        launchandopen();

        datetimepage.settimezone(data.get("timezone"));
        datetimepage.setdate(data.get("date"));
        datetimepage.sethour(data.get("hour"));
        datetimepage.setminute(data.get("minute"));
        datetimepage.setsecond(data.get("second"));
        datetimepage.clickset();


        soft.assertEquals(datetimepage.getdate(),   data.get("date"),   "Date mismatch!");
        soft.assertEquals(datetimepage.gethour(),   data.get("hour"),   "Hour mismatch!");
        soft.assertEquals(datetimepage.getminute(), data.get("minute"), "Minute mismatch!");
        soft.assertEquals(datetimepage.getsecond(), data.get("second"), "Second mismatch!");

        System.out.println("=== TC05 Set Date and Time Normal ===");
        System.out.println("Date   : " + datetimepage.getdate());
        System.out.println("Hour   : " + datetimepage.gethour());
        System.out.println("Minute : " + datetimepage.getminute());
        System.out.println("Second : " + datetimepage.getsecond());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC06 — Set Date and Time Min Values
    // ─────────────────────────────────────────────
    @Test
    public void setdatetime_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setdatetime_min");

        launchandopen();

        datetimepage.setdate(data.get("date"));
        datetimepage.sethour(data.get("hour"));
        datetimepage.setminute(data.get("minute"));
        datetimepage.setsecond(data.get("second"));
        datetimepage.clickset();

        

        soft.assertEquals(datetimepage.getdate(),   data.get("date"),   "Min Date mismatch!");
        soft.assertEquals(datetimepage.gethour(),   data.get("hour"),   "Min Hour mismatch!");
        soft.assertEquals(datetimepage.getminute(), data.get("minute"), "Min Minute mismatch!");
        soft.assertEquals(datetimepage.getsecond(), data.get("second"), "Min Second mismatch!");

        System.out.println("=== TC06 Set Date and Time Min ===");
        System.out.println("Date   : " + datetimepage.getdate());
        System.out.println("Hour   : " + datetimepage.gethour());
        System.out.println("Minute : " + datetimepage.getminute());
        System.out.println("Second : " + datetimepage.getsecond());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC07 — Set Date and Time Max Values
    // ─────────────────────────────────────────────
    @Test
    public void setdatetime_max() throws IOException {
    	
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setdatetime_max");

        launchandopen();

        datetimepage.setdate(data.get("date"));
        datetimepage.sethour(data.get("hour"));
        datetimepage.setminute(data.get("minute"));
        datetimepage.setsecond(data.get("second"));
        datetimepage.clickset();


        soft.assertEquals(datetimepage.getdate(),   data.get("date"),   "Max Date mismatch!");
        soft.assertEquals(datetimepage.gethour(),   data.get("hour"),   "Max Hour mismatch!");
        soft.assertEquals(datetimepage.getminute(), data.get("minute"), "Max Minute mismatch!");
        soft.assertEquals(datetimepage.getsecond(), data.get("second"), "Max Second mismatch!");

        System.out.println("=== TC07 Set Date and Time Max ===");
        System.out.println("Date   : " + datetimepage.getdate());
        System.out.println("Hour   : " + datetimepage.gethour());
        System.out.println("Minute : " + datetimepage.getminute());
        System.out.println("Second : " + datetimepage.getsecond());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC08 — Sync with PC
    // ─────────────────────────────────────────────
    @Test
    public void syncwithpc() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        datetimepage.clicksyncwithpc();
        datetimepage.clicksave();

        // after sync — fields should not be empty
        soft.assertNotNull(datetimepage.getdate(),   "Date empty after sync!");
        soft.assertNotNull(datetimepage.gethour(),   "Hour empty after sync!");
        soft.assertNotNull(datetimepage.getminute(), "Minute empty after sync!");

        System.out.println("=== TC08 Sync with PC ===");
        System.out.println("Date   : " + datetimepage.getdate());
        System.out.println("Hour   : " + datetimepage.gethour());
        System.out.println("Minute : " + datetimepage.getminute());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC09 — Enable Set via ONVIF
    // ─────────────────────────────────────────────
    @Test
    public void setviaonvif_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setviaonvif_enable");

        launchandopen();
        datetimepage.enablesetviaonvif();
        datetimepage.clicksave();

        soft.assertEquals(
            String.valueOf(datetimepage.issetviaonvifselected()),
            data.get("setviaonvif"), "Set via ONVIF enable mismatch!");

        System.out.println("=== TC09 Enable Set via ONVIF ===");
        System.out.println("Set via ONVIF : " + datetimepage.issetviaonvifselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC10 — Disable Set via ONVIF
    // ─────────────────────────────────────────────
    @Test
    public void setviaonvif_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setviaonvif_disable");

        launchandopen();
        datetimepage.disablesetviaonvif();
        datetimepage.clicksave();


        soft.assertEquals(
            String.valueOf(datetimepage.issetviaonvifselected()),
            data.get("setviaonvif"), "Set via ONVIF disable mismatch!");

        System.out.println("=== TC10 Disable Set via ONVIF ===");
        System.out.println("Set via ONVIF : " + datetimepage.issetviaonvifselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC11 — Enable Set via API
    // ─────────────────────────────────────────────
    @Test
    public void setviaapi_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setviaapi_enable");

        launchandopen();
        datetimepage.enablesetviaapi();
        datetimepage.clicksave();

        soft.assertEquals(
            String.valueOf(datetimepage.issetviaApiselected()),
            data.get("setviaapi"), "Set via API enable mismatch!");

        System.out.println("=== TC11 Enable Set via API ===");
        System.out.println("Set via API : " + datetimepage.issetviaApiselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC12 — Disable Set via API
    // ─────────────────────────────────────────────
    @Test
    public void setviaapi_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setviaapi_disable");

        launchandopen();
        datetimepage.disablesetviaapi();
        datetimepage.clicksave();


        soft.assertEquals(
            String.valueOf(datetimepage.issetviaApiselected()),
            data.get("setviaapi"), "Set via API disable mismatch!");

        System.out.println("=== TC12 Disable Set via API ===");
        System.out.println("Set via API : " + datetimepage.issetviaApiselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC13 — Enable Both Checkboxes
    // ─────────────────────────────────────────────
    @Test
    public void bothcheckbox_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("bothcheckbox_enable");

        launchandopen();
        datetimepage.enablesetviaonvif();
        datetimepage.enablesetviaapi();
        datetimepage.clicksave();

        
        soft.assertEquals(
            String.valueOf(datetimepage.issetviaonvifselected()),
            data.get("setviaonvif"), "ONVIF mismatch!");
        soft.assertEquals(
            String.valueOf(datetimepage.issetviaApiselected()),
            data.get("setviaapi"), "API mismatch!");

        System.out.println("=== TC13 Enable Both Checkboxes ===");
        System.out.println("Set via ONVIF : " + datetimepage.issetviaonvifselected());
        System.out.println("Set via API   : " + datetimepage.issetviaApiselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC14 — Disable Both Checkboxes
    // ─────────────────────────────────────────────
    @Test
    public void bothcheckbox_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("bothcheckbox_disable");

        launchandopen();
        datetimepage.disablesetviaonvif();
        datetimepage.disablesetviaapi();
        datetimepage.clicksave();

        soft.assertEquals(
            String.valueOf(datetimepage.issetviaonvifselected()),
            data.get("setviaonvif"), "ONVIF mismatch!");
        soft.assertEquals(
            String.valueOf(datetimepage.issetviaApiselected()),
            data.get("setviaapi"), "API mismatch!");

        System.out.println("=== TC14 Disable Both Checkboxes ===");
        System.out.println("Set via ONVIF : " + datetimepage.issetviaonvifselected());
        System.out.println("Set via API   : " + datetimepage.issetviaApiselected());

        soft.assertAll();
    }

    // ═══════════════════════════════════════════════
    // SECTION 2 — NTP SETTINGS
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC15 — Default NTP Values
    // ─────────────────────────────────────────────
    @Test
    public void defaultntp() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultntp");

        launchandopen();
        datetimepage.clickdefaultandconfirm();

        soft.assertEquals(
            String.valueOf(datetimepage.issynchronizeselected()),
            data.get("synchronize"), "NTP default mismatch!");

        System.out.println("=== TC15 Default NTP ===");
        System.out.println("NTP Sync : " + datetimepage.issynchronizeselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC16 — Enable NTP — Update 1 Hour
    // ─────────────────────────────────────────────
    @Test
    public void ntp_1hour() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("ntp_1hour");

        launchandopen();
        datetimepage.enablesynchronize();
        datetimepage.setupdatetime(data.get("updatetime"));
        datetimepage.clicksave();


        soft.assertEquals(
            String.valueOf(datetimepage.issynchronizeselected()),
            data.get("synchronize"), "NTP sync mismatch!");
        soft.assertEquals(datetimepage.getupdatetime(),
            data.get("updatetime"), "Update 1hr mismatch!");

        System.out.println("=== TC16 NTP Enable 1 Hour ===");
        System.out.println("NTP Sync    : " + datetimepage.issynchronizeselected());
        System.out.println("Update Time : " + datetimepage.getupdatetime());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC17 — Enable NTP — Update 6 Hours
    // ─────────────────────────────────────────────
    @Test
    public void ntp_6hour() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("ntp_6hour");

        launchandopen();
        datetimepage.enablesynchronize();
        datetimepage.setupdatetime(data.get("updatetime"));
        datetimepage.clicksave();
     
        

        soft.assertEquals(
            String.valueOf(datetimepage.issynchronizeselected()),
            data.get("synchronize"), "NTP sync mismatch!");
        soft.assertEquals(datetimepage.getupdatetime(),
            data.get("updatetime"), "Update 6hr mismatch!");

        System.out.println("=== TC17 NTP Enable 6 Hours ===");
        System.out.println("NTP Sync    : " + datetimepage.issynchronizeselected());
        System.out.println("Update Time : " + datetimepage.getupdatetime());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC18 — Enable NTP — Update 12 Hours
    // ─────────────────────────────────────────────
    @Test
    public void ntp_12hour() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("ntp_12hour");

        launchandopen();
        datetimepage.enablesynchronize();
        datetimepage.setupdatetime(data.get("updatetime"));
        datetimepage.clicksave();
       

        soft.assertEquals(
            String.valueOf(datetimepage.issynchronizeselected()),
            data.get("synchronize"), "NTP sync mismatch!");
        soft.assertEquals(datetimepage.getupdatetime(),
            data.get("updatetime"), "Update 12hr mismatch!");

        System.out.println("=== TC18 NTP Enable 12 Hours ===");
        System.out.println("NTP Sync    : " + datetimepage.issynchronizeselected());
        System.out.println("Update Time : " + datetimepage.getupdatetime());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC19 — Enable NTP — Update 24 Hours
    // ─────────────────────────────────────────────
    @Test
    public void ntp_24hour() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("ntp_24hour");

        launchandopen();
        datetimepage.enablesynchronize();
        datetimepage.setupdatetime(data.get("updatetime"));
        datetimepage.clicksave();
    

        soft.assertEquals(
            String.valueOf(datetimepage.issynchronizeselected()),
            data.get("synchronize"), "NTP sync mismatch!");
        soft.assertEquals(datetimepage.getupdatetime(),
            data.get("updatetime"), "Update 24hr mismatch!");

        System.out.println("=== TC19 NTP Enable 24 Hours ===");
        System.out.println("NTP Sync    : " + datetimepage.issynchronizeselected());
        System.out.println("Update Time : " + datetimepage.getupdatetime());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC20 — Disable NTP
    // ─────────────────────────────────────────────
    @Test
    public void ntp_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("ntp_disable");

        launchandopen();
        datetimepage.disablesynchronize();
        datetimepage.clicksave();


        soft.assertEquals(
            String.valueOf(datetimepage.issynchronizeselected()),
            data.get("synchronize"), "NTP disable mismatch!");

        System.out.println("=== TC20 Disable NTP ===");
        System.out.println("NTP Sync : " + datetimepage.issynchronizeselected());

        soft.assertAll();
    }

    // ═══════════════════════════════════════════════
    // SECTION 3 — DAYLIGHT SAVING TIME (DST)
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC21 — Default DST Values
    // ─────────────────────────────────────────────
    @Test
    public void defaultdst() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultdst");

        launchandopen();
        datetimepage.clickdefaultandconfirm();
   

        soft.assertEquals(
            String.valueOf(datetimepage.isdstselected()),
            data.get("dst"), "DST default mismatch!");

        System.out.println("=== TC21 Default DST ===");
        System.out.println("DST : " + datetimepage.isdstselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC22 — Enable DST Standard Region
    // ─────────────────────────────────────────────
    @Test
    public void dst_standard() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("dst_standard");

        launchandopen();
        datetimepage.enabledst();

        // set forward clock
        datetimepage.setfwdmonth(data.get("fwdmonth"));
        datetimepage.setfwdweek(data.get("fwdweek"));
        datetimepage.setfwdday(data.get("fwdday"));
        datetimepage.setfwdtimehh(data.get("fwdtimehh"));
        datetimepage.setfwdtimemm(data.get("fwdtimemm"));

        // set reverse clock
        datetimepage.setrevmonth(data.get("revmonth"));
        datetimepage.setrevweek(data.get("revweek"));
        datetimepage.setrevday(data.get("revday"));
        datetimepage.setrevtimehh(data.get("revtimehh"));
        datetimepage.setrevtimemm(data.get("revtimemm"));

        datetimepage.clicksave();

        soft.assertEquals(String.valueOf(datetimepage.isdstselected()),  data.get("dst"),      "DST mismatch!");
        soft.assertEquals(datetimepage.getfwdmonth(), data.get("fwdmonth"), "Fwd Month mismatch!");
        soft.assertEquals(datetimepage.getfwdweek(),  data.get("fwdweek"),  "Fwd Week mismatch!");
        soft.assertEquals(datetimepage.getfwdday(),   data.get("fwdday"),   "Fwd Day mismatch!");
        soft.assertEquals(datetimepage.getfwdtimehh(),data.get("fwdtimehh"),"Fwd HH mismatch!");
        soft.assertEquals(datetimepage.getfwdtimemm(),data.get("fwdtimemm"),"Fwd MM mismatch!");
        soft.assertEquals(datetimepage.getrevmonth(), data.get("revmonth"), "Rev Month mismatch!");
        soft.assertEquals(datetimepage.getrevweek(),  data.get("revweek"),  "Rev Week mismatch!");
        soft.assertEquals(datetimepage.getrevday(),   data.get("revday"),   "Rev Day mismatch!");
        soft.assertEquals(datetimepage.getrevtimehh(),data.get("revtimehh"),"Rev HH mismatch!");
        soft.assertEquals(datetimepage.getrevtimemm(),data.get("revtimemm"),"Rev MM mismatch!");

        System.out.println("=== TC22 DST Standard ===");
        System.out.println("DST       : " + datetimepage.isdstselected());
        System.out.println("Fwd Month : " + datetimepage.getfwdmonth());
        System.out.println("Fwd Week  : " + datetimepage.getfwdweek());
        System.out.println("Rev Month : " + datetimepage.getrevmonth());
        System.out.println("Rev Week  : " + datetimepage.getrevweek());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC23 — Enable DST Europe Region
    // ─────────────────────────────────────────────
    @Test
    public void dst_europe() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("dst_europe");

        launchandopen();
        datetimepage.enabledst();

        datetimepage.setfwdmonth(data.get("fwdmonth"));
        datetimepage.setfwdweek(data.get("fwdweek"));
        datetimepage.setfwdday(data.get("fwdday"));
        datetimepage.setfwdtimehh(data.get("fwdtimehh"));
        datetimepage.setfwdtimemm(data.get("fwdtimemm"));
        datetimepage.setrevmonth(data.get("revmonth"));
        datetimepage.setrevweek(data.get("revweek"));
        datetimepage.setrevday(data.get("revday"));
        datetimepage.setrevtimehh(data.get("revtimehh"));
        datetimepage.setrevtimemm(data.get("revtimemm"));

        datetimepage.clicksave();


        soft.assertEquals(String.valueOf(datetimepage.isdstselected()),  data.get("dst"),       "DST mismatch!");
        soft.assertEquals(datetimepage.getfwdmonth(), data.get("fwdmonth"),  "Fwd Month mismatch!");
        soft.assertEquals(datetimepage.getfwdweek(),  data.get("fwdweek"),   "Fwd Week mismatch!");
        soft.assertEquals(datetimepage.getrevmonth(), data.get("revmonth"),  "Rev Month mismatch!");
        soft.assertEquals(datetimepage.getrevweek(),  data.get("revweek"),   "Rev Week mismatch!");

        System.out.println("=== TC23 DST Europe ===");
        System.out.println("DST       : " + datetimepage.isdstselected());
        System.out.println("Fwd Month : " + datetimepage.getfwdmonth());
        System.out.println("Rev Month : " + datetimepage.getrevmonth());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC24 — Enable DST Australia Region
    // ─────────────────────────────────────────────
    @Test
    public void dst_australia() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("dst_australia");

        launchandopen();
        datetimepage.enabledst();

        datetimepage.setfwdmonth(data.get("fwdmonth"));
        datetimepage.setfwdweek(data.get("fwdweek"));
        datetimepage.setfwdday(data.get("fwdday"));
        datetimepage.setfwdtimehh(data.get("fwdtimehh"));
        datetimepage.setfwdtimemm(data.get("fwdtimemm"));
        datetimepage.setrevmonth(data.get("revmonth"));
        datetimepage.setrevweek(data.get("revweek"));
        datetimepage.setrevday(data.get("revday"));
        datetimepage.setrevtimehh(data.get("revtimehh"));
        datetimepage.setrevtimemm(data.get("revtimemm"));

        datetimepage.clicksave();


        soft.assertEquals(String.valueOf(datetimepage.isdstselected()),  data.get("dst"),       "DST mismatch!");
        soft.assertEquals(datetimepage.getfwdmonth(), data.get("fwdmonth"),  "Fwd Month mismatch!");
        soft.assertEquals(datetimepage.getrevmonth(), data.get("revmonth"),  "Rev Month mismatch!");

        System.out.println("=== TC24 DST Australia ===");
        System.out.println("DST       : " + datetimepage.isdstselected());
        System.out.println("Fwd Month : " + datetimepage.getfwdmonth());
        System.out.println("Rev Month : " + datetimepage.getrevmonth());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC25 — DST Min Time Values
    // ─────────────────────────────────────────────
    @Test
    public void dst_mintime() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("dst_mintime");

        launchandopen();
        datetimepage.enabledst();

        datetimepage.setfwdtimehh(data.get("fwdtimehh"));
        datetimepage.setfwdtimemm(data.get("fwdtimemm"));
        datetimepage.setrevtimehh(data.get("revtimehh"));
        datetimepage.setrevtimemm(data.get("revtimemm"));

        datetimepage.clicksave();


        soft.assertEquals(datetimepage.getfwdtimehh(), data.get("fwdtimehh"), "Fwd Min HH mismatch!");
        soft.assertEquals(datetimepage.getfwdtimemm(), data.get("fwdtimemm"), "Fwd Min MM mismatch!");
        soft.assertEquals(datetimepage.getrevtimehh(), data.get("revtimehh"), "Rev Min HH mismatch!");
        soft.assertEquals(datetimepage.getrevtimemm(), data.get("revtimemm"), "Rev Min MM mismatch!");

        System.out.println("=== TC25 DST Min Time ===");
        System.out.println("Fwd HH : " + datetimepage.getfwdtimehh());
        System.out.println("Fwd MM : " + datetimepage.getfwdtimemm());
        System.out.println("Rev HH : " + datetimepage.getrevtimehh());
        System.out.println("Rev MM : " + datetimepage.getrevtimemm());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC26 — DST Max Time Values
    // ─────────────────────────────────────────────
    @Test
    public void dst_maxtime() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("dst_maxtime");

        launchandopen();
        datetimepage.enabledst();

        datetimepage.setfwdmonth(data.get("fwdmonth"));
        datetimepage.setfwdweek(data.get("fwdweek"));
        datetimepage.setfwdday(data.get("fwdday"));
        datetimepage.setfwdtimehh(data.get("fwdtimehh"));
        datetimepage.setfwdtimemm(data.get("fwdtimemm"));
        datetimepage.setrevmonth(data.get("revmonth"));
        datetimepage.setrevweek(data.get("revweek"));
        datetimepage.setrevday(data.get("revday"));
        datetimepage.setrevtimehh(data.get("revtimehh"));
        datetimepage.setrevtimemm(data.get("revtimemm"));

        datetimepage.clicksave();
       
        

        soft.assertEquals(datetimepage.getfwdtimehh(), data.get("fwdtimehh"), "Fwd Max HH mismatch!");
        soft.assertEquals(datetimepage.getfwdtimemm(), data.get("fwdtimemm"), "Fwd Max MM mismatch!");
        soft.assertEquals(datetimepage.getrevtimehh(), data.get("revtimehh"), "Rev Max HH mismatch!");
        soft.assertEquals(datetimepage.getrevtimemm(), data.get("revtimemm"), "Rev Max MM mismatch!");

        System.out.println("=== TC26 DST Max Time ===");
        System.out.println("Fwd HH : " + datetimepage.getfwdtimehh());
        System.out.println("Fwd MM : " + datetimepage.getfwdtimemm());
        System.out.println("Rev HH : " + datetimepage.getrevtimehh());
        System.out.println("Rev MM : " + datetimepage.getrevtimemm());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC27 — Disable DST
    // ─────────────────────────────────────────────
    @Test
    public void dst_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("dst_disable");

        launchandopen();
        datetimepage.disabledst();
        datetimepage.clicksave();


        soft.assertEquals(
            String.valueOf(datetimepage.isdstselected()),
            data.get("dst"), "DST disable mismatch!");

        System.out.println("=== TC27 Disable DST ===");
        System.out.println("DST : " + datetimepage.isdstselected());

        soft.assertAll();
    }
}