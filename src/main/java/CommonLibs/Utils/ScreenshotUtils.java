package CommonLibs.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Create by Aslam Mujawar on 11/08/24
 */

public class ScreenshotUtils {

    WebDriver driver;
    private TakesScreenshot camera;
    String currentWorkingDir=System.getProperty("user.dir");


    public ScreenshotUtils(WebDriver driver) throws IOException {
        this.driver=driver;
        this.camera=(TakesScreenshot) driver; //Type casting
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


    //Comparing screen using AShot library
    public  boolean visualTest(String baselineImageFile) throws IOException {

        driver.manage().window().maximize();
        BufferedImage actualImage = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver)
                .getImage();
        ImageIO.write(actualImage, "PNG", new File(currentWorkingDir+"/resources/fullpage_screenshot.png"));

        BufferedImage expectedImage = ImageIO.read(new File(baselineImageFile));

        //compare the images
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualImage);

        boolean flag=!diff.hasDiff();

        return flag;


    }
}
