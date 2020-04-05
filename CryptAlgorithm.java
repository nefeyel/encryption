package encryptdecrypt;

/* Interface for implementation of both console and file encryption (decryption) with string buffer for placing
results into */
public interface CryptAlgorithm {

    static StringBuffer stringBuffer = new StringBuffer();

    StringBuffer consoleEncryption(String data, int key);
    StringBuffer consoleDecryption(String data, int key);

    void fileEncryption(int key, String in, String out);
    void fileDecryption(int key, String in, String out);


}
