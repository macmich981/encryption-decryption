//put imports you need here

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();
        int age = scanner.nextInt();
        String stage = scanner.next();
        int exp = scanner.nextInt();
        String cuisine = scanner.next();

        System.out.println("The form for " + name + " is completed. We will contact you if we need a chef that cooks " + cuisine + " dishes.");
    }
}