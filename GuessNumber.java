import java.util.Scanner;
import java.util.Random;

public class GuessNumber {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bestScore = Integer.MAX_VALUE;

        System.out.println("=== Угадай число ===");

        boolean playAgain = true;
        while (playAgain) {
            int max = chooseDifficulty(scanner);
            int secret = RANDOM.nextInt(max) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("Я загадал число от 1 до " + max + ". Попробуй угадать!");

            while (!guessed) {
                int guess = readNumber(scanner, "Твой вариант: ");
                attempts++;

                if (guess < secret) {
                    System.out.println("Больше ⬆");
                } else if (guess > secret) {
                    System.out.println("Меньше ⬇");
                } else {
                    guessed = true;
                    System.out.println("Угадал за " + attempts + " попыток! 🎉");
                }
            }

            if (attempts < bestScore) {
                bestScore = attempts;
                System.out.println("Новый рекорд: " + bestScore);
            } else {
                System.out.println("Твой рекорд: " + bestScore);
            }

            System.out.print("Сыграем ещё? (д/н): ");
            String answer = scanner.next().trim().toLowerCase();
            playAgain = answer.equals("д") || answer.equals("y") || answer.equals("да");
        }

        System.out.println("Спасибо за игру!");
        scanner.close();
    }

    private static int chooseDifficulty(Scanner scanner) {
        System.out.println("Выбери сложность:");
        System.out.println("1 - Лёгкая (1-10)");
        System.out.println("2 - Средняя (1-100)");
        System.out.println("3 - Сложная (1-1000)");

        while (true) {
            int choice = readNumber(scanner, "Твой выбор: ");
            switch (choice) {
                case 1:
                    return 10;
                case 2:
                    return 100;
                case 3:
                    return 1000;
                default:
                    System.out.println("Введи 1, 2 или 3.");
            }
        }
    }

    private static int readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Это не число, попробуй ещё раз.");
            scanner.next();
        }
    }
}