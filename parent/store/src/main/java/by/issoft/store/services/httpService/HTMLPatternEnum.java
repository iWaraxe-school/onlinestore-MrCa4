package by.issoft.store.services.httpService;

public enum HTMLPatternEnum {
    H3_WHITE_TEXT_WITH_BRTAG("<font color=\"white\"><h4>%s</h4></font>"),
    PRODUCTS_WITH_LINK_TO_ORDER("<form action=\"/order/addproduct\" id=\"submit\" method=\"POST\">\n" +
            "<input name=\"surname\" value=\"%s\" border=0 size=100 readonly=1>\n" +
            "<input type=\"submit\" value=\"+ to cart\"><br>");



    private String pattern;

    public String getPattern() {
        return pattern;
    }


    HTMLPatternEnum(String pattern) {
        this.pattern = pattern;

    }
}
