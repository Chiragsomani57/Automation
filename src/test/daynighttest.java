package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.daynightpageobject;
import ipcamera.testcomponent.basetest;

public class daynighttest extends basetest {

    daynightpageobject daynightpage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\daynight.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate to Day and Night tab ────
    public void launchandopen() {
        loginpage.logintocamera();
        daynightpage = new daynightpageobject(driver);
        daynightpage.clickconfiguration();
        daynightpage.switchtopanelframe();
        daynightpage.clickimagesetting();
        daynightpage.clickappearancemenu();
        switchtoconfigframe();
        daynightpage.clickdayandnighttab();

    }



    // ─── assert invalid value not accepted ────────
    public void assertnotaccepted(String actual, String invalid,
                                   String field, SoftAssert soft) {
        soft.assertNotEquals(actual, invalid,
            field + " should NOT accept: " + invalid);
    }

    // ══════════════════════════════════════════════
    // TC01 — Verify Default Values
    // ══════════════════════════════════════════════
    @Test
    public void tc01_defaultvalues() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc01_defaultvalues");

        launchandopen();
        daynightpage.clickdefaultandconfirm();

        soft.assertEquals(daynightpage.gettemplate(),     data.get("template"),       "Template default mismatch!");
        soft.assertEquals(daynightpage.getirledmode(),    data.get("irledmode"),      "IR LED Mode default mismatch!");
        soft.assertEquals(daynightpage.getdaytonight(),   data.get("daytonight"),     "Day to Night default mismatch!");
        soft.assertEquals(daynightpage.getnighttoday(),   data.get("nighttoday"),     "Night to Day default mismatch!");
        soft.assertEquals(daynightpage.getintensitymode(),data.get("intensitymode"),  "Intensity Mode default mismatch!");
        soft.assertEquals(daynightpage.getintensityfar(), data.get("intensityfar"),   "Intensity Far default mismatch!");
        soft.assertEquals(daynightpage.getintensitynear(),data.get("intensitynear"),  "Intensity Near default mismatch!");

        System.out.println("=== TC01 Default Values ===");
        System.out.println("Template       : " + daynightpage.gettemplate());
        System.out.println("IR LED Mode    : " + daynightpage.getirledmode());
        System.out.println("Day to Night   : " + daynightpage.getdaytonight());
        System.out.println("Night to Day   : " + daynightpage.getnighttoday());
        System.out.println("Intensity Mode : " + daynightpage.getintensitymode());
        System.out.println("Intensity Far  : " + daynightpage.getintensityfar());
        System.out.println("Intensity Near : " + daynightpage.getintensitynear());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC02 — Template Options
    // ══════════════════════════════════════════════
    @Test
    public void tc02_template_basic() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_template_basic");
        launchandopen();
        daynightpage.settemplate(data.get("template"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.gettemplate(), data.get("template"), "Template Basic mismatch!");
        System.out.println("=== TC02 Template Basic === " + daynightpage.gettemplate());
        soft.assertAll();
    }

    @Test
    public void tc02_template_indoor() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_template_indoor");
        launchandopen();
        daynightpage.settemplate(data.get("template"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.gettemplate(), data.get("template"), "Template Indoor mismatch!");
        System.out.println("=== TC02 Template Indoor === " + daynightpage.gettemplate());
        soft.assertAll();
    }

