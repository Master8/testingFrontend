package com.example.components;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by maste on 5/11/2017.
 */
public class Settings extends ElementsContainer {

    public SelenideElement getPanelSettings() {
        return $("div#dropColumns");
    }
}
