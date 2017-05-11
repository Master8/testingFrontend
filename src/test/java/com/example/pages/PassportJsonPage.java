package com.example.pages;


import java.util.Set;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Adgam on 25.04.2017.
 */
public class PassportJsonPage extends AbstractPage {

    public PassportJsonPage(String url){
        super();
        this.url = url;

        Set<String> handles = getWebDriver().getWindowHandles();
        switchTo().window((String) handles.toArray()[handles.size()-1]);
    }

    @Override
    public AbstractPage waitPageLoaded() {
        return this;
    }
}
