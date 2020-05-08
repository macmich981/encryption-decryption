import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int cnt = 0;
        char temp = str.charAt(0);

        for (int i = 0; i < str.length(); i++) {
            if (temp == str.charAt(i)) {
                cnt++;
            }
            if (i + 1 == str.length() || temp != str.charAt(i + 1)) {
                System.out.print(temp + "" + cnt);
                cnt = 0;
                if (i + 1 < str.length()) {
                    temp = str.charAt(i + 1);
                }
            }
        }
    }
}
