package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ipcamera.pageobject.streamprofileobject;
import ipcamera.testcomponent.basetest;

public class streamprofiletest extends basetest {

    streamprofileobject streampage;

    // ─── fetch data by testcase + cameratype ──────
    public HashMap<String, String> testdata(String testcasename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\data\\streamprofile.csv";
        return getcsvdata(path, testcasename, getcameratype());
    }

    // ─── login + navigate + switch to panel-frame ─
    public void launchandopen(int profilenumber) {
        loginpage.logintocamera();
        streampage = new streamprofileobject(driver);
        streampage.clickconfiguartion();
        switchtopanelframe();
        streampage.clickstreamprofie();
        switchtoconfigframe();
        streampage.clickprofile(profilenumber);
    }

    // ─────────────────────────────────────────────
    // TC01 - Default Button Profile 1
    // ─────────────────────────────────────────────
    @Test
    public void defaultvalueprofile1() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultvalueprofile1");

        launchandopen(1);
        streampage.clickdefaultandconfirm();  // Default → config-frame → Yes

        soft.assertEquals(streampage.getprofileno(),    data.get("profileno"),    "Profile No mismatch!");
        soft.assertEquals(streampage.getname(),         data.get("name"),         "Name mismatch!");
        soft.assertEquals(streampage.getcodec(),        data.get("codec"),        "Codec mismatch!");
        soft.assertEquals(streampage.getresolution(),   data.get("resolution"),   "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),      data.get("bitrate"),      "Bitrate mismatch!");
        soft.assertEquals(streampage.getimagequality(), data.get("imagequality"), "Image Quality mismatch!");
        soft.assertEquals(streampage.getfps(),          data.get("fps"),          "FPS mismatch!");
        soft.assertEquals(streampage.getgop(),          data.get("gop"),          "GOP mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");

        System.out.println("=== TC01 Default Button Profile 1 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Profile No   : " + streampage.getprofileno());
        System.out.println("Name         : " + streampage.getname());
        System.out.println("Codec        : " + streampage.getcodec());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());
        System.out.println("GOP          : " + streampage.getgop());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC02 - Default Button Profile 2
    // ─────────────────────────────────────────────
    @Test
    public void defaultvalueprofile2() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultvalueprofile2");

        launchandopen(2);
        streampage.clickdefaultandconfirm();


        soft.assertEquals(streampage.getprofileno(),    data.get("profileno"),    "Profile No mismatch!");
        soft.assertEquals(streampage.getname(),         data.get("name"),         "Name mismatch!");
        soft.assertEquals(streampage.getcodec(),        data.get("codec"),        "Codec mismatch!");
        soft.assertEquals(streampage.getresolution(),   data.get("resolution"),   "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),      data.get("bitrate"),      "Bitrate mismatch!");
        soft.assertEquals(streampage.getimagequality(), data.get("imagequality"), "Image Quality mismatch!");
        soft.assertEquals(streampage.getfps(),          data.get("fps"),          "FPS mismatch!");
        soft.assertEquals(streampage.getgop(),          data.get("gop"),          "GOP mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");

        System.out.println("=== TC02 Default Button Profile 2 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Profile No   : " + streampage.getprofileno());
        System.out.println("Name         : " + streampage.getname());
        System.out.println("Codec        : " + streampage.getcodec());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());
        System.out.println("GOP          : " + streampage.getgop());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC03 - Default Button Profile 3
    // ─────────────────────────────────────────────
    @Test
    public void defaultvalueprofile3() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultvalueprofile3");

        launchandopen(3);
        streampage.clickdefaultandconfirm();


