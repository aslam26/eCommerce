package com.Luma.Pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Aslam Mujawar on 11/08/24
 */

public class MenPage extends BasePage{

    public MenPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="a#ui-id-5")
    WebElement MenTab;

    @FindBy(css="a#ui-id-17")
    WebElement TopMenu;

    @FindBy(css="a#ui-id-19")
    WebElement JacketMenu;

    @FindBy(css=":nth-child(1)  div strong > a")
    List<WebElement> productList;

    @FindBy(xpath="//span[@class='price']")
    List<WebElement> productPrice;

    @FindBy(css="h1#page-title-heading")
    WebElement headingOfPage;

    @FindBy(xpath = "//div[@class='ea-stickybox-hide']")
    WebElement closeAdv;

    @FindBy(xpath = "//main//div[2]/div[3]/select")
    WebElement sortDropdown;

    @FindBy(css="input#search")
    WebElement searchField;

    public String navigateToProductPage(){
        Actions actions=new Actions(driver);
        actions.moveToElement(MenTab)
                .moveToElement(TopMenu)
                .moveToElement(JacketMenu)
                .perform();
        JacketMenu.click();
        return headingOfPage.getText();
    }

    public List<String> getProductNames(){

        List<String> list=new ArrayList<>();
        for(WebElement element: productList){
            list.add(element.getText());
        }
        return list;

    }

    public void closeAdvAlert(){
        closeAdv.click();
    }

    public List<String> getProductPrice(){

        List<String> list=new ArrayList<>();
        for(WebElement element:productPrice){
            list.add(element.getText());
        }
        return list;
    }

    public void selectFromDropDown(String text){
        Select select=new Select(sortDropdown);
        select.selectByVisibleText(text);
    }


    public void searchForItem(String item){
        searchField.sendKeys(item);
        searchField.sendKeys(Keys.ENTER);
    }
}
