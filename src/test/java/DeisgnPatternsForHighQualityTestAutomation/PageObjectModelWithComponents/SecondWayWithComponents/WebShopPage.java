package DeisgnPatternsForHighQualityTestAutomation.PageObjectModelWithComponents.SecondWayWithComponents;

public class WebShopPage {

    public SearchBar searchBar() {
        return new SearchBar();
    }

    public ResultList resultList() {
        return new ResultList();
    }
}

