import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {
            for (int i = 0; i < string.length(); i++) {
                if (Character.isLetter(string.charAt(i))) {
                    stringBuilder.append(string.charAt(i));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}