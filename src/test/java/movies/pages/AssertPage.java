package movies.pages;

import net.thucydides.core.pages.PageObject;

import java.util.Arrays;

public class AssertPage extends PageObject {

    public Boolean assertKeyValues(String[] actual, String[] expected) {

        return Arrays.equals(actual, expected);
    }

}
