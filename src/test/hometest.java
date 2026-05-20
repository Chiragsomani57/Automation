package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.homepageobject;
import ipcamera.testcomponent.basetest;

public class hometest extends basetest {

    homepageobject homepage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\home.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + open home page ───────────────────
    public void launchandopen() throws IOException {
        loginpage.login("admin", "Admin@123");
        homepage = new homepageobject(driver);
        switchtopanelframe();
        homepage.clickapperance();
    }

    // ─── helper: set value and check not equal ────
    public void assertnotaccepted(String actual, String invalid, String field, SoftAssert soft) {
        soft.assertNotEquals(actual, invalid,
            field + " should not accept invalid value: " + invalid);
    }

    // ══════════════════════════════════════════════
    // SECTION 1 — DEFAULT VALUES
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC01 — Verify All Default Values
    // ─────────────────────────────────────────────
    @Test
    public void defaultvalue() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultvalue");

        launchandopen();

        soft.assertEquals(homepage.getbrightness(), data.get("brightness"), "Brightness default mismatch!");
        soft.assertEquals(homepage.getcontrast(),   data.get("contrast"),   "Contrast default mismatch!");
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation default mismatch!");
        soft.assertEquals(homepage.gethue(),        data.get("hue"),        "Hue default mismatch!");

