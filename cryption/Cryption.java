package encryptdecrypt.cryption;

import encryptdecrypt.CryptAlgorithm;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cryption implements CryptAlgorithm {

    /**
     * Encryption method for encrypting text data from a String using a key by which it shifts the input unicode character.
     * @param data String text for encryption, can contain any Unicode character.
     * @param key integer number by which it shifts the input character and replaces it by the other character which position is
     *            (input character position) + key in unicode table
     * @return resulting StringBuffer type object which contains all encrypted letters
     */
    @Override
    public StringBuffer consoleEncryption(String data, int key) {
        for (char item : data.toCharArray()) {
            char shiftItem = (char) (item + key);
            stringBuffer.append(shiftItem);
        }
        return stringBuffer;
    }

    /**
     * Decryption method for decrypting text data from a String using a key by which it shifts the input unicode character.
     * @param data text for decryption, can contain any Unicode character.
     * @param key integer number by which it shifts the input character and replaces it by the other character which
     *            position is (input character position) - key in unicode table
     * @return resulting StringBuffer type object which contains all encrypted letters
     */
    @Override
    public StringBuffer consoleDecryption(String data, int key) {
        for (char item : data.toCharArray()) {
            char shiftItem = (char) (item - key);
            stringBuffer.append(shiftItem);
        }
        return stringBuffer;
    }

    /**
     * Encryption of the text unicode string from the text file and putting the result in output text file.
     * @param key integer number by which it shifts the input character and replaces it by the other character
     * @param in input file name as string from which string data is taken for encryption
     * @param out output file name as a string which contains resulting string buffer from encryptConsole method
     *            See also {@link #consoleEncryption(String, int)}.
     */
    @Override
    public void fileEncryption(int key, String in, String out) {
        try (PrintWriter printWriter = new PrintWriter(out)) {
            String data = new String(Files.readAllBytes(Paths.get(in)));
            StringBuffer stringBuffer = consoleEncryption(data, key);

            printWriter.print(stringBuffer);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * Decryption of the text unicode string from the text file and putting the result in output text file.
     * @param key integer number by which it shifts the input character and replaces it by the other character
     * @param in input file name as a string from which string data is taken for decryption
     * @param out output file name as a string which contains resulting string buffer from decryptConsole method
     *            See also {@link #consoleDecryption(String, int)}.
     */
    @Override
    public void fileDecryption(int key, String in, String out) {
        try (PrintWriter printWriter = new PrintWriter(out)) {
            String data = new String(Files.readAllBytes(Paths.get(in)));
            StringBuffer stringBuffer = consoleDecryption(data, key);

            printWriter.print(stringBuffer);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
