package by.issoft.store.services.httpService;

public enum HTMLPatternEnum {
    H3_WHITE_TEXT_WITH_BRTAG("<a href=\"/products/%s\" class=\"categories\">%s</a><br>"),
    PRODUCTS_WITH_LINK_TO_ORDER("<form action=\"/order/addproduct\" id=\"submit\" method=\"POST\">\n" +
            "<input name=\"product\" value=\"%s\" class=\"input\" size=100 readonly=1>\n" +
            "<input type=\"submit\" class=\"but\" value=\"Add to cart\"></form><br><br>"),
    ORDER_HTML_PATTERN("<input name=\"product\" class=\"input\" value=\"%s\" size=100 readonly=1><br>"),
    ORDER_LIST_PATTERN("<textarea class=\"textarea\" rows=\"10\" cols=\"100\">%s</textarea><br>");



    private String pattern;

    public String getPattern() {
        return pattern;
    }


    HTMLPatternEnum(String pattern) {
        this.pattern = pattern;

    }
}
