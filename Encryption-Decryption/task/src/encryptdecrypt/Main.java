package encryptdecrypt;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

interface EncryptionAlghorithm {
    String encryption(String str, int n);
    String decryption(String str, int n);
}

class ShiftEncryption implements EncryptionAlghorithm {
    private static final List<Character> LOWER_ALPHABET =
            Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    private static final List<Character> UPPER_ALPHABET =
            Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    @Override
    public String encryption(String str, int n) {
        List<Character> alphabet = Arrays.asList(new Character[LOWER_ALPHABET.size()]);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                Collections.copy(alphabet, UPPER_ALPHABET);
            } else {
                Collections.copy(alphabet, LOWER_ALPHABET);
            }
            int index = alphabet.indexOf(str.charAt(i));
            if (str.charAt(i) == ' ') {
                stringBuilder.append(' ');
            } else if (!Character.isLetter(str.charAt(i))) {
                stringBuilder.append(str.charAt(i));
            } else if (index + n > alphabet.size()) {
                int temp = alphabet.size() - index;
                temp = n - temp;
                stringBuilder.append(alphabet.get(temp));
            } else {
                stringBuilder.append(alphabet.get(index + n));
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String decryption(String str, int n) {
        List<Character> alphabet = Arrays.asList(new Character[LOWER_ALPHABET.size()]);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                Collections.copy(alphabet, UPPER_ALPHABET);
            } else {
                Collections.copy(alphabet, LOWER_ALPHABET);
            }
            int index = alphabet.indexOf(str.charAt(i));
            if (str.charAt(i) == ' ') {
                stringBuilder.append(' ');
            } else if (!Character.isLetter(str.charAt(i))) {
                stringBuilder.append(str.charAt(i));
            } else if (index - n < 0) {
                int temp = n - index;
                temp = alphabet.size() - temp;
                stringBuilder.append(alphabet.get(temp));
            } else {
                stringBuilder.append(alphabet.get(index - n));
            }
        }
        return stringBuilder.toString();
    }
}

class UnicodeEncryption implements EncryptionAlghorithm {

    @Override
    public String encryption(String str, int n) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = (char) (str.charAt(i) + n);
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    @Override
    public String decryption(String str, int n) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = (char) (str.charAt(i) - n);
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}

class Context {
    private final EncryptionAlghorithm alghorithm;

    public Context(EncryptionAlghorithm alghorithm) {
        this.alghorithm = alghorithm;
    }

    public String code(String str, int n) {
        return alghorithm.encryption(str, n);
    }

    public String decode(String str, int n) {
        return alghorithm.decryption(str, n);
    }
}

public class Main {

    private static void save(String filename, String encFile) {
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println(encFile);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static String load(String filename) {
        String str = "";
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                str = scanner.nextLine();
            }
            return str;
        } catch (Exception e) {
            System.out.println("Error");
        }
        return str;
    }

    public static void main(String[] args) {
        String action = "enc";
        String str = "";
        int key = 0;
        String filenameOut = "";
        String filenameIn = "";
        String alg = "shift";
        Context context = new Context(new ShiftEncryption());

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    action = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    str = args[i + 1];
                    break;
                case "-out":
                    filenameOut = args[i + 1];
                    break;
                case "-in":
                    filenameIn = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
            }
        }

        switch (action) {
            case "enc":
                if (!filenameIn.isEmpty()) {
                    str = load(filenameIn);
                }
                if (alg.equals("unicode")) {
                    context = new Context(new UnicodeEncryption());
                }
                String encWord = context.code(str, key);
                if (filenameOut.isEmpty()) {
                    System.out.println(encWord);
                } else {
                    save(filenameOut, encWord);
                }
                break;
            case "dec":
                if (!filenameIn.isEmpty()) {
                    str = load(filenameIn);
                }
                if (alg.equals("unicode")) {
                    context = new Context(new UnicodeEncryption());
                }
                String decWord = context.decode(str, key);
                if (filenameOut.isEmpty()) {
                    System.out.println(decWord);
                } else {
                    save(filenameOut, decWord);
                }
                break;
        }
    }
}
