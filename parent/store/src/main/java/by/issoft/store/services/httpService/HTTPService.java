package by.issoft.store.services.httpService;

import by.issoft.store.services.httpService.handlers.*;
import by.issoft.store.utils.commandUtils.FabricCommands;
import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.util.List;


//TODO Handlers in separate package and 1 handler 1 class!!!
public class HTTPService {

        protected static HTTPService httpService=null;

    public static FabricCommands getHttpStoreFabricCommands() {
        return httpStoreFabricCommands;
    }

    private static FabricCommands httpStoreFabricCommands=null;


    public static void setCategoryListCacshe(List<String> categoryListCashe) {
        HTTPService.categoryListCashe = categoryListCashe;
    }

    public static List<String> getCategoryListCashe() {
        return categoryListCashe;
    }

    private static List<String> categoryListCashe=null;

    public static void setCategoriesAndProductsList( List<String> categoriesAndProductsList) {
        HTTPService.categoriesAndProductsList = categoriesAndProductsList;
    }

    public static List<String> getCategoriesAndProductsList() {
        return categoriesAndProductsList;
    }

    private static List<String> categoriesAndProductsList = null;

        private HTTPService(){}

        public static HTTPService getHTTPService(FabricCommands storeFabricCommands){
            if (httpService == null){
                httpStoreFabricCommands=storeFabricCommands;
                httpService =new HTTPService();
            }
            return httpService;
        }

        @SneakyThrows
        public  void startHttpServer() {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/index", new IndexHandler());
            server.createContext("/categories", new CategoryHandler());
            server.createContext("/products", new ProductHandler());
            server.createContext("/top5", new ProductHandler());
            server.createContext("/templates/", new TemplateHandler());
            server.createContext("/static/", new StaticHandler());
            server.setExecutor(null); // creates a default executor
            server.start();


            server.createContext("/sort", new IndexHandler());
            //server.createContext("/top5/", new ProductHandler());
            server.createContext("/order/addproduct", new AddProductHandler());

        }








}
