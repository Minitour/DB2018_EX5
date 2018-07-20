package utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Antonio Zaitoun on 20/07/2018.
 */
public final class Utils {

    //private constructor to avoid instances
    private Utils(){}

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private static final String VALID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private static String generateToken(int length,String from) {
        StringBuilder sb =  new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++)
            sb.append(from.charAt(random.nextInt(from.length())));

        return sb.toString();
    }

    private static String generateToken(int length) {
        return generateToken(length, VALID_CHARS);
    }


    /**
     * Generate a random session token with a length of 128.
     * @return
     */
    public static String generateToken(){
        return generateToken(128);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validate(final String hex) {
        return pattern.matcher(hex).matches();
    }
}
