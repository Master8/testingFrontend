package com.example.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.components.*;
import com.example.components.search.SearchButton;
import com.example.components.search.SearchInput;
import com.example.pages.DataPage;
import com.example.pages.DataSetPage;
import com.example.pages.MainPage;
import com.example.pages.PassportJsonPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;


@Test
@Report
public class MainPageTest extends BaseTest {
    private static final String CATEGORY_NAME = "Архитектура и строительство";

    private DataPage getDataPage() {
        MainPage page = page(MainPage.class);

        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoryByName(CATEGORY_NAME).getUnit().click();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();

        return dataPage;
    }

    private DataSetPosition getDataSetPosition(String nameDataSetPosition){

        return getDataPage().getDataSetPosition(nameDataSetPosition);
    }
    
    
    private DataSetPage getDataSetPage(DataSetPosition dataSetPosition){

        dataSetPosition.getSelf().click();

        String url = dataSetPosition.getSelf().findElementByTagName("a").getAttribute("href");

        DataSetPage dataSetPage = new DataSetPage(url);
        dataSetPage.shouldBeOpened();
        
        return dataSetPage;
    }


    @Test
    public void testSearchByName() throws Exception {
        DataPage dataPage = getDataPage();

        final String query = "реестр";

        SearchInput input = dataPage.getSearchInput();
        input.getSelf().setValue(query);
        SearchButton searchButton = dataPage.getSearchButton();
        searchButton.getSelf().click();

        ElementsCollection results = searchButton.getSearchReasults();

        if (results.size() != 0) {
            results.forEach((SelenideElement element) -> {
                if (element.isDisplayed())
                    element.shouldHave(text(query));
            });
        } else {
            dataPage.getSearchMessage().getSelf().shouldNotHave(cssClass("hidden"));
        }
    }

    @Test
    public void testOpenAndCloseInformationCard() throws Exception {
        DataSetPosition dataSetPosition = getDataSetPosition("Информация по вводу жилья");
        DataSetPage dataSetPage = getDataSetPage(dataSetPosition);

        ItemData item = dataSetPage.getItemData("Всего на территории города");
        InformationCard card = dataSetPage.getInformatinCard();
        item.getSelf().click();
        card.getSelf().shouldNotHave(cssClass("closed"));
        item.getSelf().click();
        card.getSelf().shouldHave(cssClass("closed"));
    }

    @Test
    public void testCheckPassport() throws Exception {
        DataSetPosition dataSetPosition = getDataSetPosition("Информация по вводу жилья");
        SelenideElement passportButton = dataSetPosition.getPassportButton();
        passportButton.click();

        SelenideElement panel = dataSetPosition.getPasportPanel();
        panel.shouldHave(cssClass("open"));

        SelenideElement item = dataSetPosition.getPassportItem();
        item.click();

        PassportJsonPage passportJsonPage = new PassportJsonPage("https://data.mos.ru/apiproxy/opendata/7703742961-informatsiya-po-vvodu-jilya/meta.json");
        passportJsonPage.shouldBeOpened();
    }


    @Test
    public void testExport() throws Exception {
        DataSetPosition dataSetPosition = getDataSetPosition("Информация по вводу жилья");
        SelenideElement exportButton = dataSetPosition.getExportButton();
        exportButton.click();

        SelenideElement panel = dataSetPosition.getExportPanel();
        panel.shouldHave(cssClass("open"));

        SelenideElement item = dataSetPosition.getExportItem();

        File file = item.download();
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testHideAndShowColumns() throws Exception {
        DataSetPosition dataSetPosition = getDataSetPosition("Информация по вводу жилья");
        DataSetPage dataSetPage = getDataSetPage(dataSetPosition);

        List<Column> columns = dataSetPage.getColumns();

        Settings settings = dataSetPage.getSettings();
        settings.getSelf().click();
        SelenideElement panelSettings = settings.getPanelSettings();
        panelSettings.shouldHave(cssClass("open"));
        dataSetPage.getAllDropColumnsItem().getSelf().click();
        settings.getSelf().click();

        columns.forEach(column -> column.getSelf().isDisplayed());

        List<DropColumnsItem> dropColumnsItems = dataSetPage.getDropColumnsItems();

        for (int i = 0; i < dropColumnsItems.size(); i++) {
            settings.getSelf().click();
            dropColumnsItems.get(i).getSelf().click();
            settings.getSelf().click();
            Assert.assertFalse(columns.get(i).getSelf().isDisplayed());
        }
    }

}
