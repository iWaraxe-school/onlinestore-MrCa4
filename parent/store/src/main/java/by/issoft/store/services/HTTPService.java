package by.issoft.store.services;

import by.issoft.store.utils.commandUtils.FabricCommands;
import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//TODO Handlers in separate package and 1 handler 1 class!!!
public class HTTPService {

        private static HTTPService httpService=null;
        private static FabricCommands httpStoreFabricCommands=null;


    public static void setCategoryListCacshe(List<String> categoryListCashe) {
        HTTPService.categoryListCashe = categoryListCashe;
    }

    private static List<String> categoryListCashe=null;

    public static void setCategoriesAndProductsMap(Map<String, List<String>> categoriesAndProductsMap) {
        HTTPService.categoriesAndProductsMap = categoriesAndProductsMap;
    }

    private static Map<String,List<String>> categoriesAndProductsMap = null;

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
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/index", new IndexHandler());
            server.createContext("/categories", new CategoryHandler());
            server.createContext("/products", new ProductsHandler());
            server.createContext("/sort", new IndexHandler());
            server.createContext("/top5/", new IndexHandler());
            server.createContext("/order/addproduct", new IndexHandler());
            server.createContext("/templates/", new TemplateHandler());
            server.setExecutor(null); // creates a default executor

            server.start();
        }

    static class ProductsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response;
            if (t.getRequestMethod().toLowerCase().equals("get")){
                httpStoreFabricCommands.exec("List All");
                response = TemplateProcessingUtil.getTemplate(
                        "templates/products.html"
                        //TODO - defferent render collections..generics?????
                        , new ArrayList<>()//categoriesAndProductsMap
                        ,HTMLPatternEnum.H3_WHITE_TEXT_WITH_BRTAG.getPattern()
                        ,"<!--<test>-->");
                t.sendResponseHeaders(200, response.length());
            }
            else{
                response = "Method Not Allowed";
                t.sendResponseHeaders(405, response.length());
            }

            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class CategoryHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response;
            if (t.getRequestMethod().toLowerCase().equals("get")){
                httpStoreFabricCommands.exec("List Category");
                response = TemplateProcessingUtil.getTemplate(
                        "templates/categories.html"
                        ,categoryListCashe
                        ,HTMLPatternEnum.H3_WHITE_TEXT_WITH_BRTAG.getPattern()
                        ,"<!--<test>-->");
                t.sendResponseHeaders(200, response.length());
            }
            else{
                response = "Method Not Allowed";
                t.sendResponseHeaders(405, response.length());
            }

            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class TemplateHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response;
            if (t.getRequestMethod().toLowerCase().equals("get")){
                response = TemplateProcessingUtil.getTemplate(String.valueOf(t.getRequestURI()));
                t.sendResponseHeaders(200, response.length());
            }
            else{
                response = "Method Not Allowed";
                t.sendResponseHeaders(405, response.length());
            }

            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
        static class IndexHandler implements HttpHandler {
            @Override
            public void handle(HttpExchange t) throws IOException {
                String response;
                if (t.getRequestMethod().toLowerCase().equals("get")){
                    System.out.println("we in get");
                    response = TemplateProcessingUtil.getTemplate("templates/index.html");
                    t.sendResponseHeaders(200, response.length());
                }
                else{
                    response = "Method Not Allowed";
                    t.sendResponseHeaders(405, response.length());
                }

                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }


}
