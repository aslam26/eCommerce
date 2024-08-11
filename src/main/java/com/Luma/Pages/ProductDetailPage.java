package com.Luma.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;

public class ProductDetailPage extends BasePage{

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css="a#ui-id-5")
    WebElement MenTab;

    @FindBy(css="a#ui-id-17")
    WebElement TopMenu;

    @FindBy(css="a#ui-id-19")
    WebElement JacketMenu;


   // WebElement montanaWindJacket=driver.findElement(By.cssSelector("li:nth-child(2) div > strong"));

    //WebElement taurusJacket=driver.findElement(RelativeLocator.with(By.cssSelector("ol > li div > strong > a")).below(montanaWindJacket));

    private WebElement getMontanaWindJacket() {
        return driver.findElement(By.cssSelector("li:nth-child(2) div > strong"));
    }

    private WebElement getTaurusJacket() {
        return driver.findElement(RelativeLocator.with(By.cssSelector("ol > li div > strong > a")).below(getMontanaWindJacket()));
    }


    public void navigateToProductPage(){
        Actions actions=new Actions(driver);
        actions.moveToElement(MenTab)
                .moveToElement(TopMenu)
                .moveToElement(JacketMenu)
                .perform();
        JacketMenu.click();
    }

    public String findTheElement(){
        return getTaurusJacket().getText();
    }



}
