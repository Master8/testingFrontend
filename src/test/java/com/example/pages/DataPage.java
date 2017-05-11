package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.example.components.DataSetPosition;
import com.example.components.search.SearchButton;
import com.example.components.search.SearchInput;
import com.example.components.search.SearchMessage;

import java.util.HashMap;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DataPage extends AbstractPage {

    public HashMap<String, String> dataSetsPositions = new HashMap<String, String>();

    public DataPage() {
        super();
        this.url = "https://data.mos.ru/opendata";

    }

    public AbstractPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public AbstractPage waitPageLoaded() {
        $(".loader-block").waitWhile(visible, 30000);
        return this;
    }

    public DataSetPosition getDataSetPosition(String nameDataSetPosition) {

        DataSetPosition dataSetPosition = new DataSetPosition();

        SelenideElement dataSetPositionElement = $$(".category-221-list li").find(text(nameDataSetPosition));

        dataSetPosition.setSelf(dataSetPositionElement);

        return dataSetPosition;
    }

    public SearchInput getSearchInput() {
        SearchInput input = new SearchInput();
        input.setSelf($("input#text"));
        return input;
    }

    public SearchButton getSearchButton() {
        SearchButton button = new SearchButton();
        button.setSelf($("input"));
        return button;
    }

    public SearchMessage getSearchMessage() {
        SearchMessage message = new SearchMessage();
        message.setSelf($("div#notFoundMsg"));
        return message;
    }
}
