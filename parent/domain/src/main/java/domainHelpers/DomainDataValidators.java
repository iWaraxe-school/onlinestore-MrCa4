package domainHelpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainDataValidators {

    public static String stringValidator(String input){
        Pattern pattern = Pattern.compile("\\W");
        Matcher matcher = pattern.matcher(input);
        String result = matcher.replaceAll("");
        return result;
    }


}


