import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(String.valueOf(str.charAt(i)).repeat(2));
        }
        System.out.println(stringBuilder.toString());
    }
}