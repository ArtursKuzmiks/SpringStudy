package SqlTest.AESutil;

/**
 * @author Artur Kuzmik on 18.5.6
 */

public interface AesUtil {

    String encrypt(String origString) throws Exception;

    String decrypt(String encrypted) throws Exception;
}
