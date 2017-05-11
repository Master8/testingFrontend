package com.example.pages;

import com.example.components.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Condition.readonly;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Adgam on 15.04.2017.
 */
public class DataSetPage extends AbstractPage{
    public DataSetPage(String url) {
        super();
        this.url = url;

        Set<String> handles = getWebDriver().getWindowHandles();
        switchTo().window((String) handles.toArray()[handles.size()-1]);
    }

    public AbstractPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public AbstractPage waitPageLoaded() {
        $(".loader-block").waitWhile(visible, 30000);
        return this;
    }

    public ItemData getItemData(String title) {
        ItemData item = new ItemData();
        item.setSelf($$("a > div > span").find(text("Всего на территории города")));
        return item;
    }

    public InformationCard getInformatinCard() {
        InformationCard card = new InformationCard();
        card.setSelf($("section#card"));
        return card;
    }

    public List<Column> getColumns() {
        List<Column> columns = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            columns.add(getColumn(i));
        }

        return columns;
    }

    private Column getColumn(int id) {
        Column column = new Column();
        id++;
        column.setSelf($("table#rows-content > thead > tr > th:nth-child(" + id + ")"));
        return column;
    }

    public DropColumnsItem getAllDropColumnsItem() {
        DropColumnsItem item = new DropColumnsItem();
        item.setSelf($("div#dropColumns > div > ul > li:nth-child(1)"));
        return item;
    }

    public List<DropColumnsItem> getDropColumnsItems() {
        List<DropColumnsItem> items = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            items.add(getDropColumnsItem(i));
        }

        return items;
    }

    private DropColumnsItem getDropColumnsItem(int id) {
        DropColumnsItem item = new DropColumnsItem();
        id++;
        item.setSelf($("div#dropColumns > div > ul > li:nth-child(" + id + ")"));
        return item;
    }

    public Settings getSettings() {
        Settings settings = new Settings();
        settings.setSelf($("a#dropColumnsLink.settings"));
        return settings;
    }
}
