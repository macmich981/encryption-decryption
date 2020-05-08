import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        str = str.toLowerCase();
        int index = -1;

        for (int i = 0; i < str.length(); i++) {
            if (i + 1 < str.length() && i + 2 < str.length()) {
                if (str.charAt(i) == 't' && str.charAt(i + 1) == 'h' && str.charAt(i + 2) == 'e') {
                    index = i;
                    break;
                }
            }
        }
        System.out.println(index);
    }
}
