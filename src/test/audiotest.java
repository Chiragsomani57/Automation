package test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hc.core5.http.nio.ssl.BasicServerTlsStrategy;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.audiopageobject;
import ipcamera.testcomponent.basetest;

public class audiotest extends basetest {

    audiopageobject audiopage;

    // ─── fetch test data ──────────────────────────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\audio.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate to audio settings ───────
    public void launchandopen() {                               
        loginpage.logintocamera();
        audiopage = new audiopageobject(driver);
        audiopage.clickconfiguration();
        switchtopanelframe();
        audiopage.clickaudiosettings();
        switchtoconfigframe();
    }

    // ═══════════════════════════════════════════════
    // SECTION 1 — DEFAULT VALUES
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC01 — Verify Default Values
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void defaultaudio() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultaudio");

        launchandopen();
        audiopage.clickdefaultandconfirm();   // Default → Yes
                       // back to panel-frame

        soft.assertEquals(audiopage.getinputencoder(),
            data.get("inputencoder"),  "Input Encoder default mismatch!");
        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"),     "Input Gain default mismatch!");
        soft.assertEquals(String.valueOf(audiopage.isaudiooutselected()),
            data.get("audioout"),      "Audio Out default mismatch!");
        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"),    "Output Gain default mismatch!");

        System.out.println("=== TC01 Default Audio ===");
        System.out.println("Input Encoder : " + audiopage.getinputencoder());
        System.out.println("Input Gain    : " + audiopage.getinputgain());
        System.out.println("Audio Out     : " + audiopage.isaudiooutselected());
        System.out.println("Output Gain   : " + audiopage.getoutputgain());