    @Test
    public void tc02_template_outdoor() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_template_outdoor");
        launchandopen();
        daynightpage.settemplate(data.get("template"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.gettemplate(), data.get("template"), "Template Outdoor mismatch!");
        System.out.println("=== TC02 Template Outdoor === " + daynightpage.gettemplate());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC03 — IR LED Mode Auto
    // ══════════════════════════════════════════════
    @Test
    public void tc03_irled_auto() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc03_irled_auto");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getirledmode(), data.get("irledmode"), "IR LED Auto mismatch!");
        System.out.println("=== TC03 IR LED Auto === " + daynightpage.getirledmode());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC04 — IR LED Mode Always Off
    // ══════════════════════════════════════════════
    @Test
    public void tc04_irled_alwaysoff() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_irled_alwaysoff");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getirledmode(), data.get("irledmode"), "IR LED Always Off mismatch!");
        System.out.println("=== TC04 IR LED Always Off === " + daynightpage.getirledmode());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC05 — IR LED Mode Always On
    // ══════════════════════════════════════════════
    @Test
    public void tc05_irled_alwayson() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_irled_alwayson");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getirledmode(), data.get("irledmode"), "IR LED Always On mismatch!");
        System.out.println("=== TC05 IR LED Always On === " + daynightpage.getirledmode());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC06 — IR LED Mode Schedule + From/To Time
    // ══════════════════════════════════════════════
    @Test
    public void tc06_irled_schedule() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc06_irled_schedule");

        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setfromhour(data.get("fromhour"));
        daynightpage.setfrommins(data.get("frommins"));
        daynightpage.settohour(data.get("tohour"));
        daynightpage.settomins(data.get("tomins"));
        daynightpage.clicksave();

        soft.assertEquals(daynightpage.getirledmode(), data.get("irledmode"), "IR LED Schedule mismatch!");
        soft.assertEquals(daynightpage.getfromhour(),  data.get("fromhour"),  "From Hour mismatch!");
        soft.assertEquals(daynightpage.getfrommins(),  data.get("frommins"),  "From Mins mismatch!");
        soft.assertEquals(daynightpage.gettohour(),    data.get("tohour"),    "To Hour mismatch!");
        soft.assertEquals(daynightpage.gettomins(),    data.get("tomins"),    "To Mins mismatch!");

        System.out.println("=== TC06 IR LED Schedule ===");
        System.out.println("IR LED Mode : " + daynightpage.getirledmode());
        System.out.println("From        : " + daynightpage.getfromhour() + ":" + daynightpage.getfrommins());
        System.out.println("To          : " + daynightpage.gettohour()   + ":" + daynightpage.gettomins());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC07 — Day to Night Switching Values
    // ══════════════════════════════════════════════
    @Test
    public void tc07_daytonight_020() throws IOException, InterruptedException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_daytonight_020");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setdaytonight(data.get("daytonight"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getdaytonight(), data.get("daytonight"), "Day to Night 0.20 mismatch!");
        System.out.println("=== TC07 Day to Night 0.20 === " + daynightpage.getdaytonight());
        soft.assertAll();
    }

