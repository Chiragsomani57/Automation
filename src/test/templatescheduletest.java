package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.templateschedulepageobject;
import ipcamera.testcomponent.basetest;

public class templatescheduletest extends basetest {

    templateschedulepageobject tspage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\templateschedule.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate to Template Schedule tab ─
    public void launchandopen() {
        loginpage.logintocamera();
        tspage = new templateschedulepageobject(driver);
        tspage.clickconfiguration();
        tspage.switchtopanelframe();
        tspage.clickimagesetting();
        tspage.clickappearancemenu();
        switchtoconfigframe();
        tspage.clicktemplatescheduletab();
    }


    // ─── assert invalid value not accepted ────────
    public void assertnotaccepted(String actual, String invalid,
                                   String field, SoftAssert soft) {
        soft.assertNotEquals(actual, invalid,
            field + " should NOT accept: " + invalid);
    }

    // ══════════════════════════════════════════════
    // TC01 — Default Values
    // ══════════════════════════════════════════════
    @Test
    public void tc01_defaultvalues() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc01_defaultvalues");

        launchandopen();
        tspage.clicktemplate(1);          // select Basic
        tspage.clickdefaultandconfirm();

        soft.assertEquals(String.valueOf(tspage.isenableselected()),
            data.get("enable"),      "Enable default mismatch!");
        soft.assertEquals(String.valueOf(tspage.isactiveselected()),
            data.get("active"),      "Active default mismatch!");
        soft.assertEquals(tspage.getfromhour(), data.get("fromhour"), "From Hour default mismatch!");
        soft.assertEquals(tspage.getfrommins(), data.get("frommins"), "From Mins default mismatch!");
        soft.assertEquals(tspage.gettohour(),   data.get("tohour"),   "To Hour default mismatch!");
        soft.assertEquals(tspage.gettomins(),   data.get("tomins"),   "To Mins default mismatch!");
        soft.assertEquals(String.valueOf(tspage.isentireweekselected()),
            data.get("entireweek"), "Entire Week default mismatch!");
        soft.assertEquals(String.valueOf(tspage.issunselected()), data.get("sun"), "Sun default mismatch!");
        soft.assertEquals(String.valueOf(tspage.ismonselected()), data.get("mon"), "Mon default mismatch!");
        soft.assertEquals(String.valueOf(tspage.istueselected()), data.get("tue"), "Tue default mismatch!");
        soft.assertEquals(String.valueOf(tspage.iswedselected()), data.get("wed"), "Wed default mismatch!");
        soft.assertEquals(String.valueOf(tspage.isthuselected()), data.get("thu"), "Thu default mismatch!");
        soft.assertEquals(String.valueOf(tspage.isfriselected()), data.get("fri"), "Fri default mismatch!");
        soft.assertEquals(String.valueOf(tspage.issatselected()), data.get("sat"), "Sat default mismatch!");

