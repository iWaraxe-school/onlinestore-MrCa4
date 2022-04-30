package by.issoft.store.utils.dbUtils;

public enum TemplateQueryEnum {
    //public String query;
    GET_CATEGORY_LIST("SELECT * FROM category"),
    GET_PRODUCT_LIST_BY_CATEGORY("SELECT * FROM products WHERE category_id = %s"),
    INSERT_NEW_ORDER("INSERT INTO orders (order_string) VALUES ('%s');"),
    GET_ORDERS("SELECT * FROM orders;");

    private String query;

    TemplateQueryEnum(String query) {
        this.query = query;

    }
    public String getQuery() {
        return query;
    }



}

