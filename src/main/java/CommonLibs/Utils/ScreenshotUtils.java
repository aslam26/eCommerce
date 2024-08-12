package CommonLibs.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Create by Aslam Mujawar on 11/08/24
 */

public class ScreenshotUtils {

    private TakesScreenshot camera;

    public ScreenshotUtils(WebDriver driver){
     camera=(TakesScreenshot) driver; //Type casting
    }

    public void captureAndSaveScreenshots(String filename) throws Exception {
        filename=filename.trim();
        File imgFile, tmpFile;
        imgFile=new File(filename);

        if(imgFile.exists()){
            throw new Exception("File does exist");
        }
        tmpFile=camera.getScreenshotAs(OutputType.FILE);

        FileUtils.moveFile(tmpFile,imgFile);
    }
}