        System.out.println("=== TC01 Default Values ===");
        System.out.println("Enable      : " + tspage.isenableselected());
        System.out.println("Active      : " + tspage.isactiveselected());
        System.out.println("From        : " + tspage.getfromhour() + ":" + tspage.getfrommins());
        System.out.println("To          : " + tspage.gettohour()   + ":" + tspage.gettomins());
        System.out.println("Entire Week : " + tspage.isentireweekselected());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC02 — Enable Checkbox
    // ══════════════════════════════════════════════
    @Test
    public void tc02_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_enable");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isenableselected()),
            data.get("enable"), "Enable mismatch!");

        System.out.println("=== TC02 Enable === " + tspage.isenableselected());
        soft.assertAll();
    }

    @Test
    public void tc02_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_disable");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isenableselected()),
            data.get("enable"), "Disable mismatch!");

        System.out.println("=== TC02 Disable === " + tspage.isenableselected());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC03 — Active Checkbox
    // ══════════════════════════════════════════════
    @Test
    public void tc03_active_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc03_active_enable");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setactivecheckbox(data.get("active"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isactiveselected()),
            data.get("active"), "Active enable mismatch!");

        System.out.println("=== TC03 Active Enable === " + tspage.isactiveselected());
        soft.assertAll();
    }

    @Test
    public void tc03_active_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc03_active_disable");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setactivecheckbox(data.get("active"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isactiveselected()),
            data.get("active"), "Active disable mismatch!");

        System.out.println("=== TC03 Active Disable === " + tspage.isactiveselected());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC04 — From Time Boundary
    // ══════════════════════════════════════════════
    @Test
    public void tc04_from_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_min");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfromhour(data.get("fromhour"));
        tspage.setfrommins(data.get("frommins"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(tspage.getfromhour(), data.get("fromhour"), "From Hour min mismatch!");
        soft.assertEquals(tspage.getfrommins(), data.get("frommins"), "From Mins min mismatch!");

        System.out.println("=== TC04 From Time Min === "
            + tspage.getfromhour() + ":" + tspage.getfrommins());
        soft.assertAll();
    }

    @Test
    public void tc04_from_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_max");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfromhour(data.get("fromhour"));
        tspage.setfrommins(data.get("frommins"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(tspage.getfromhour(), data.get("fromhour"), "From Hour max mismatch!");
        soft.assertEquals(tspage.getfrommins(), data.get("frommins"), "From Mins max mismatch!");

        System.out.println("=== TC04 From Time Max === "
            + tspage.getfromhour() + ":" + tspage.getfrommins());
        soft.assertAll();
    }

    @Test
    public void tc04_from_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_set");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfromhour(data.get("fromhour"));
        tspage.setfrommins(data.get("frommins"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(tspage.getfromhour(), data.get("fromhour"), "From Hour set mismatch!");
        soft.assertEquals(tspage.getfrommins(), data.get("frommins"), "From Mins set mismatch!");

        System.out.println("=== TC04 From Time Set === "
            + tspage.getfromhour() + ":" + tspage.getfrommins());
        soft.assertAll();
    }

    @Test
    public void tc04_from_hour_neg() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_hour_neg");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfromhour(data.get("fromhour"));

        assertnotaccepted(tspage.getfromhour(), data.get("fromhour"), "From Hour", soft);
        System.out.println("=== TC04 From Hour Negative === " + tspage.getfromhour());
        soft.assertAll();
    }

    @Test
    public void tc04_from_hour_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_hour_above");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfromhour(data.get("fromhour"));

        assertnotaccepted(tspage.getfromhour(), data.get("fromhour"), "From Hour", soft);
        System.out.println("=== TC04 From Hour Above Max === " + tspage.getfromhour());
        soft.assertAll();
    }

    @Test
    public void tc04_from_mins_neg() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_mins_neg");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfrommins(data.get("frommins"));

        assertnotaccepted(tspage.getfrommins(), data.get("frommins"), "From Mins", soft);
        System.out.println("=== TC04 From Mins Negative === " + tspage.getfrommins());
        soft.assertAll();
    }

    @Test
    public void tc04_from_mins_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_mins_above");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfrommins(data.get("frommins"));

        assertnotaccepted(tspage.getfrommins(), data.get("frommins"), "From Mins", soft);
        System.out.println("=== TC04 From Mins Above Max === " + tspage.getfrommins());
        soft.assertAll();
    }

    @Test
    public void tc04_from_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_from_alpha");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setfromhour(data.get("fromhour"));
        tspage.setfrommins(data.get("frommins"));

        assertnotaccepted(tspage.getfromhour(), data.get("fromhour"), "From Hour", soft);
        assertnotaccepted(tspage.getfrommins(), data.get("frommins"), "From Mins", soft);
        System.out.println("=== TC04 From Alpha === "
            + tspage.getfromhour() + ":" + tspage.getfrommins());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC05 — To Time Boundary
    // ══════════════════════════════════════════════
    @Test
    public void tc05_to_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_to_min");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.settohour(data.get("tohour"));
        tspage.settomins(data.get("tomins"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(tspage.gettohour(), data.get("tohour"), "To Hour min mismatch!");
        soft.assertEquals(tspage.gettomins(), data.get("tomins"), "To Mins min mismatch!");

        System.out.println("=== TC05 To Time Min === "
            + tspage.gettohour() + ":" + tspage.gettomins());
        soft.assertAll();
    }

    @Test
    public void tc05_to_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_to_max");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setactivecheckbox(data.get("active"));
        tspage.settohour(data.get("tohour"));
        tspage.settomins(data.get("tomins"));
        tspage.clicksave();

        soft.assertEquals(tspage.gettohour(), data.get("tohour"), "To Hour max mismatch!");
        soft.assertEquals(tspage.gettomins(), data.get("tomins"), "To Mins max mismatch!");

        System.out.println("=== TC05 To Time Max === "
            + tspage.gettohour() + ":" + tspage.gettomins());
        soft.assertAll();
    }

    @Test
    public void tc05_to_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_to_set");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.settohour(data.get("tohour"));
        tspage.settomins(data.get("tomins"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(tspage.gettohour(), data.get("tohour"), "To Hour set mismatch!");
        soft.assertEquals(tspage.gettomins(), data.get("tomins"), "To Mins set mismatch!");

        System.out.println("=== TC05 To Time Set === "
            + tspage.gettohour() + ":" + tspage.gettomins());
        soft.assertAll();
    }

    @Test
    public void tc05_to_hour_neg() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_to_hour_neg");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.settohour(data.get("tohour"));

        assertnotaccepted(tspage.gettohour(), data.get("tohour"), "To Hour", soft);
        System.out.println("=== TC05 To Hour Negative === " + tspage.gettohour());
        soft.assertAll();
    }

    @Test
    public void tc05_to_hour_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_to_hour_above");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.settohour(data.get("tohour"));

        assertnotaccepted(tspage.gettohour(), data.get("tohour"), "To Hour", soft);
        System.out.println("=== TC05 To Hour Above Max === " + tspage.gettohour());
        soft.assertAll();
    }

    @Test
    public void tc05_to_mins_neg() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_to_mins_neg");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.settomins(data.get("tomins"));

        assertnotaccepted(tspage.gettomins(), data.get("tomins"), "To Mins", soft);
        System.out.println("=== TC05 To Mins Negative === " + tspage.gettomins());
        soft.assertAll();
    }

    @Test
    public void tc05_to_mins_above() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_to_mins_above");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.settomins(data.get("tomins"));

        assertnotaccepted(tspage.gettomins(), data.get("tomins"), "To Mins", soft);
        System.out.println("=== TC05 To Mins Above Max === " + tspage.gettomins());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC06 — Entire Week Checkbox
    // ══════════════════════════════════════════════
    @Test
    public void tc06_entireweek_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc06_entireweek_enable");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setentireweek(data.get("entireweek"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isentireweekselected()),
            data.get("entireweek"), "Entire Week enable mismatch!");
        soft.assertEquals(String.valueOf(tspage.issunselected()), data.get("sun"), "Sun mismatch!");
        soft.assertEquals(String.valueOf(tspage.ismonselected()), data.get("mon"), "Mon mismatch!");
        soft.assertEquals(String.valueOf(tspage.istueselected()), data.get("tue"), "Tue mismatch!");
        soft.assertEquals(String.valueOf(tspage.iswedselected()), data.get("wed"), "Wed mismatch!");
        soft.assertEquals(String.valueOf(tspage.isthuselected()), data.get("thu"), "Thu mismatch!");
        soft.assertEquals(String.valueOf(tspage.isfriselected()), data.get("fri"), "Fri mismatch!");
        soft.assertEquals(String.valueOf(tspage.issatselected()), data.get("sat"), "Sat mismatch!");

        System.out.println("=== TC06 Entire Week Enable ===");
        System.out.println("Entire Week : " + tspage.isentireweekselected());
        System.out.println("All Days    : Sun=" + tspage.issunselected()
            + " Mon=" + tspage.ismonselected()
            + " Tue=" + tspage.istueselected()
            + " Wed=" + tspage.iswedselected()
            + " Thu=" + tspage.isthuselected()
            + " Fri=" + tspage.isfriselected()
            + " Sat=" + tspage.issatselected());
        soft.assertAll();
    }

    @Test
    public void tc06_entireweek_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc06_entireweek_disable");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setentireweek(data.get("entireweek"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isentireweekselected()),
            data.get("entireweek"), "Entire Week disable mismatch!");

        System.out.println("=== TC06 Entire Week Disable === " + tspage.isentireweekselected());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC07 — Individual Days
    // ══════════════════════════════════════════════
    @Test
    public void tc07_weekdays() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_weekdays");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setalldays(data.get("sun"), data.get("mon"), data.get("tue"),
                          data.get("wed"), data.get("thu"), data.get("fri"), data.get("sat"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.issunselected()), data.get("sun"), "Sun mismatch!");
        soft.assertEquals(String.valueOf(tspage.ismonselected()), data.get("mon"), "Mon mismatch!");
        soft.assertEquals(String.valueOf(tspage.istueselected()), data.get("tue"), "Tue mismatch!");
        soft.assertEquals(String.valueOf(tspage.iswedselected()), data.get("wed"), "Wed mismatch!");
        soft.assertEquals(String.valueOf(tspage.isthuselected()), data.get("thu"), "Thu mismatch!");
        soft.assertEquals(String.valueOf(tspage.isfriselected()), data.get("fri"), "Fri mismatch!");
        soft.assertEquals(String.valueOf(tspage.issatselected()), data.get("sat"), "Sat mismatch!");

        System.out.println("=== TC07 Weekdays ===");
        System.out.println("Mon=" + tspage.ismonselected() + " Tue=" + tspage.istueselected()
            + " Wed=" + tspage.iswedselected() + " Thu=" + tspage.isthuselected()
            + " Fri=" + tspage.isfriselected());
        soft.assertAll();
    }

    @Test
    public void tc07_weekend() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_weekend");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setalldays(data.get("sun"), data.get("mon"), data.get("tue"),
                          data.get("wed"), data.get("thu"), data.get("fri"), data.get("sat"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.issunselected()), data.get("sun"), "Sun mismatch!");
        soft.assertEquals(String.valueOf(tspage.issatselected()), data.get("sat"), "Sat mismatch!");

        System.out.println("=== TC07 Weekend ===");
        System.out.println("Sun=" + tspage.issunselected() + " Sat=" + tspage.issatselected());
        soft.assertAll();
    }

    @Test
    public void tc07_alldays() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc07_alldays");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setalldays(data.get("sun"), data.get("mon"), data.get("tue"),
                          data.get("wed"), data.get("thu"), data.get("fri"), data.get("sat"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.issunselected()), data.get("sun"), "Sun mismatch!");
        soft.assertEquals(String.valueOf(tspage.ismonselected()), data.get("mon"), "Mon mismatch!");
        soft.assertEquals(String.valueOf(tspage.istueselected()), data.get("tue"), "Tue mismatch!");
        soft.assertEquals(String.valueOf(tspage.iswedselected()), data.get("wed"), "Wed mismatch!");
        soft.assertEquals(String.valueOf(tspage.isthuselected()), data.get("thu"), "Thu mismatch!");
        soft.assertEquals(String.valueOf(tspage.isfriselected()), data.get("fri"), "Fri mismatch!");
        soft.assertEquals(String.valueOf(tspage.issatselected()), data.get("sat"), "Sat mismatch!");

        System.out.println("=== TC07 All Days ===");
        System.out.println("Sun=" + tspage.issunselected() + " Mon=" + tspage.ismonselected()
            + " Tue=" + tspage.istueselected() + " Wed=" + tspage.iswedselected()
            + " Thu=" + tspage.isthuselected() + " Fri=" + tspage.isfriselected()
            + " Sat=" + tspage.issatselected());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC08 — Template Table Click and Verify
    // ══════════════════════════════════════════════
    @Test
    public void tc08_select_basic() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_select_basic");

        launchandopen();
        tspage.clicktemplate(1);

        soft.assertEquals(tspage.gettemplateindex(), data.get("templateindex"), "Template Index Basic mismatch!");
        soft.assertEquals(tspage.gettemplatename(),  data.get("templatename"),  "Template Name Basic mismatch!");

        System.out.println("=== TC08 Select Basic ===");
        System.out.println("Index : " + tspage.gettemplateindex());
        System.out.println("Name  : " + tspage.gettemplatename());
        soft.assertAll();
    }

    @Test
    public void tc08_select_outdoor() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_select_outdoor");

        launchandopen();
        tspage.clicktemplate(2);

        soft.assertEquals(tspage.gettemplateindex(), data.get("templateindex"), "Template Index Outdoor mismatch!");
        soft.assertEquals(tspage.gettemplatename(),  data.get("templatename"),  "Template Name Outdoor mismatch!");

        System.out.println("=== TC08 Select Outdoor ===");
        System.out.println("Index : " + tspage.gettemplateindex());
        System.out.println("Name  : " + tspage.gettemplatename());
        soft.assertAll();
    }

    @Test
    public void tc08_select_indoor() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_select_indoor");

        launchandopen();
        tspage.clicktemplate(3);

        soft.assertEquals(tspage.gettemplateindex(), data.get("templateindex"), "Template Index Indoor mismatch!");
        soft.assertEquals(tspage.gettemplatename(),  data.get("templatename"),  "Template Name Indoor mismatch!");

        System.out.println("=== TC08 Select Indoor ===");
        System.out.println("Index : " + tspage.gettemplateindex());
        System.out.println("Name  : " + tspage.gettemplatename());
        soft.assertAll();
    }

    @Test
    public void tc08_select_day() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_select_day");

        launchandopen();
        tspage.clicktemplate(4);

        soft.assertEquals(tspage.gettemplateindex(), data.get("templateindex"), "Template Index Day mismatch!");
        soft.assertEquals(tspage.gettemplatename(),  data.get("templatename"),  "Template Name Day mismatch!");

        System.out.println("=== TC08 Select Day ===");
        System.out.println("Index : " + tspage.gettemplateindex());
        System.out.println("Name  : " + tspage.gettemplatename());
        soft.assertAll();
    }

    @Test
    public void tc08_select_night() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc08_select_night");

        launchandopen();
        tspage.clicktemplate(5);

        soft.assertEquals(tspage.gettemplateindex(), data.get("templateindex"), "Template Index Night mismatch!");
        soft.assertEquals(tspage.gettemplatename(),  data.get("templatename"),  "Template Name Night mismatch!");

        System.out.println("=== TC08 Select Night ===");
        System.out.println("Index : " + tspage.gettemplateindex());
        System.out.println("Name  : " + tspage.gettemplatename());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC09 — Set All Together
    // ══════════════════════════════════════════════
    @Test
    public void tc09_setall_weekdays() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_setall_weekdays");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setactivecheckbox(data.get("active"));
        tspage.setfromhour(data.get("fromhour"));
        tspage.setfrommins(data.get("frommins"));
        tspage.settohour(data.get("tohour"));
        tspage.settomins(data.get("tomins"));
        tspage.setentireweek(data.get("entireweek"));
        tspage.setalldays(data.get("sun"), data.get("mon"), data.get("tue"),
                          data.get("wed"), data.get("thu"), data.get("fri"), data.get("sat"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isenableselected()), data.get("enable"),      "Enable mismatch!");
        soft.assertEquals(String.valueOf(tspage.isactiveselected()), data.get("active"),      "Active mismatch!");
        soft.assertEquals(tspage.getfromhour(), data.get("fromhour"), "From Hour mismatch!");
        soft.assertEquals(tspage.getfrommins(), data.get("frommins"), "From Mins mismatch!");
        soft.assertEquals(tspage.gettohour(),   data.get("tohour"),   "To Hour mismatch!");
        soft.assertEquals(tspage.gettomins(),   data.get("tomins"),   "To Mins mismatch!");
        soft.assertEquals(String.valueOf(tspage.issunselected()), data.get("sun"), "Sun mismatch!");
        soft.assertEquals(String.valueOf(tspage.ismonselected()), data.get("mon"), "Mon mismatch!");
        soft.assertEquals(String.valueOf(tspage.istueselected()), data.get("tue"), "Tue mismatch!");
        soft.assertEquals(String.valueOf(tspage.iswedselected()), data.get("wed"), "Wed mismatch!");
        soft.assertEquals(String.valueOf(tspage.isthuselected()), data.get("thu"), "Thu mismatch!");
        soft.assertEquals(String.valueOf(tspage.isfriselected()), data.get("fri"), "Fri mismatch!");
        soft.assertEquals(String.valueOf(tspage.issatselected()), data.get("sat"), "Sat mismatch!");

        System.out.println("=== TC09 Set All Weekdays ===");
        System.out.println("Enable : " + tspage.isenableselected());
        System.out.println("Active : " + tspage.isactiveselected());
        System.out.println("From   : " + tspage.getfromhour() + ":" + tspage.getfrommins());
        System.out.println("To     : " + tspage.gettohour()   + ":" + tspage.gettomins());
        soft.assertAll();
    }

    @Test
    public void tc09_setall_weekend() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_setall_weekend");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setactivecheckbox(data.get("active"));
        tspage.setfromhour(data.get("fromhour"));
        tspage.setfrommins(data.get("frommins"));
        tspage.settohour(data.get("tohour"));
        tspage.settomins(data.get("tomins"));
        tspage.setentireweek(data.get("entireweek"));
        tspage.setalldays(data.get("sun"), data.get("mon"), data.get("tue"),
                          data.get("wed"), data.get("thu"), data.get("fri"), data.get("sat"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isenableselected()), data.get("enable"), "Enable mismatch!");
        soft.assertEquals(tspage.getfromhour(), data.get("fromhour"), "From Hour mismatch!");
        soft.assertEquals(tspage.gettohour(),   data.get("tohour"),   "To Hour mismatch!");
        soft.assertEquals(String.valueOf(tspage.issunselected()), data.get("sun"), "Sun mismatch!");
        soft.assertEquals(String.valueOf(tspage.issatselected()), data.get("sat"), "Sat mismatch!");
        soft.assertEquals(String.valueOf(tspage.ismonselected()), data.get("mon"), "Mon mismatch!");

        System.out.println("=== TC09 Set All Weekend ===");
        System.out.println("From : " + tspage.getfromhour() + ":" + tspage.getfrommins());
        System.out.println("To   : " + tspage.gettohour()   + ":" + tspage.gettomins());
        System.out.println("Sun=" + tspage.issunselected() + " Sat=" + tspage.issatselected());
        soft.assertAll();
    }

    @Test
    public void tc09_setall_entireweek() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc09_setall_entireweek");

        launchandopen();
        tspage.clicktemplate(1);
        tspage.setenablecheckbox(data.get("enable"));
        tspage.setactivecheckbox(data.get("active"));
        tspage.setfromhour(data.get("fromhour"));
        tspage.setfrommins(data.get("frommins"));
        tspage.settohour(data.get("tohour"));
        tspage.settomins(data.get("tomins"));
        tspage.setentireweek(data.get("entireweek"));
        tspage.clicksave();
        tspage.clicktemplate(1);

        soft.assertEquals(String.valueOf(tspage.isenableselected()),    data.get("enable"),      "Enable mismatch!");
        soft.assertEquals(String.valueOf(tspage.isactiveselected()),    data.get("active"),      "Active mismatch!");
        soft.assertEquals(String.valueOf(tspage.isentireweekselected()),data.get("entireweek"),  "Entire Week mismatch!");
        soft.assertEquals(tspage.getfromhour(), data.get("fromhour"),   "From Hour mismatch!");
        soft.assertEquals(tspage.getfrommins(), data.get("frommins"),   "From Mins mismatch!");
        soft.assertEquals(tspage.gettohour(),   data.get("tohour"),     "To Hour mismatch!");
        soft.assertEquals(tspage.gettomins(),   data.get("tomins"),     "To Mins mismatch!");

        System.out.println("=== TC09 Set All Entire Week ===");
        System.out.println("Enable      : " + tspage.isenableselected());
        System.out.println("Active      : " + tspage.isactiveselected());
        System.out.println("Entire Week : " + tspage.isentireweekselected());
        System.out.println("From        : " + tspage.getfromhour() + ":" + tspage.getfrommins());
        System.out.println("To          : " + tspage.gettohour()   + ":" + tspage.gettomins());
        soft.assertAll();
    }
}