        System.out.println("=== TC01 Default Values ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        System.out.println("Contrast   : " + homepage.getcontrast());
        System.out.println("Saturation : " + homepage.getsaturation());
        System.out.println("Hue        : " + homepage.gethue());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 2 — BRIGHTNESS
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC02 — Brightness Default (50)
    // ─────────────────────────────────────────────
    @Test
    public void brightness_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("brightness_default");

        launchandopen();

        soft.assertEquals(homepage.getbrightness(),
            data.get("brightness"), "Brightness default mismatch!");

        System.out.println("=== TC02 Brightness Default ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC03 — Brightness Min (0)
    // ─────────────────────────────────────────────
    @Test
    public void brightness_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("brightness_min");

        launchandopen();
        homepage.setbrightness(data.get("brightness"));

        soft.assertEquals(homepage.getbrightness(),
            data.get("brightness"), "Brightness min mismatch!");

        System.out.println("=== TC03 Brightness Min ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC04 — Brightness Max (100)
    // ─────────────────────────────────────────────
    @Test
    public void brightness_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("brightness_max");

        launchandopen();
        homepage.setbrightness(data.get("brightness"));

        soft.assertEquals(homepage.getbrightness(),
            data.get("brightness"), "Brightness max mismatch!");

        System.out.println("=== TC04 Brightness Max ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC05 — Brightness Set (75)
    // ─────────────────────────────────────────────
    @Test
    public void brightness_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("brightness_set");

        launchandopen();
        homepage.setbrightness(data.get("brightness"));

        soft.assertEquals(homepage.getbrightness(),
            data.get("brightness"), "Brightness set mismatch!");

        System.out.println("=== TC05 Brightness Set ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC06 — Brightness Negative (-1)
    // ─────────────────────────────────────────────
    @Test
    public void brightness_negative() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        homepage.setbrightness("-1");
        String result = homepage.getbrightness();

        assertnotaccepted(result, "-1", "Brightness", soft);

        System.out.println("=== TC06 Brightness Negative ===");
        System.out.println("Value after set : " + result);
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC07 — Brightness Above Max (101)
    // ─────────────────────────────────────────────
    @Test
    public void brightness_abovemax() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        homepage.setbrightness("101");
        String result = homepage.getbrightness();

        assertnotaccepted(result, "101", "Brightness", soft);

        System.out.println("=== TC07 Brightness Above Max ===");
        System.out.println("Value after set : " + result);
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC08 — Brightness Alphabets
    // ─────────────────────────────────────────────
    @Test
    public void brightness_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        homepage.setbrightness("abc");
        String result = homepage.getbrightness();

        assertnotaccepted(result, "abc", "Brightness", soft);

        System.out.println("=== TC08 Brightness Alpha ===");
        System.out.println("Value after set : " + result);
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC09 — Brightness Special Characters
    // ─────────────────────────────────────────────
    @Test
    public void brightness_special() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        homepage.setbrightness("@#$");
        String result = homepage.getbrightness();

        assertnotaccepted(result, "@#$", "Brightness", soft);

        System.out.println("=== TC09 Brightness Special ===");
        System.out.println("Value after set : " + result);
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC10 — Brightness Decimal
    // ─────────────────────────────────────────────
    @Test
    public void brightness_decimal() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        homepage.setbrightness("5.5");
        String result = homepage.getbrightness();

        assertnotaccepted(result, "5.5", "Brightness", soft);

        System.out.println("=== TC10 Brightness Decimal ===");
        System.out.println("Value after set : " + result);
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC11 — Brightness Empty
    // ─────────────────────────────────────────────
    @Test
    public void brightness_empty() throws IOException {
        SoftAssert soft = new SoftAssert();

        launchandopen();
        homepage.setbrightness("");
        String result = homepage.getbrightness();

        soft.assertNotEquals(result, "", "Brightness should not accept empty value!");

        System.out.println("=== TC11 Brightness Empty ===");
        System.out.println("Value after set : " + result);
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 3 — CONTRAST
    // ══════════════════════════════════════════════

    @Test
    public void contrast_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("contrast_default");

        launchandopen();

        soft.assertEquals(homepage.getcontrast(),
            data.get("contrast"), "Contrast default mismatch!");

        System.out.println("=== TC12 Contrast Default ===");
        System.out.println("Contrast : " + homepage.getcontrast());
        soft.assertAll();
    }

    @Test
    public void contrast_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("contrast_min");

        launchandopen();
        homepage.setcontrast(data.get("contrast"));

        soft.assertEquals(homepage.getcontrast(),
            data.get("contrast"), "Contrast min mismatch!");

        System.out.println("=== TC13 Contrast Min ===");
        System.out.println("Contrast : " + homepage.getcontrast());
        soft.assertAll();
    }

    @Test
    public void contrast_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("contrast_max");

        launchandopen();
        homepage.setcontrast(data.get("contrast"));

        soft.assertEquals(homepage.getcontrast(),
            data.get("contrast"), "Contrast max mismatch!");

        System.out.println("=== TC14 Contrast Max ===");
        System.out.println("Contrast : " + homepage.getcontrast());
        soft.assertAll();
    }

    @Test
    public void contrast_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("contrast_set");

        launchandopen();
        homepage.setcontrast(data.get("contrast"));

        soft.assertEquals(homepage.getcontrast(),
            data.get("contrast"), "Contrast set mismatch!");

        System.out.println("=== TC15 Contrast Set ===");
        System.out.println("Contrast : " + homepage.getcontrast());
        soft.assertAll();
    }

    @Test
    public void contrast_negative() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setcontrast("-1");
        assertnotaccepted(homepage.getcontrast(), "-1", "Contrast", soft);
        System.out.println("=== TC16 Contrast Negative === Value: " + homepage.getcontrast());
        soft.assertAll();
    }

    @Test
    public void contrast_abovemax() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setcontrast("101");
        assertnotaccepted(homepage.getcontrast(), "101", "Contrast", soft);
        System.out.println("=== TC17 Contrast Above Max === Value: " + homepage.getcontrast());
        soft.assertAll();
    }

