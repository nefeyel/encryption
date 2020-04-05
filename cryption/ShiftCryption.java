package encryptdecrypt.cryption;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShiftCryption extends Cryption {

    /**
     * Every call to this method generates upper case english alphabet
     * @return string buffer containing all english alphabet chars in capital letters
     */
    public StringBuffer getUpperCaseAlphabet() {
        StringBuffer upperCaseBuffer = new StringBuffer();
        for(char c = 'A'; c <= 'Z'; ++c) {
            upperCaseBuffer.append(c);
        }
        return upperCaseBuffer;
    }

    /**
     * Every call to this method generates lower case english alphabet
     * @return string buffer containing all english alphabet chars in small caps
     */
    public StringBuffer getLowerCaseAlphabet() {
        StringBuffer lowerCaseBuffer = new StringBuffer();
        for(char c = 'a'; c <= 'z'; ++c) {
            lowerCaseBuffer.append(c);
        }
        return lowerCaseBuffer;
    }

    /**
     * Encryption method for encrypting text data from a String using a key by which it shifts the input character
     * from the english alphabet.
     * @param data String text for encryption, can contain any character from english alphabet.
     * @param key integer number by which it shifts the input character and replaces it by the other character which
     *            position is (input character position) + key in the english alphabet.
     * @return resulting StringBuffer type object which contains all encrypted letters.
     */
    @Override
    public StringBuffer consoleEncryption(String data, int key) {
        int alphabetNum;
        char shiftItem = 0;
        for (char item : data.toCharArray()) {
            if (Character.isLowerCase(item)) {
                alphabetNum = getLowerCaseAlphabet().toString().indexOf(item);
                shiftItem = alphabetNum + key >= getLowerCaseAlphabet().length() ?
                        getLowerCaseAlphabet().charAt(alphabetNum + key - getLowerCaseAlphabet().length()) :
                        getLowerCaseAlphabet().charAt(alphabetNum + key);



            } else if (Character.isUpperCase(item)){
                alphabetNum = getUpperCaseAlphabet().toString().indexOf(item);
                shiftItem = alphabetNum + key >= getUpperCaseAlphabet().length() ?
                        getUpperCaseAlphabet().charAt(alphabetNum + key - getUpperCaseAlphabet().length()) :
                        getUpperCaseAlphabet().charAt(alphabetNum + key);

            } else {
                shiftItem = item;
            }
            stringBuffer.append(shiftItem);
        }
        return stringBuffer;
    }

    /**
     * Decryption method for decrypting text data from a String using a key by which it shifts the input character
     * from the english alphabet.
     * @param data text for decryption, can contain any character from english alphabet.
     * @param key integer number by which it shifts the input character and replaces it by the other character which
     *            position is (input character position) - key in the english alphabet.
     * @return resulting StringBuffer type object which contains all decrypted letters.
     */
    @Override
    public StringBuffer consoleDecryption(String data, int key) {
        int alphabetNum;
        char shiftItem = 0;
        for (char item : data.toCharArray()) {
            if (Character.isLowerCase(item)) {
                alphabetNum = getLowerCaseAlphabet().toString().indexOf(item);
                shiftItem = alphabetNum - key < 0 ?
                        getLowerCaseAlphabet().charAt(alphabetNum - key + getLowerCaseAlphabet().length()) :
                        getLowerCaseAlphabet().charAt(alphabetNum - key);
            } else if (Character.isUpperCase(item)){
                alphabetNum = getUpperCaseAlphabet().toString().indexOf(item);
                shiftItem = alphabetNum - key < 0 ?
                        getUpperCaseAlphabet().charAt(alphabetNum - key + getUpperCaseAlphabet().length()) :
                        getUpperCaseAlphabet().charAt(alphabetNum - key);
            } else {
                shiftItem = item;
            }
            stringBuffer.append(shiftItem);
        }
        return stringBuffer;
    }

    /**
     * Encryption of the text string containing only english letters from the text file and putting the result in
     * output text file.
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
     * Encryption of the text string containing only english letters from the text file and putting the result in
     * output text file.
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