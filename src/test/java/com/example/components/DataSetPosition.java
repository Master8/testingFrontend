package com.example.components;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Adgam on 15.04.2017.
 */
public class DataSetPosition extends ElementsContainer {

    public SelenideElement getPassportButton() {
        return $("ul.items-list.category-221-list > li:nth-child(1) > a:nth-child(1) > div:nth-child(1) > span:nth-child(3) > span#dropPassportLink.export");
    }

    public SelenideElement getPasportPanel() {
        return $("ul#dropPass");
    }

    public SelenideElement getPassportItem() {
        return $("ul#dropPass > li:nth-child(1)");
    }

    public SelenideElement getExportButton() {
        return $("ul.items-list.category-221-list > li:nth-child(1) > a:nth-child(1) > div:nth-child(1) > span:nth-child(3) > span#dropDepartmentsLink.export");
    }

    public SelenideElement getExportPanel() {
        return $("ul#dropExport");
    }

    public SelenideElement getExportItem() {
        return $("ul#dropExport > li:nth-child(2) > div > a");
    }
}