        soft.assertEquals(streampage.getprofileno(),  data.get("profileno"),  "Profile No mismatch!");
        soft.assertEquals(streampage.getname(),       data.get("name"),       "Name mismatch!");
        soft.assertEquals(streampage.getresolution(), data.get("resolution"), "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),    data.get("bitrate"),    "Bitrate mismatch!");
        soft.assertEquals(streampage.getfps(),        data.get("fps"),        "FPS mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");

        System.out.println("=== TC03 Default Button Profile 3 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Profile No   : " + streampage.getprofileno());
        System.out.println("Name         : " + streampage.getname());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC04 - Default Button Profile 4
    // ─────────────────────────────────────────────
    @Test
    public void defaultvalueprofile4() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultvalueprofile4");

        launchandopen(4);
        streampage.clickdefaultandconfirm();
    

        soft.assertEquals(streampage.getprofileno(),  data.get("profileno"),  "Profile No mismatch!");
        soft.assertEquals(streampage.getname(),       data.get("name"),       "Name mismatch!");
        soft.assertEquals(streampage.getcodec(),      data.get("codec"),      "Codec mismatch!");
        soft.assertEquals(streampage.getresolution(), data.get("resolution"), "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),    data.get("bitrate"),    "Bitrate mismatch!");
        soft.assertEquals(streampage.getfps(),        data.get("fps"),        "FPS mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");
        soft.assertEquals(String.valueOf(streampage.isroiselected()),   data.get("roi"),   "ROI mismatch!");

        System.out.println("=== TC04 Default Button Profile 4 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Profile No   : " + streampage.getprofileno());
        System.out.println("Name         : " + streampage.getname());
        System.out.println("Codec        : " + streampage.getcodec());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC05 - Set Values + Save Profile 1
    // ─────────────────────────────────────────────
    @Test
    public void setandsaveprofile1() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setvalueprofile1");

        launchandopen(1);

        // set values
        streampage.setcodec(data.get("codec"));
        streampage.setresolution(data.get("resolution"));
        streampage.setbitrate(data.get("bitrate"));
        streampage.setimagequality(data.get("imagequality"));
        streampage.selectCBR();
        streampage.setfps(data.get("fps"));
        streampage.setgop(data.get("gop"));
        streampage.disableaudio();

        // save
        streampage.clicksave();

        // verify saved values
        soft.assertEquals(streampage.getcodec(),        data.get("codec"),        "Codec mismatch!");
        soft.assertEquals(streampage.getresolution(),   data.get("resolution"),   "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),      data.get("bitrate"),      "Bitrate mismatch!");
        soft.assertEquals(streampage.getimagequality(), data.get("imagequality"), "Image Quality mismatch!");
        soft.assertTrue(streampage.isCBRselected(),     "CBR not selected!");
        soft.assertEquals(streampage.getfps(),          data.get("fps"),          "FPS mismatch!");
        soft.assertEquals(streampage.getgop(),          data.get("gop"),          "GOP mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");

        System.out.println("=== TC05 Set and Save Profile 1 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Codec        : " + streampage.getcodec());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());
        System.out.println("GOP          : " + streampage.getgop());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC06 - Set Values + Save Profile 2
    // ─────────────────────────────────────────────
    @Test
    public void setandsaveprofile2() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setvalueprofile2");

        launchandopen(2);

        streampage.setcodec(data.get("codec"));
        streampage.setresolution(data.get("resolution"));
        streampage.setbitrate(data.get("bitrate"));
        streampage.setimagequality(data.get("imagequality"));
        streampage.selectCBR();
        streampage.setfps(data.get("fps"));
        streampage.setgop(data.get("gop"));
        streampage.disableaudio();

        streampage.clicksave();

        soft.assertEquals(streampage.getcodec(),        data.get("codec"),        "Codec mismatch!");
        soft.assertEquals(streampage.getresolution(),   data.get("resolution"),   "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),      data.get("bitrate"),      "Bitrate mismatch!");
        soft.assertEquals(streampage.getimagequality(), data.get("imagequality"), "Image Quality mismatch!");
        soft.assertTrue(streampage.isCBRselected(),     "CBR not selected!");
        soft.assertEquals(streampage.getfps(),          data.get("fps"),          "FPS mismatch!");
        soft.assertEquals(streampage.getgop(),          data.get("gop"),          "GOP mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");

        System.out.println("=== TC06 Set and Save Profile 2 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Codec        : " + streampage.getcodec());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());
        System.out.println("GOP          : " + streampage.getgop());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC07 - Set Values + Save Profile 3
    // ─────────────────────────────────────────────
    @Test
    public void setandsaveprofile3() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setvalueprofile3");

        launchandopen(3);

        streampage.setresolution(data.get("resolution"));
        streampage.setbitrate(data.get("bitrate"));
        streampage.setfps(data.get("fps"));
        streampage.disableaudio();

        streampage.clicksave();
      

        soft.assertEquals(streampage.getresolution(), data.get("resolution"), "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),    data.get("bitrate"),    "Bitrate mismatch!");
        soft.assertEquals(streampage.getfps(),        data.get("fps"),        "FPS mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");

        System.out.println("=== TC07 Set and Save Profile 3 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC08 - Set Values + Save Profile 4
    // ─────────────────────────────────────────────
    @Test
    public void setandsaveprofile4() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("setvalueprofile4");

        launchandopen(4);

        streampage.setcodec(data.get("codec"));
        streampage.setresolution(data.get("resolution"));
        streampage.setbitrate(data.get("bitrate"));
        streampage.setfps(data.get("fps"));
        streampage.disableaudio();
        streampage.enableroi();

        streampage.clicksave();
 

        soft.assertEquals(streampage.getcodec(),      data.get("codec"),      "Codec mismatch!");
        soft.assertEquals(streampage.getresolution(), data.get("resolution"), "Resolution mismatch!");
        soft.assertEquals(streampage.getbitrate(),    data.get("bitrate"),    "Bitrate mismatch!");
        soft.assertEquals(streampage.getfps(),        data.get("fps"),        "FPS mismatch!");
        soft.assertEquals(String.valueOf(streampage.isaudioselected()), data.get("audio"), "Audio mismatch!");
        soft.assertEquals(String.valueOf(streampage.isroiselected()),   data.get("roi"),   "ROI mismatch!");

        System.out.println("=== TC08 Set and Save Profile 4 ===");
        System.out.println("Camera Type  : " + getcameratype());
        System.out.println("Codec        : " + streampage.getcodec());
        System.out.println("Resolution   : " + streampage.getresolution());
        System.out.println("Bitrate      : " + streampage.getbitrate());
        System.out.println("FPS          : " + streampage.getfps());

        soft.assertAll();
    }

    // ─────────────────────────────────────────────
    // TC09 - Cancel Button Profile 1
    // ─────────────────────────────────────────────
    @Test
    public void cancelprofile1() throws IOException {
        SoftAssert soft = new SoftAssert();
        HashMap<String, String> data = testdata("defaultvalueprofile1");

        launchandopen(1);

        // change values
        streampage.setcodec("H.265");
        streampage.setfps("10");

        // cancel — values should revert
        streampage.clickcancel();

        // verify original values still there
        soft.assertEquals(streampage.getcodec(), data.get("codec"), "Codec should revert after cancel!");
        soft.assertEquals(streampage.getfps(),   data.get("fps"),   "FPS should revert after cancel!");

        System.out.println("=== TC09 Cancel Profile 1 ===");
        System.out.println("Codec after cancel : " + streampage.getcodec());
        System.out.println("FPS after cancel   : " + streampage.getfps());

        soft.assertAll();
    }
}