    @Test
    public void contrast_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setcontrast("abc");
        assertnotaccepted(homepage.getcontrast(), "abc", "Contrast", soft);
        System.out.println("=== TC18 Contrast Alpha === Value: " + homepage.getcontrast());
        soft.assertAll();
    }

    @Test
    public void contrast_empty() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setcontrast("");
        soft.assertNotEquals(homepage.getcontrast(), "", "Contrast should not accept empty!");
        System.out.println("=== TC19 Contrast Empty === Value: " + homepage.getcontrast());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 4 — SATURATION
    // ══════════════════════════════════════════════

    @Test
    public void saturation_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("saturation_default");
        launchandopen();
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation default mismatch!");
        System.out.println("=== TC20 Saturation Default === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    @Test
    public void saturation_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("saturation_min");
        launchandopen();
        homepage.setsaturation(data.get("saturation"));
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation min mismatch!");
        System.out.println("=== TC21 Saturation Min === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    @Test
    public void saturation_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("saturation_max");
        launchandopen();
        homepage.setsaturation(data.get("saturation"));
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation max mismatch!");
        System.out.println("=== TC22 Saturation Max === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    @Test
    public void saturation_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("saturation_set");
        launchandopen();
        homepage.setsaturation(data.get("saturation"));
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation set mismatch!");
        System.out.println("=== TC23 Saturation Set === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    @Test
    public void saturation_negative() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setsaturation("-1");
        assertnotaccepted(homepage.getsaturation(), "-1", "Saturation", soft);
        System.out.println("=== TC24 Saturation Negative === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    @Test
    public void saturation_abovemax() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setsaturation("101");
        assertnotaccepted(homepage.getsaturation(), "101", "Saturation", soft);
        System.out.println("=== TC25 Saturation Above Max === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    @Test
    public void saturation_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setsaturation("abc");
        assertnotaccepted(homepage.getsaturation(), "abc", "Saturation", soft);
        System.out.println("=== TC26 Saturation Alpha === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    @Test
    public void saturation_empty() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.setsaturation("");
        soft.assertNotEquals(homepage.getsaturation(), "", "Saturation should not accept empty!");
        System.out.println("=== TC27 Saturation Empty === Value: " + homepage.getsaturation());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 5 — HUE
    // ══════════════════════════════════════════════

    @Test
    public void hue_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("hue_default");
        launchandopen();
        soft.assertEquals(homepage.gethue(), data.get("hue"), "Hue default mismatch!");
        System.out.println("=== TC28 Hue Default === Value: " + homepage.gethue());
        soft.assertAll();
    }

    @Test
    public void hue_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("hue_min");
        launchandopen();
        homepage.sethue(data.get("hue"));
        soft.assertEquals(homepage.gethue(), data.get("hue"), "Hue min mismatch!");
        System.out.println("=== TC29 Hue Min === Value: " + homepage.gethue());
        soft.assertAll();
    }

    @Test
    public void hue_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("hue_max");
        launchandopen();
        homepage.sethue(data.get("hue"));
        soft.assertEquals(homepage.gethue(), data.get("hue"), "Hue max mismatch!");
        System.out.println("=== TC30 Hue Max === Value: " + homepage.gethue());
        soft.assertAll();
    }

    @Test
    public void hue_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("hue_set");
        launchandopen();
        homepage.sethue(data.get("hue"));
        soft.assertEquals(homepage.gethue(), data.get("hue"), "Hue set mismatch!");
        System.out.println("=== TC31 Hue Set === Value: " + homepage.gethue());
        soft.assertAll();
    }

    @Test
    public void hue_negative() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.sethue("-1");
        assertnotaccepted(homepage.gethue(), "-1", "Hue", soft);
        System.out.println("=== TC32 Hue Negative === Value: " + homepage.gethue());
        soft.assertAll();
    }

    @Test
    public void hue_abovemax() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.sethue("101");
        assertnotaccepted(homepage.gethue(), "101", "Hue", soft);
        System.out.println("=== TC33 Hue Above Max === Value: " + homepage.gethue());
        soft.assertAll();
    }

    @Test
    public void hue_alpha() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.sethue("abc");
        assertnotaccepted(homepage.gethue(), "abc", "Hue", soft);
        System.out.println("=== TC34 Hue Alpha === Value: " + homepage.gethue());
        soft.assertAll();
    }

    @Test
    public void hue_empty() throws IOException {
        SoftAssert soft = new SoftAssert();
        launchandopen();
        homepage.sethue("");
        soft.assertNotEquals(homepage.gethue(), "", "Hue should not accept empty!");
        System.out.println("=== TC35 Hue Empty === Value: " + homepage.gethue());
        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 6 — SET ALL VALUES TOGETHER
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC36 — Set All Normal Values
    // ─────────────────────────────────────────────
    @Test(groups="sanity")
    public void setall_normal() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setall_normal");

        launchandopen();
        homepage.setbrightness(data.get("brightness"));
        homepage.setcontrast(data.get("contrast"));
        homepage.setsaturation(data.get("saturation"));
        homepage.sethue(data.get("hue"));

        soft.assertEquals(homepage.getbrightness(), data.get("brightness"), "Brightness mismatch!");
        soft.assertEquals(homepage.getcontrast(),   data.get("contrast"),   "Contrast mismatch!");
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation mismatch!");
        soft.assertEquals(homepage.gethue(),        data.get("hue"),        "Hue mismatch!");

        System.out.println("=== TC36 Set All Normal ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        System.out.println("Contrast   : " + homepage.getcontrast());
        System.out.println("Saturation : " + homepage.getsaturation());
        System.out.println("Hue        : " + homepage.gethue());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC37 — Set All Min Values
    // ─────────────────────────────────────────────
    @Test
    public void setall_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setall_min");

        launchandopen();
        homepage.setbrightness(data.get("brightness"));
        homepage.setcontrast(data.get("contrast"));
        homepage.setsaturation(data.get("saturation"));
        homepage.sethue(data.get("hue"));

        soft.assertEquals(homepage.getbrightness(), data.get("brightness"), "Brightness min mismatch!");
        soft.assertEquals(homepage.getcontrast(),   data.get("contrast"),   "Contrast min mismatch!");
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation min mismatch!");
        soft.assertEquals(homepage.gethue(),        data.get("hue"),        "Hue min mismatch!");

        System.out.println("=== TC37 Set All Min ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        System.out.println("Contrast   : " + homepage.getcontrast());
        System.out.println("Saturation : " + homepage.getsaturation());
        System.out.println("Hue        : " + homepage.gethue());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC38 — Set All Max Values
    // ─────────────────────────────────────────────
    @Test
    public void setall_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setall_max");

        launchandopen();
        homepage.setbrightness(data.get("brightness"));
        homepage.setcontrast(data.get("contrast"));
        homepage.setsaturation(data.get("saturation"));
        homepage.sethue(data.get("hue"));

        soft.assertEquals(homepage.getbrightness(), data.get("brightness"), "Brightness max mismatch!");
        soft.assertEquals(homepage.getcontrast(),   data.get("contrast"),   "Contrast max mismatch!");
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation max mismatch!");
        soft.assertEquals(homepage.gethue(),        data.get("hue"),        "Hue max mismatch!");

        System.out.println("=== TC38 Set All Max ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        System.out.println("Contrast   : " + homepage.getcontrast());
        System.out.println("Saturation : " + homepage.getsaturation());
        System.out.println("Hue        : " + homepage.gethue());
        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC39 — Reset All to Default (50)
    // ─────────────────────────────────────────────
    @Test
    public void setall_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setall_default");

        launchandopen();
        homepage.setbrightness(data.get("brightness"));
        homepage.setcontrast(data.get("contrast"));
        homepage.setsaturation(data.get("saturation"));
        homepage.sethue(data.get("hue"));

        soft.assertEquals(homepage.getbrightness(), data.get("brightness"), "Brightness reset mismatch!");
        soft.assertEquals(homepage.getcontrast(),   data.get("contrast"),   "Contrast reset mismatch!");
        soft.assertEquals(homepage.getsaturation(), data.get("saturation"), "Saturation reset mismatch!");
        soft.assertEquals(homepage.gethue(),        data.get("hue"),        "Hue reset mismatch!");

        System.out.println("=== TC39 Reset All to Default ===");
        System.out.println("Brightness : " + homepage.getbrightness());
        System.out.println("Contrast   : " + homepage.getcontrast());
        System.out.println("Saturation : " + homepage.getsaturation());
        System.out.println("Hue        : " + homepage.gethue());
        soft.assertAll();
    }
}
