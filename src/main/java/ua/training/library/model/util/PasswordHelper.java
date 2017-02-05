package ua.training.library.model.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * <p> The Util class.
 * This class provide method for encoding user password.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class PasswordHelper {

    private static final Logger logger = Logger.getLogger(PasswordHelper.class);

    /**
     * The constant contains encoding algorithm
     */
    public static final String ALGORITHM = "MD5";

    // Password encoding sample for filling DB testing items
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException
    {
        String passwordToHash = "0000";

        String securePassword = getSecurePassword(passwordToHash);
        System.out.println(securePassword);
    }

    /**
     * This method encodes password
     *
     * @param passwordToHash - this parameter contains password
     * @return - encoded password
     */
    public static String getSecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Fail in password encoding.");
        }
        return generatedPassword;
    }

}
