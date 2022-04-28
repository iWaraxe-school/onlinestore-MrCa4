package by.issoft.store.services.httpService;

public enum HTMLPatternEnum {
    H3_WHITE_TEXT_WITH_BRTAG("<a href=\"/products/%s\" class=\"categories\">%s</a><br>"),
    PRODUCTS_WITH_LINK_TO_ORDER("<form action=\"/order/addproduct\" id=\"submit\" method=\"POST\">\n" +
            "<input name=\"surname\" value=\"%s\" class=\"input\" size=100 readonly=1>\n" +
            "<input type=\"submit\" class=\"but\" value=\"Add to cart\"><br><br>");



    private String pattern;

    public String getPattern() {
        return pattern;
    }


    HTMLPatternEnum(String pattern) {
        this.pattern = pattern;

    }
}