        soft.assertAll();
    }

    // ═══════════════════════════════════════════════
    // SECTION 2 — INPUT ENCODER
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC02 — Set Input Encoder G.711 u-law
    // ─────────────────────────────────────────────
    @Test
    public void encoder_ulaw() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("encoder_ulaw");

        launchandopen();
        audiopage.setinputencoder(data.get("inputencoder"));
        audiopage.clicksave();
        

        soft.assertEquals(audiopage.getinputencoder(),
            data.get("inputencoder"), "Encoder u-law mismatch!");

        System.out.println("=== TC02 Encoder G.711 u-law ===");
        System.out.println("Input Encoder : " + audiopage.getinputencoder());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC03 — Set Input Encoder G.711 a-law
    // ─────────────────────────────────────────────
    @Test
    public void encoder_alaw() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("encoder_alaw");

        launchandopen();
        audiopage.setinputencoder(data.get("inputencoder"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getinputencoder(),
            data.get("inputencoder"), "Encoder a-law mismatch!");

        System.out.println("=== TC03 Encoder G.711 a-law ===");
        System.out.println("Input Encoder : " + audiopage.getinputencoder());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC04 — Set Input Encoder G.726
    // ─────────────────────────────────────────────
    @Test
    public void encoder_g726() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("encoder_g726");

        launchandopen();
        audiopage.setinputencoder(data.get("inputencoder"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getinputencoder(),
            data.get("inputencoder"), "Encoder G.726 mismatch!");

        System.out.println("=== TC04 Encoder G.726 ===");
        System.out.println("Input Encoder : " + audiopage.getinputencoder());

        soft.assertAll();
    }

    // ═══════════════════════════════════════════════
    // SECTION 3 — INPUT GAIN
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC05 — Input Gain Default Value
    // ─────────────────────────────────────────────
    @Test
    public void inputgain_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("inputgain_default");

        launchandopen();
        audiopage.clickdefaultandconfirm();
      

        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"), "Input Gain default mismatch!");

        System.out.println("=== TC05 Input Gain Default ===");
        System.out.println("Input Gain : " + audiopage.getinputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC06 — Input Gain Min Value (0)
    // ─────────────────────────────────────────────
    @Test
    public void inputgain_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("inputgain_min");

        launchandopen();
        audiopage.setinputgain(data.get("inputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"), "Input Gain min mismatch!");

        System.out.println("=== TC06 Input Gain Min ===");
        System.out.println("Input Gain : " + audiopage.getinputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC07 — Input Gain Max Value (10)
    // ─────────────────────────────────────────────
    @Test
    public void inputgain_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("inputgain_max");

        launchandopen();
        audiopage.setinputgain(data.get("inputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"), "Input Gain max mismatch!");

        System.out.println("=== TC07 Input Gain Max ===");
        System.out.println("Input Gain : " + audiopage.getinputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC08 — Input Gain Set Value (7)
    // ─────────────────────────────────────────────
    @Test
    public void inputgain_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("inputgain_set");

        launchandopen();
        audiopage.setinputgain(data.get("inputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"), "Input Gain set mismatch!");

        System.out.println("=== TC08 Input Gain Set ===");
        System.out.println("Input Gain : " + audiopage.getinputgain());

        soft.assertAll();
    }

    // ═══════════════════════════════════════════════
    // SECTION 4 — AUDIO OUT CHECKBOX
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC09 — Enable Audio Out
    // ─────────────────────────────────────────────
    @Test
    public void audioout_enable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("audioout_enable");

        launchandopen();
        audiopage.enableaudioout();
        audiopage.clicksave();
      

        soft.assertEquals(String.valueOf(audiopage.isaudiooutselected()),
            data.get("audioout"), "Audio Out enable mismatch!");

        System.out.println("=== TC09 Audio Out Enable ===");
        System.out.println("Audio Out : " + audiopage.isaudiooutselected());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC10 — Disable Audio Out
    // ─────────────────────────────────────────────
    @Test
    public void audioout_disable() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("audioout_disable");

        launchandopen();
        audiopage.disableaudioout();
        audiopage.clicksave();
      

        soft.assertEquals(String.valueOf(audiopage.isaudiooutselected()),
            data.get("audioout"), "Audio Out disable mismatch!");

        System.out.println("=== TC10 Audio Out Disable ===");
        System.out.println("Audio Out : " + audiopage.isaudiooutselected());

        soft.assertAll();
    }

    // ═══════════════════════════════════════════════
    // SECTION 5 — OUTPUT GAIN
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC11 — Output Gain Default Value
    // ─────────────────────────────────────────────
    @Test
    public void outputgain_default() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("outputgain_default");

        launchandopen();
        audiopage.clickdefaultandconfirm();
      

        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"), "Output Gain default mismatch!");

        System.out.println("=== TC11 Output Gain Default ===");
        System.out.println("Output Gain : " + audiopage.getoutputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC12 — Output Gain Min Value (0)
    // ─────────────────────────────────────────────
    @Test
    public void outputgain_min() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("outputgain_min");

        launchandopen();
        audiopage.setoutputgain(data.get("outputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"), "Output Gain min mismatch!");

        System.out.println("=== TC12 Output Gain Min ===");
        System.out.println("Output Gain : " + audiopage.getoutputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC13 — Output Gain Max Value (10)
    // ─────────────────────────────────────────────
    @Test
    public void outputgain_max() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("outputgain_max");

        launchandopen();
        audiopage.setoutputgain(data.get("outputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"), "Output Gain max mismatch!");

        System.out.println("=== TC13 Output Gain Max ===");
        System.out.println("Output Gain : " + audiopage.getoutputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC14 — Output Gain Set Value (7)
    // ─────────────────────────────────────────────
    @Test
    public void outputgain_set() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("outputgain_set");

        launchandopen();
        audiopage.setoutputgain(data.get("outputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"), "Output Gain set mismatch!");

        System.out.println("=== TC14 Output Gain Set ===");
        System.out.println("Output Gain : " + audiopage.getoutputgain());

        soft.assertAll();
    }

    // ═══════════════════════════════════════════════
    // SECTION 6 — SET ALL VALUES TOGETHER
    // ═══════════════════════════════════════════════

    // ─────────────────────────────────────────────
    // TC15 — Set All with G.711 u-law
    // ─────────────────────────────────────────────
    @Test
    public void setall_ulaw() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setall_ulaw");

        launchandopen();

        audiopage.setinputencoder(data.get("inputencoder"));
        audiopage.setinputgain(data.get("inputgain"));
        audiopage.enableaudioout();
        audiopage.setoutputgain(data.get("outputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getinputencoder(),
            data.get("inputencoder"),  "Encoder mismatch!");
        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"),     "Input Gain mismatch!");
        soft.assertEquals(String.valueOf(audiopage.isaudiooutselected()),
            data.get("audioout"),      "Audio Out mismatch!");
        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"),    "Output Gain mismatch!");

        System.out.println("=== TC15 Set All u-law ===");
        System.out.println("Input Encoder : " + audiopage.getinputencoder());
        System.out.println("Input Gain    : " + audiopage.getinputgain());
        System.out.println("Audio Out     : " + audiopage.isaudiooutselected());
        System.out.println("Output Gain   : " + audiopage.getoutputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC16 — Set All with G.711 a-law
    // ─────────────────────────────────────────────
    @Test(groups = "sanity")
    public void setall_alaw() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setall_alaw");

        launchandopen();

        audiopage.setinputencoder(data.get("inputencoder"));
        audiopage.setinputgain(data.get("inputgain"));
        audiopage.enableaudioout();
        audiopage.setoutputgain(data.get("outputgain"));
        audiopage.clicksave();
      

        soft.assertEquals(audiopage.getinputencoder(),
            data.get("inputencoder"),  "Encoder mismatch!");
        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"),     "Input Gain mismatch!");
        soft.assertEquals(String.valueOf(audiopage.isaudiooutselected()),
            data.get("audioout"),      "Audio Out mismatch!");
        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"),    "Output Gain mismatch!");

        System.out.println("=== TC16 Set All a-law ===");
        System.out.println("Input Encoder : " + audiopage.getinputencoder());
        System.out.println("Input Gain    : " + audiopage.getinputgain());
        System.out.println("Audio Out     : " + audiopage.isaudiooutselected());
        System.out.println("Output Gain   : " + audiopage.getoutputgain());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC17 — Set All with G.726 + Audio Out Disabled
    // ─────────────────────────────────────────────
    @Test
    public void setall_g726() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setall_g726");

        launchandopen();

        audiopage.setinputencoder(data.get("inputencoder"));
        audiopage.setinputgain(data.get("inputgain"));
        audiopage.disableaudioout();
        audiopage.setoutputgain(data.get("outputgain"));
        audiopage.clicksave();

        soft.assertEquals(audiopage.getinputencoder(),
            data.get("inputencoder"),  "Encoder mismatch!");
        soft.assertEquals(audiopage.getinputgain(),
            data.get("inputgain"),     "Input Gain mismatch!");
        soft.assertEquals(String.valueOf(audiopage.isaudiooutselected()),
            data.get("audioout"),      "Audio Out mismatch!");
        soft.assertEquals(audiopage.getoutputgain(),
            data.get("outputgain"),    "Output Gain mismatch!");

        System.out.println("=== TC17 Set All G.726 ===");
        System.out.println("Input Encoder : " + audiopage.getinputencoder());
        System.out.println("Input Gain    : " + audiopage.getinputgain());
        System.out.println("Audio Out     : " + audiopage.isaudiooutselected());
        System.out.println("Output Gain   : " + audiopage.getoutputgain());

        soft.assertAll();
    }
    
    @Test
    public void negtest_inputgain_empty() throws IOException
    {
    	HashMap<String, String> data = testdata("negtest_inputgain_empty");

        launchandopen();
        audiopage.setinputencoder(data.get("inputgain"));
        audiopage.clicksave();
        Assert.assertEquals(audiopage.getinputgain(), data.get("expected"));
    	
    }
    
    @Test
    public void negtest_inputgain_0() throws IOException
    {
    	HashMap<String, String> data = testdata("negtest_inputgain_0");

        launchandopen();
        audiopage.setinputencoder(data.get("inputgain"));
        audiopage.clicksave();
        Assert.assertEquals(audiopage.getinputgain(), data.get("expected"));
    	
    }
    
    @Test
    public void negtest_inputgain_11() throws IOException
    {
    	HashMap<String, String> data = testdata("negtest_inputgain_11");

        launchandopen();
        audiopage.setinputencoder(data.get("inputgain"));
        audiopage.clicksave();
        Assert.assertEquals(audiopage.getinputgain(), data.get("expected"));
    	
    }
    
    
    @Test
    public void negtest_outputgain_empty() throws IOException
    {
    	HashMap<String, String> data = testdata("negtest_outputgain_empty");

        launchandopen();
        audiopage.setinputencoder(data.get("outputgain"));
        audiopage.clicksave();
        Assert.assertEquals(audiopage.getoutputgain(), data.get("expected"));
    	
    }
    
    @Test
    public void negtest_outputgain_0() throws IOException
    {
    	HashMap<String, String> data = testdata("negtest_outputgain_0");

        launchandopen();
        audiopage.setinputencoder(data.get("outputgain"));
        audiopage.clicksave();
        Assert.assertEquals(audiopage.getoutputgain(), data.get("expected"));
    	
    }
    
    @Test
    public void negtest_outputgain_11() throws IOException
    {
    	HashMap<String, String> data = testdata("negtest_outputgain_11");

        launchandopen();
        audiopage.setinputencoder(data.get("outputgain"));
        audiopage.clicksave();
        Assert.assertEquals(audiopage.getoutputgain(), data.get("expected"));

    }

    @Test(groups="sanity")
    public void cancel_all() throws IOException {
        SoftAssert soft=new SoftAssert();
        HashMap<String,String> data=testdata("cancel_all");
        HashMap<String,String> data1=testdata("defaultaudio");
        launchandopen();
        audiopage.clickdefaultandconfirm();
        audiopage.setinputencoder(data.get("inputencoder"));
        audiopage.setinputgain(data.get("inputgain"));
        audiopage.enableaudioout();
        audiopage.setoutputgain(data.get("outputgain"));

        audiopage.clickcancel();
        soft.assertEquals(audiopage.getinputencoder(),data1.get("inputencoder"));
        soft.assertEquals(audiopage.getinputgain(),data1.get("inputgain"));
        soft.assertEquals(String.valueOf(audiopage.isaudiooutselected()),data1.get("audioout"));
        soft.assertEquals(audiopage.getoutputgain(),data1.get("outputgain"));

        soft.assertAll();




    }
  

    
    
    
}
