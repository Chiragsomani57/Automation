package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.usermanagementpageobject;
import ipcamera.testcomponent.basetest;

public class usermanagementtest extends basetest {

    usermanagementpageobject userpage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\usermanagement.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate to user accounts ────────
    public void launchandopen() {
        loginpage.logintocamera();
        userpage = new usermanagementpageobject(driver);
        userpage.clickconfiguration();
        userpage.switchtopanelframe();
        userpage.clickuseraccounttab();
        userpage.clickuseracclink();
        userpage.switchtoconfigframe();

    }


    // ══════════════════════════════════════════════
    // TC01 — Add User Index 1 (first available slot)
    // ══════════════════════════════════════════════
    @Test(groups = "sanity")
    public void tc01_adduser_index1() throws IOException, InterruptedException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc01_adduser_index1");

        launchandopen();

        // ─── click first available empty slot ─────
        userpage.clickuserrow(4);   // row 4 — first empty after admin/operator/viewer

        // ─── fill user details ────────────────────
        userpage.enableuser();
        userpage.setusername(data.get("username"));
        userpage.setusertype(data.get("usertype"));
        userpage.clicksave();


        // ─── verify user added in table ───────────
        soft.assertEquals(userpage.getusernamebyrow(4),
            data.get("username"), "User Name mismatch in table!");
        soft.assertEquals(userpage.getusertypebyrow(4).toLowerCase(),
            data.get("usertype"), "User Type mismatch in table!");

        System.out.println("=== TC01 Add User Index 1 ===");
        System.out.println("Username  : " + userpage.getusernamebyrow(4));
        System.out.println("User Type : " + userpage.getusertypebyrow(4));

        soft.assertAll();

        // ─── verify login with new user ───────────
        driver.get(loginpage.getindexurl());
        loginpage.login(data.get("username"), data.get("password"));
        loginpage.setnewpaasword(data.get("newpassword"));
        loginpage.setconfimpassword(data.get("confirmpaasword"));
        loginpage.clickcreatepassword();
        loginpage.login(data.get("username"),data.get("newpassword"));

        soft.assertTrue(driver.getCurrentUrl().contains("main.html"),
            "New user should be able to login!");

        System.out.println("Login URL : " + driver.getCurrentUrl());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC02 — Add User Index 32 (max/boundary slot)
    // ══════════════════════════════════════════════
    @Test(groups = "sanity")
    public void tc02_adduser_index32() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc02_adduser_index32");

        launchandopen();

        // ─── click row 32 — max user boundary ─────
        userpage.clickuserrow(32);

        // ─── fill user details ────────────────────
        userpage.enableuser();
        userpage.setusername(data.get("username"));
        userpage.setusertype(data.get("usertype"));
        userpage.clicksave();



        // ─── verify login with new user ───────────
        driver.get(loginpage.getindexurl());
        loginpage.login(data.get("username"), data.get("password"));
        loginpage.setnewpaasword(data.get("newpassword"));
        loginpage.setconfimpassword(data.get("confirmpaasword"));
        loginpage.clickcreatepassword();
        loginpage.login(data.get("username"),data.get("newpassword"));

        soft.assertTrue(driver.getCurrentUrl().contains("main.html"),
                "New user should be able to login!");

        System.out.println("Login URL : " + driver.getCurrentUrl());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC03 — Delete User
    // ══════════════════════════════════════════════
    @Test(dependsOnMethods = "tc01_adduser_index1")
    public void tc03_deleteuser() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc03_deleteuser");

        launchandopen();

        // ─── click the user to delete ─────────────
        userpage.clickuserrow(4);


        // ─── delete + confirm ─────────────────────
        userpage.clickdeleteandconfirm();


        // ─── verify user removed from table ───────
        String usernameafterdelete = userpage.getusernamebyrow(4);
        soft.assertNotEquals(usernameafterdelete, data.get("username"),
            "User should be deleted from table!");

        System.out.println("=== TC03 Delete User ===");
        System.out.println("Username before delete : " + data.get("username"));
        System.out.println("Username after delete  : " + usernameafterdelete);

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC04 — User Account Default
    // ══════════════════════════════════════════════
    @Test(dependsOnMethods ="tc02_adduser_index32" )
    public void tc04_useraccount_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc04_useraccount_default");

        launchandopen();

        // ─── click admin user ─────────────────────
        userpage.clickuserrow(32);

        // ─── click default + confirm ──────────────
        userpage.clickdefaultandconfirm();
        userpage.clickuserrow(32);

        // ─── verify default values ────────────────
        soft.assertEquals(String.valueOf(userpage.isuserenabled()),
            data.get("enable"), "Enable default mismatch!");

        System.out.println("=== TC04 User Account Default ===");
        System.out.println("Enable    : " + userpage.isuserenabled());
        System.out.println("Username  : " + userpage.getusername());
        System.out.println("User Type : " + userpage.getusertype());

        soft.assertAll();
    }

    // ══════════════════════════════════════════════
    // TC05 — Global Policy Default
    // ══════════════════════════════════════════════
    @Test(groups = "sanity")
    public void tc05_globalpolicy_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("tc05_globalpolicy_default");

        // ─── navigate to global policy ────────────
        loginpage.logintocamera();
        userpage = new usermanagementpageobject(driver);
        userpage.clickconfiguration();
        userpage.switchtopanelframe();
        userpage.clickuseraccounttab();
        userpage.clickglobalpolicylink();
        userpage.switchtoconfigframe();

        // ─── click default + confirm ──────────────
        userpage.clickdefaultandconfirm();

        // ─── verify default values ────────────────
        soft.assertEquals(userpage.getminchars(),
            data.get("minchars"), "Min Chars default mismatch!");
        soft.assertEquals(userpage.getpasswordstrength(),
            data.get("passwordstrength"), "Password Strength default mismatch!");
        soft.assertEquals(String.valueOf(userpage.ispasswordvalidityenabled()),
            data.get("setpasswordvalidity"), "Password Validity default mismatch!");
        soft.assertEquals(userpage.getresettimer(),
            data.get("resettimer"), "Reset Timer default mismatch!");
        soft.assertEquals(String.valueOf(userpage.islockafterattemptsenabledabled()),
            data.get("lockafterattempts"), "Lock After Attempts default mismatch!");
        soft.assertEquals(userpage.getmaxattempts(),
            data.get("maxattempts"), "Max Attempts default mismatch!");
        soft.assertEquals(userpage.getautounlock(),
            data.get("autounlock"), "Auto Unlock default mismatch!");

        System.out.println("=== TC05 Global Policy Default ===");
        System.out.println("Min Chars         : " + userpage.getminchars());
        System.out.println("Password Strength : " + userpage.getpasswordstrength());
        System.out.println("Password Validity : " + userpage.ispasswordvalidityenabled());
        System.out.println("Reset Timer       : " + userpage.getresettimer());
        System.out.println("Lock After        : " + userpage.islockafterattemptsenabledabled());
        System.out.println("Max Attempts      : " + userpage.getmaxattempts());
        System.out.println("Auto Unlock       : " + userpage.getautounlock());

        soft.assertAll();

    }
}
