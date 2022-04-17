package by.issoft.store.utils;

import by.issoft.store.utils.dbUtils.DBClientUtil;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomStoreDBPopulator {

    private static final String CATEGORY_TABLE_NAME = "category";

//    private static final String SESSION_TABLE_NAME = "session";
//    private static final String SESSION_TABLE_FIELDS = "session_id, session, user_id, time_expiration";
//    private static final String USERS_TABLE_NAME = "users";
//    private static final String USERS_TABLE_FIELDS = "user_id, username, password_hash, last_login";
//    private static final String ORDERS_TABLE_NAME = "orders";
//    private static final String ORDERS_TABLE_FIELDS = "order_id, user_id, product_id_list";
//    private static final String ORDERS_TABLE_FIELDS_INITIALIZATION =
//            "order_id INT NOT NULL AUTO_INCREMENT, " +
//            "user_id int, " +
//            "FOREIGN KEY(user_id) REFERENCES user(user_id), " +
//            "product_id_list varchar(50)";
    private static final String CATEGORY_TABLE_INSERT_FIELDS = "category_name";
    private static final String PRODUCTS_TABLE_INSERT_FIELDS = "product_name, product_rate, product_price, category_id";
    private static final String PRODUCTS_TABLE_NAME = "products";
    private static final String CATEGORY_TABLE_FIELDS_INITIALIZATION = "" +
            "category_id INTEGER PRIMARY KEY, " +
            "category_name varchar(30)";
    private static final String PRODUCT_TABLE_FIELDS_INITIALIZATION = "" +
            "product_id INTEGER PRIMARY KEY, " +
            " product_name varchar(30)," +
            " product_rate int," +
            " product_price double," +
            " category_id int," +
            " FOREIGN KEY(category_id) REFERENCES categories(id)";


    private DBClientUtil dbClientUtil;

    public RandomStoreDBPopulator(){

    }

    public void process(){
        this.deleteTable(CATEGORY_TABLE_NAME);
        this.deleteTable(PRODUCTS_TABLE_NAME);
        this.createTable(CATEGORY_TABLE_NAME,CATEGORY_TABLE_FIELDS_INITIALIZATION);
        this.createTable(PRODUCTS_TABLE_NAME,PRODUCT_TABLE_FIELDS_INITIALIZATION);
        generateStoreCategoriesAndProducts();
    }

    private void deleteTable(String tableName)  {
        DBClientUtil.exec( String.format("DROP TABLE IF EXISTS %s;", tableName));
    }

    private void createTable(String tableName, String fields)  {
        DBClientUtil.exec( String.format("CREATE TABLE IF NOT EXISTS %s (%s);", tableName, fields));
    }

    private void insertFieldIntoTable(String tableName, String fields, String values){
        DBClientUtil.exec(String.format("INSERT INTO %s (%s) VALUES ('%s');"
                , tableName, fields, values.replaceAll("'","`")
                                            .replaceAll(",","','")));
    }

    private void generateStoreCategoriesAndProducts(){
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        AtomicInteger category_id_iterator= new AtomicInteger();
        randomStorePopulator.getAllCategories().forEach(category-> {
            insertFieldIntoTable(CATEGORY_TABLE_NAME, CATEGORY_TABLE_INSERT_FIELDS, category.getName());
            category_id_iterator.getAndIncrement();
            randomStorePopulator.generateProductsForCategory(category, Optional.of(10)).forEach(product->
                    insertFieldIntoTable(PRODUCTS_TABLE_NAME, PRODUCTS_TABLE_INSERT_FIELDS, product.getName() +
                            ',' + product.getRate() +
                            ',' + product.getPrice() +
                            ',' + category_id_iterator.get()));
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

