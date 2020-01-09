package mil.dtic.cbes.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhiteLister {

    // default pwd length for the checkPwd() method
    final static int constantLength = 15;

    protected static final Logger log = LoggerFactory.getLogger(WhiteLister.class);

    public static synchronized String safetext(String input, String allowed, String replacementChar) {

        String whiteList = "[^a-zA-Z0-9_" + allowed + ".-]";

        Pattern p = null;
        try {
            p = Pattern.compile(whiteList);
        }
        catch (PatternSyntaxException ex) {
            log.info("There was an error parsing the text using the pattern: " + whiteList);
            return "";
        }

        Matcher matcher = p.matcher(input);		  
        return matcher.replaceAll(replacementChar);
    }

    public static String safetext(String input, String allowed) {
        return safetext(input, allowed, "_");
    }

    public static String safetext(String str) {
        return (safetext(str, ""));
    }

    /**
     * checkPwd methods verify that the user entered pwds are: 1.
     * Proper length. 2. Contains at least two digits. 3. Contains at least two
     * lower case characters. 4. Contains at least two upper case characters. 5.
     * Contains at least two special characters. 6. Are in the standard American
     * keyboard set (e.g., no umlauts) This is required because the Directory
     * Server does not like those characters such as an umlaut in pwds.
     *
     * The method is overloaded. First version takes pwd and length
     * arguments. The second one takes only pwd argument and defaults
     * length to 8. The third one takes a pwd and userid (it makes sure the
     * userid is not contained in the pwd).
     */

    /** Overloaded method that excepts pwd and length arguments **/
    public static boolean checkPwd(String pwd, int length) {
        return checkPwdDetail(pwd, length);
    }

    /**
     * Overloaded method that excepts pwd argument and defaults length to
     * eight characters.
     */
    public static boolean checkPwd(String pwd) {
        return checkPwdDetail(pwd, constantLength);
    }

    /**
     * Overloaded method that excepts pwd argument and defaults length to
     * eight characters, and has the userid for a 2nd arg. The userid is checked
     * to make sure it is not contained in the pwd.
     */
    public static boolean checkPwd(String pwd, String userid) {
        return checkPwdDetail(pwd, constantLength)
                && !isUseridInPwd(pwd, userid);
    }

    /** The body of the chechPwd methods **/
    private static synchronized boolean checkPwdDetail(String pwd, int length) {
        boolean isLongEnough = false;

        int hasSpecialChar = 0;
        int hasUpperCase = 0;
        int hasLowerCase = 0;
        int hasDigit = 0;

        char[] contents = pwd.toCharArray();

        if (pwd.length() >= length) {
            isLongEnough = true;
        }

        for (int i = 0; i < contents.length; i++) {
            if (Character.isDigit(contents[i])) {
                hasDigit++;
            } else if (!Character.isLetterOrDigit(contents[i])) {
                hasSpecialChar++;
            } else if (Character.isUpperCase(contents[i])) {
                hasUpperCase++;
            } else if (Character.isLowerCase(contents[i])) {
                hasLowerCase++;
            }
        }

        return isLongEnough && hasSpecialChar > 1 && hasUpperCase > 1
                && hasLowerCase > 1 && hasDigit > 1 && is7bitClean(pwd);
    }

    /**
     * Is userid contained in pwd?
     */
    static synchronized boolean isUseridInPwd(String pwd, String userid) {
        if (pwd == null || userid == null) {
            return false;
        }

        int index = pwd.indexOf(userid);

        if (index == -1) // not found
        {
            return false;
        } else // userid is a substring of pwd
        {
            return true;
        }
    }

    /**
     * Is the string 7-bit clean--ASCII, in other words. This check needs to be
     * done for LDAP pwds (since they must be 7-bit clean). If the string
     * is null, false will be returned.
     */
    public static synchronized boolean is7bitClean(String str) {
        if (str == null)
            return false;

        for (int i = 0; i < str.length(); i++) {
            int intch = (int) str.charAt(i);

            // Standard "range" of 7-bit ASCII chars
            if (intch < 32 || intch > 126) {
                return false;
            }
        }

        return true;
    }
}

