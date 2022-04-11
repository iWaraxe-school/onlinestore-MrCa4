package by.issoft.store.utils;

import java.util.List;

public class RandomStoreDBPopulator {

    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String CATEGORY_TABLE_FIELDS = "category";
    public static final String PRODUCTS_TABLE_FIELDS = "product_name, product_rate, product_price, category_id";
    public static final String PRODUCTS_TABLE_NAME = "products";
    public static final String CATEGORY_TABLE_FIELDS_INITIALIZATION = "id INT NOT NULL AUTO_INCREMENT, " +
            "category_name varchar(30)";
    public static final String PRODUCT_TABLE_FIELDS_INITIALIZATION = "id INT NOT NULL AUTO_INCREMENT," +
            " product_name varchar(30)," +
            " product_rate int," +
            " product_price double," +
            " category_id int," +
            " FOREIGN KEY(cat_id) REFERENCES categories(id)";

    private DBClientUtil dbClientUtil;
    public RandomStoreDBPopulator(){
        this.dbClientUtil = new DBClientUtil();
        this.dbClientUtil.connect();
        this.deleteTable(CATEGORY_TABLE_NAME);
        this.deleteTable(PRODUCTS_TABLE_NAME);
        this.createTable(CATEGORY_TABLE_NAME,CATEGORY_TABLE_FIELDS_INITIALIZATION);
        this.createTable(PRODUCTS_TABLE_NAME,PRODUCT_TABLE_FIELDS_INITIALIZATION);
    }

    public void deleteTable(String tableName)  {
        this.dbClientUtil.exec( String.format("DROP TABLE IF EXISTS %s;", tableName));
    }

    public void createTable(String tableName, String fields)  {
        this.dbClientUtil.exec( String.format("CREATE TABLE IF NOT EXISTS %s (%s);", tableName, fields));
    }

    public void insertFieldIntoTable(String tableName, String fields, String values){
        this.dbClientUtil.exec(String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, fields, values));
    }

    public void generateStoreCategories(){
        List<String> categoryList = List.of("bike","milk","phone");
        categoryList.forEach(category-> {
            insertFieldIntoTable(CATEGORY_TABLE_NAME, CATEGORY_TABLE_FIELDS, category);
        });

    }

//    public List<Product> generateProductsForCategory(Category category, Optional<Integer> countOfProducts){
//        countOfProducts = (countOfProducts == null)? Optional.ofNullable(Integer.valueOf(0)) : countOfProducts;
//        return IntStream.range(0,countOfProducts.orElse(10))
//                .mapToObj(i->new Product(generateProductNameForCurrentCategory(category),generatePrice(),generateRate()))
//                .collect(Collectors.toList());
//
//    }
//
//    private String generateProductNameForCurrentCategory(Category category){
//        return category.getUsableProductName(faker);
//    }
//
//    private Float generatePrice(){
//        return (float) faker.number().randomDouble(1,1,1000);
//    }
//
//    private Float generateRate(){
//        return (float) faker.number().randomDouble(1,1,1000);
//    }
//}





}

