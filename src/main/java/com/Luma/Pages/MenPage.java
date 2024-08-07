package com.Luma.Pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getProductPrice(){

        List<String> list=new ArrayList<>();
        for(WebElement element:productPrice){
            list.add(element.getText());
        }
        return list;
    }


}
