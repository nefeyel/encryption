package encryptdecrypt;

import encryptdecrypt.cryption.Cryption;
import encryptdecrypt.cryption.ShiftCryption;

/**Command line encryption and decryption in java by simple Caesar's
 * shifting encryption in Unicode table and in common english alphabet letters.
 * So encoding is done by replacing a letter for another one by a position (key).
 * Decoding is a reversed step and should return the result before encoding.
 */
public class Main {
    /** Main class with its main method used for command line calling only
     * Using command line parameters we can encrypt or decrypt some text from .txt file
     * or from typing string text in command line
     * @param args as a array may contain:
     * -mode     enc which means encryption mode and dec for decryption mode
     * -key      integer value added to the letter's position in table to make a
     *                  replacement - encoding process
     * -alg      algorithm can be unicode for unicode table and shift for english
     *                  letters only formed in a circle (after last Z comes A B C..)
     * -in       input text file to encrypt or decrypt
     * -out      output text file as a result of encryption or decryption
     * -data     text typed in command line as a string to encrypt or decrypt
     */
    public static void main(String[] args) {

        Cryption cryption = new Cryption();
        ShiftCryption shiftCryption = new ShiftCryption();

/* we set default values in case they aren't entered in command line and that is that the
default mode is encryption, algorithm is english alphabet (shift) letters rounded in a
circle so that after last letter Z comes A again and also before A is Z, and input text
form a command line or a file is empty string.
 */
        String mode = "enc", alg = "shift";
        int key = 0;
        String data = "", in = "", out = "";


        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }

        if (in.equals("") || out.equals("")) {
            if (mode.equals("dec")) {
                if (alg.equals("unicode")) {
                    System.out.println(cryption.consoleDecryption(data, key));
                } else {
                    System.out.println(shiftCryption.consoleDecryption(data, key));
                }
            } else {
                if (alg.equals("unicode")) {
                    System.out.println(cryption.consoleEncryption(data, key));
                } else {
                    System.out.println(shiftCryption.consoleEncryption(data, key));
                }
            }
        } else {
            if (mode.equals("dec")) {
                if (alg.equals("unicode")) {
                    cryption.fileDecryption(key, in, out);
                } else {
                    shiftCryption.fileDecryption(key, in, out);
                }
            } else {
                if (alg.equals("unicode")) {
                    cryption.fileEncryption(key, in, out);
                } else {
                    shiftCryption.fileEncryption(key, in, out);
                }
            }
        }
    }
}
