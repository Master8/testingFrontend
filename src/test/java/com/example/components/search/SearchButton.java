package com.example.components.search;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by maste on 5/11/2017.
 */
public class SearchButton extends ElementsContainer {

    public ElementsCollection getSearchReasults() {
        return $$("span.ds-caption-text");
    }
}
