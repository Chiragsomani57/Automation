package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.testcomponent.basetest;

public class logintest extends basetest {

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\login.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── open login page ──────────────────────────
    @SuppressWarnings("null")
	public void launchandopen() throws IOException {
        driver.get(loginpage.getindexurl());
    }

    // ══════════════════════════════════════════════
    // SECTION 1 — POSITIVE TEST CASES
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC01 — Valid Login
    // ─────────────────────────────────────────────
    @Test
    public void login_valid() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("login_valid");

        launchandopen();
        loginpage.login(data.get("username"), data.get("password"));

        // after valid login — URL should change to main.html
        soft.assertTrue(
            driver.getCurrentUrl().contains("main.html"),
            "Valid login failed — URL did not change to main.html!");

        System.out.println("=== TC01 Valid Login ===");
        System.out.println("Username : " + data.get("username"));
        System.out.println("URL      : " + driver.getCurrentUrl());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 2 — NEGATIVE : WRONG CREDENTIALS
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC02 — Wrong Password
    // ─────────────────────────────────────────────
    @Test
    public void login_wrongpassword() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("login_wrongpassword");

        launchandopen();
        loginpage.login(data.get("username"), data.get("password"));

        soft.assertEquals(loginpage.geterrormsg(),
            data.get("errormessage"), "Wrong password error message mismatch!");

        System.out.println("=== TC02 Wrong Password ===");
        System.out.println("Error Msg : " + loginpage.geterrormsg());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC03 — Wrong Username
    // ─────────────────────────────────────────────
    @Test
    public void login_wrongusername() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("login_wrongusername");

        launchandopen();
        loginpage.login(data.get("username"), data.get("password"));

        soft.assertEquals(loginpage.geterrormsg(),
            data.get("errormessage"), "Wrong username error message mismatch!");

        System.out.println("=== TC03 Wrong Username ===");
        System.out.println("Error Msg : " + loginpage.geterrormsg());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC04 — Wrong Both Username and Password
    // ─────────────────────────────────────────────
    @Test
    public void login_wrongboth() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("login_wrongboth");

        launchandopen();
        loginpage.login(data.get("username"), data.get("password"));

        soft.assertEquals(loginpage.geterrormsg(),
            data.get("errormessage"), "Wrong both error message mismatch!");

        System.out.println("=== TC04 Wrong Both ===");
        System.out.println("Error Msg : " + loginpage.geterrormsg());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // SECTION 3 — NEGATIVE : EMPTY FIELDS
    // ══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC05 — Empty Username
    // ─────────────────────────────────────────────
    @Test
    public void login_emptyusername() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("login_emptyusername");

        launchandopen();
        loginpage.login(data.get("username"), data.get("password"));

        soft.assertEquals(loginpage.geterrormsg(),
            data.get("errormessage"), "Empty username error message mismatch!");

        System.out.println("=== TC05 Empty Username ===");
        System.out.println("Error Msg : " + loginpage.geterrormsg());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC06 — Empty Password
    // ─────────────────────────────────────────────
    @Test
    public void login_emptypassword() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("login_emptypassword");

        launchandopen();
        loginpage.login(data.get("username"), data.get("password"));

        soft.assertEquals(loginpage.geterrormsg(),
            data.get("errormessage"), "Empty password error message mismatch!");

        System.out.println("=== TC06 Empty Password ===");
        System.out.println("Error Msg : " + loginpage.geterrormsg());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC07 — Both Empty
    // ─────────────────────────────────────────────
    @Test
    public void login_bothempty() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("login_bothempty");

        launchandopen();
        loginpage.login(data.get("username"), data.get("password"));

        soft.assertEquals(loginpage.geterrormsg(),
            data.get("errormessage"), "Both empty error message mismatch!");

        System.out.println("=== TC07 Both Empty ===");
        System.out.println("Error Msg : " + loginpage.geterrormsg());

        soft.assertAll();
    }
    
}


