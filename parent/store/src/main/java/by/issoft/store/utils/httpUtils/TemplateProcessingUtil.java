package by.issoft.store.utils.httpUtils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class TemplateProcessingUtil {

    @SneakyThrows
    public static String getTemplate(String templateFile){
        return templateReader(templateFile);
    }
    @SneakyThrows
    public static String getTemplate(String templateFile, List<String> modelData ,String renderPattern
            , String injectTag){
        return renderTemplate(templateReader(templateFile),modelData,renderPattern,injectTag);

    }

    private static String renderTemplate(String template
            ,List<String> modelData
            ,String renderPattern
            , String injectTag){
//        Document html = Jsoup.parse(template);
        String renderString = modelData.stream()
                .map(element -> String.format(renderPattern,element))
                .reduce("", String::concat);
        return template.replace(injectTag,renderString);
    }

    @SneakyThrows
    private static String templateReader(String templateFile){
        String templateString;
        BufferedReader br;
        br = new BufferedReader(new FileReader("parent/store/src/main/resources/"+templateFile));

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        templateString = sb.toString();
        br.close();
        return templateString;

    }

}
