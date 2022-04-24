package by.issoft.store.services;

public enum HTMLPatternEnum {
    H3_WHITE_TEXT_WITH_BRTAG("<font color=\"white\"><h4>%s</h4></font>");



    private String pattern;

    public String getPattern() {
        return pattern;
    }


    HTMLPatternEnum(String pattern) {
        this.pattern = pattern;

    }
}