    @Test
    public void tc07_daytonight_050() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_daytonight_050");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setdaytonight(data.get("daytonight"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getdaytonight(), data.get("daytonight"), "Day to Night 0.50 mismatch!");
        System.out.println("=== TC07 Day to Night 0.50 === " + daynightpage.getdaytonight());
        soft.assertAll();
    }

    @Test
    public void tc07_daytonight_100() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_daytonight_100");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setdaytonight(data.get("daytonight"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getdaytonight(), data.get("daytonight"), "Day to Night 1.00 mismatch!");
        System.out.println("=== TC07 Day to Night 1.00 === " + daynightpage.getdaytonight());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC08 — Night to Day Switching Values
    // ══════════════════════════════════════════════
    @Test
    public void tc08_nighttoday_08() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_nighttoday_08");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setnighttoday(data.get("nighttoday"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getnighttoday(), data.get("nighttoday"), "Night to Day 0.8 mismatch!");
        System.out.println("=== TC08 Night to Day 0.8 === " + daynightpage.getnighttoday());
        soft.assertAll();
    }

    @Test
    public void tc08_nighttoday_20() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_nighttoday_20");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setnighttoday(data.get("nighttoday"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getnighttoday(), data.get("nighttoday"), "Night to Day 2.0 mismatch!");
        System.out.println("=== TC08 Night to Day 2.0 === " + daynightpage.getnighttoday());
        soft.assertAll();
    }

    @Test
    public void tc08_nighttoday_50() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_nighttoday_50");
        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setnighttoday(data.get("nighttoday"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getnighttoday(), data.get("nighttoday"), "Night to Day 5.0 mismatch!");
        System.out.println("=== TC08 Night to Day 5.0 === " + daynightpage.getnighttoday());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC09 — Schedule From/To Time Boundary
    // ══════════════════════════════════════════════
    @Test
    public void tc09_schedule_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_schedule_min");

        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setfromhour(data.get("fromhour"));
        daynightpage.setfrommins(data.get("frommins"));
        daynightpage.settohour(data.get("tohour"));
        daynightpage.settomins(data.get("tomins"));
        daynightpage.clicksave();

        soft.assertEquals(daynightpage.getfromhour(), data.get("fromhour"), "From Hour min mismatch!");
        soft.assertEquals(daynightpage.getfrommins(), data.get("frommins"), "From Mins min mismatch!");
        soft.assertEquals(daynightpage.gettohour(),   data.get("tohour"),   "To Hour min mismatch!");
        soft.assertEquals(daynightpage.gettomins(),   data.get("tomins"),   "To Mins min mismatch!");

        System.out.println("=== TC09 Schedule Min Time ===");
        System.out.println("From : " + daynightpage.getfromhour() + ":" + daynightpage.getfrommins());
        System.out.println("To   : " + daynightpage.gettohour()   + ":" + daynightpage.gettomins());
        soft.assertAll();
    }

    @Test
    public void tc09_schedule_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_schedule_max");

        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setfromhour(data.get("fromhour"));
        daynightpage.setfrommins(data.get("frommins"));
        daynightpage.settohour(data.get("tohour"));
        daynightpage.settomins(data.get("tomins"));
        daynightpage.clicksave();

        soft.assertEquals(daynightpage.getfromhour(), data.get("fromhour"), "From Hour max mismatch!");
        soft.assertEquals(daynightpage.getfrommins(), data.get("frommins"), "From Mins max mismatch!");
        soft.assertEquals(daynightpage.gettohour(),   data.get("tohour"),   "To Hour max mismatch!");
        soft.assertEquals(daynightpage.gettomins(),   data.get("tomins"),   "To Mins max mismatch!");

        System.out.println("=== TC09 Schedule Max Time ===");
        System.out.println("From : " + daynightpage.getfromhour() + ":" + daynightpage.getfrommins());
        System.out.println("To   : " + daynightpage.gettohour()   + ":" + daynightpage.gettomins());
        soft.assertAll();
    }

    @Test
    public void tc09_schedule_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_schedule_set");

        launchandopen();
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setfromhour(data.get("fromhour"));
        daynightpage.setfrommins(data.get("frommins"));
        daynightpage.settohour(data.get("tohour"));
        daynightpage.settomins(data.get("tomins"));
        daynightpage.clicksave();

        soft.assertEquals(daynightpage.getfromhour(), data.get("fromhour"), "From Hour set mismatch!");
        soft.assertEquals(daynightpage.getfrommins(), data.get("frommins"), "From Mins set mismatch!");
        soft.assertEquals(daynightpage.gettohour(),   data.get("tohour"),   "To Hour set mismatch!");
        soft.assertEquals(daynightpage.gettomins(),   data.get("tomins"),   "To Mins set mismatch!");

        System.out.println("=== TC09 Schedule Set Time ===");
        System.out.println("From : " + daynightpage.getfromhour() + ":" + daynightpage.getfrommins());
        System.out.println("To   : " + daynightpage.gettohour()   + ":" + daynightpage.gettomins());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC10 — Intensity Mode Auto
    // ══════════════════════════════════════════════
    @Test
    public void tc10_intensity_auto() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc10_intensity_auto");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getintensitymode(), data.get("intensitymode"), "Intensity Auto mismatch!");
        System.out.println("=== TC10 Intensity Auto === " + daynightpage.getintensitymode());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC11 — Intensity Mode Smart
    // ══════════════════════════════════════════════
    @Test
    public void tc11_intensity_smart() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc11_intensity_smart");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.clicksave();
        System.out.println("=== TC11 Intensity Smart === " + daynightpage.getintensitymode());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC12 — Intensity Mode Manual + Far/Near
    // ══════════════════════════════════════════════
    @Test
    public void tc12_intensity_manual() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc12_intensity_manual");

        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        daynightpage.clicksave();

        soft.assertEquals(daynightpage.getintensityfar(),  data.get("intensityfar"),  "Intensity Far mismatch!");
        soft.assertEquals(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near mismatch!");

        System.out.println("=== TC12 Intensity Manual ===");
        System.out.println("Intensity Far  : " + daynightpage.getintensityfar());
        System.out.println("Intensity Near : " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC13 — Intensity Far Boundary Values
    // ══════════════════════════════════════════════
    @Test
    public void tc13_intensityfar_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc13_intensityfar_min");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getintensityfar(), data.get("intensityfar"), "Intensity Far min mismatch!");
        System.out.println("=== TC13 Intensity Far Min === " + daynightpage.getintensityfar());
        soft.assertAll();
    }

    @Test
    public void tc13_intensityfar_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc13_intensityfar_max");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));

        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getintensityfar(), data.get("intensityfar"), "Intensity Far max mismatch!");
        System.out.println("=== TC13 Intensity Far Max === " + daynightpage.getintensityfar());
        soft.assertAll();
    }

    @Test
    public void tc13_intensityfar_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc13_intensityfar_set");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getintensityfar(), data.get("intensityfar"), "Intensity Far set mismatch!");
        System.out.println("=== TC13 Intensity Far Set === " + daynightpage.getintensityfar());
        soft.assertAll();
    }

    @Test
    public void tc13_intensityfar_neg() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc13_intensityfar_neg");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));
        assertnotaccepted(daynightpage.getintensityfar(), data.get("intensityfar"), "Intensity Far", soft);
        System.out.println("=== TC13 Intensity Far Negative === " + daynightpage.getintensityfar());
        soft.assertAll();
    }

    @Test
    public void tc13_intensityfar_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc13_intensityfar_above");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));
        assertnotaccepted(daynightpage.getintensityfar(), data.get("intensityfar"), "Intensity Far", soft);
        System.out.println("=== TC13 Intensity Far Above Max === " + daynightpage.getintensityfar());
        soft.assertAll();
    }

    @Test
    public void tc13_intensityfar_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc13_intensityfar_alpha");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));
        assertnotaccepted(daynightpage.getintensityfar(), data.get("intensityfar"), "Intensity Far", soft);
        System.out.println("=== TC13 Intensity Far Alpha === " + daynightpage.getintensityfar());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC14 — Intensity Near Boundary Values
    // ══════════════════════════════════════════════
    @Test
    public void tc14_intensitynear_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc14_intensitynear_min");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near min mismatch!");
        System.out.println("=== TC14 Intensity Near Min === " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    @Test
    public void tc14_intensitynear_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc14_intensitynear_max");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near max mismatch!");
        System.out.println("=== TC14 Intensity Near Max === " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    @Test
    public void tc14_intensitynear_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc14_intensitynear_set");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        daynightpage.clicksave();
        soft.assertEquals(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near set mismatch!");
        System.out.println("=== TC14 Intensity Near Set === " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    @Test
    public void tc14_intensitynear_neg() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc14_intensitynear_neg");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        assertnotaccepted(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near", soft);
        System.out.println("=== TC14 Intensity Near Negative === " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    @Test
    public void tc14_intensitynear_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc14_intensitynear_above");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        assertnotaccepted(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near", soft);
        System.out.println("=== TC14 Intensity Near Above Max === " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    @Test
    public void tc14_intensitynear_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc14_intensitynear_alpha");
        launchandopen();
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        assertnotaccepted(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near", soft);
        System.out.println("=== TC14 Intensity Near Alpha === " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC15 — Set All Together
    // ══════════════════════════════════════════════
    @Test
    public void tc15_setall_normal() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc15_setall_normal");

        launchandopen();
        daynightpage.settemplate(data.get("template"));
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setdaytonight(data.get("daytonight"));
        daynightpage.setnighttoday(data.get("nighttoday"));
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.setintensityfar(data.get("intensityfar"));
        daynightpage.setintensitynear(data.get("intensitynear"));
        daynightpage.clicksave();

        soft.assertEquals(daynightpage.gettemplate(),      data.get("template"),      "Template mismatch!");
        soft.assertEquals(daynightpage.getirledmode(),     data.get("irledmode"),     "IR LED Mode mismatch!");
        soft.assertEquals(daynightpage.getdaytonight(),    data.get("daytonight"),    "Day to Night mismatch!");
        soft.assertEquals(daynightpage.getnighttoday(),    data.get("nighttoday"),    "Night to Day mismatch!");
        soft.assertEquals(daynightpage.getintensitymode(), data.get("intensitymode"), "Intensity Mode mismatch!");
        soft.assertEquals(daynightpage.getintensityfar(),  data.get("intensityfar"),  "Intensity Far mismatch!");
        soft.assertEquals(daynightpage.getintensitynear(), data.get("intensitynear"), "Intensity Near mismatch!");

        System.out.println("=== TC15 Set All Normal ===");
        System.out.println("Template       : " + daynightpage.gettemplate());
        System.out.println("IR LED Mode    : " + daynightpage.getirledmode());
        System.out.println("Day to Night   : " + daynightpage.getdaytonight());
        System.out.println("Night to Day   : " + daynightpage.getnighttoday());
        System.out.println("Intensity Mode : " + daynightpage.getintensitymode());
        System.out.println("Intensity Far  : " + daynightpage.getintensityfar());
        System.out.println("Intensity Near : " + daynightpage.getintensitynear());
        soft.assertAll();
    }

    @Test
    public void tc15_setall_schedule() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc15_setall_schedule");

        launchandopen();
        daynightpage.settemplate(data.get("template"));
        daynightpage.setirledmode(data.get("irledmode"));
        daynightpage.setfromhour(data.get("fromhour"));
        daynightpage.setfrommins(data.get("frommins"));
        daynightpage.settohour(data.get("tohour"));
        daynightpage.settomins(data.get("tomins"));
        daynightpage.setdaytonight(data.get("daytonight"));
        daynightpage.setnighttoday(data.get("nighttoday"));
        daynightpage.setintensitymode(data.get("intensitymode"));
        daynightpage.clicksave();

        soft.assertEquals(daynightpage.gettemplate(),   data.get("template"),   "Template mismatch!");
        soft.assertEquals(daynightpage.getirledmode(),  data.get("irledmode"),  "IR LED Schedule mismatch!");
        soft.assertEquals(daynightpage.getfromhour(),   data.get("fromhour"),   "From Hour mismatch!");
        soft.assertEquals(daynightpage.getfrommins(),   data.get("frommins"),   "From Mins mismatch!");
        soft.assertEquals(daynightpage.gettohour(),     data.get("tohour"),     "To Hour mismatch!");
        soft.assertEquals(daynightpage.gettomins(),     data.get("tomins"),     "To Mins mismatch!");
        soft.assertEquals(daynightpage.getdaytonight(), data.get("daytonight"), "Day to Night mismatch!");
        soft.assertEquals(daynightpage.getnighttoday(), data.get("nighttoday"), "Night to Day mismatch!");

        System.out.println("=== TC15 Set All Schedule ===");
        System.out.println("IR LED Mode  : " + daynightpage.getirledmode());
        System.out.println("From         : " + daynightpage.getfromhour() + ":" + daynightpage.getfrommins());
        System.out.println("To           : " + daynightpage.gettohour()   + ":" + daynightpage.gettomins());
        System.out.println("Day to Night : " + daynightpage.getdaytonight());
        System.out.println("Night to Day : " + daynightpage.getnighttoday());
        soft.assertAll();
    }
}
