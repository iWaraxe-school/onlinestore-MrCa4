package by.issoft.store.services.httpService;

import by.issoft.store.services.authService.AuthService;
import by.issoft.store.services.httpService.handlers.*;
import by.issoft.store.utils.commandUtils.FabricCommands;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//TODO Handlers in separate package and 1 handler 1 class!!!
public class HTTPService {

        protected static HTTPService httpService=null;

    public static FabricCommands getHttpStoreFabricCommands() {
        return httpStoreFabricCommands;
    }

    private static FabricCommands httpStoreFabricCommands=null;

    public static FabricCommands getHttpOrderFabricCommands() {
        return httpOrderFabricCommands;
    }

    public static void setHttpOrderFabricCommands(FabricCommands httpOrderFabricCommands) {
        HTTPService.httpOrderFabricCommands = httpOrderFabricCommands;
    }

    private static FabricCommands httpOrderFabricCommands=null;


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


    public static Map<String, List<String>> getOrderMap() {
        return orderMap;
    }

    public static void clearOrderMap(){
        orderMap.clear();
    }

    public static void setOrderMap(Map<String, List<String>> orderMap) {
        HTTPService.orderMap = orderMap;
    }

    private static Map<String,List<String>> orderMap = new HashMap<>();



    private static List<String> categoriesAndProductsList = null;

        private HTTPService(){}

        public static HTTPService getHTTPService(FabricCommands storeFabricCommands, FabricCommands orderFabricCommands){
            if (httpService == null){
                httpStoreFabricCommands=storeFabricCommands;
                httpOrderFabricCommands=orderFabricCommands;
                httpService =new HTTPService();
            }
            return httpService;
        }


        @SneakyThrows
        public  void startHttpServer() {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/",new IndexHandler());
            server.createContext("/index", new IndexHandler());
            server.createContext("/confirm", new ConfirmOrderHandler());
            server.createContext("/categories", new CategoryHandler());
            server.createContext("/products/", new ProductHandler());
            server.createContext("/top5", new ProductHandler());
            server.createContext("/templates/", new TemplateHandler());
            server.createContext("/static/", new StaticHandler());
            server.createContext("/order/addproduct", new AddProductHandler());
            server.createContext("/cart/", new CartHandler());
            AuthService.setSecurity(server.createContext("/manager", new ManagerHandler()));
            server.setExecutor(null);
            server.start();

        }

        @SneakyThrows
        public static String readPostData(HttpExchange t){
            InputStreamReader isr =  new InputStreamReader(t.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            int b;
            StringBuilder buf = new StringBuilder(1024);
            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }
            br.close();
            isr.close();
            return buf.toString();
        }








}
