import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.next();
        Set<Integer> set = new HashSet<>();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str1);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.indexOf(str2, i) != -1) {
                set.add(stringBuffer.indexOf(str2, i));
            }
        }
        System.out.println(set.size());
    }
}
