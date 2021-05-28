package cinema;

import java.util.Scanner;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);

    public static void show(char[][] array) {
        System.out.println("Cinema:");
        for (char[] chars : array) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buy(char[][] array) {
        boolean input = true;
        while (input) {
            System.out.println();
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row >= array.length || seat >= array[0].length) {
                System.out.printf("%nWrong input!%n");
            } else if (array[row][seat] == 'B') {
                    System.out.printf("%nThat ticket has already been purchased!%n");
            } else {
                array[row][seat] = 'B';
                int price = 10;
                if ((array[0].length - 1) * (array.length - 1) > 60 && row > ((array.length - 1) / 2)) {
                    price = 8;
                }
                System.out.printf("Ticket price: $%d%n", price);
                input = false;
            }
        }
    }

    public static void stat(char[][] array) {
        int numOfRow = array.length - 1;
        int numOfSeat = array[0].length - 1;
        int numberOf8 = 0;
        int numberOf10 = 0;
        float percentage = 0;
        int income  = 0;
        int totalIncome = 0;
        int totalSeat = numOfSeat * numOfRow;
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[0].length; j++) {
                if (array[i][j] == 'B' && totalSeat > 60
                        && i > (numOfRow / 2)) {
                    numberOf8++;
                } else if (array[i][j] == 'B') {
                    numberOf10++;
                }
            }
        }
        if (totalSeat > 60) {
            totalIncome = (numOfRow / 2 * 10 + (numOfRow - numOfRow / 2) * 8) * numOfSeat;
        } else {
            totalIncome = totalSeat * 10;
        }
        System.out.printf("%nNumber of purchased tickets: %d%n", numberOf8 + numberOf10);
        System.out.printf("Percentage: %.2f%%%n", (float)(numberOf8 + numberOf10) / totalSeat * 100);
        System.out.printf("Current income: $%d%n", numberOf8 * 8 + numberOf10 * 10);
        System.out.printf("Total income: $%d%n", totalIncome);
//        Number of purchased tickets: 0
//        Percentage: 0.00%
//        Current income: $0
//        Total income: $360
    }

    public static void menu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        // Write your code here
        int rowAscii = 48;
        int seatAscii = 48;
        System.out.println("Enter the number of rows:");
        int numOfRow = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numOfSeat = scanner.nextInt();
        char[][] cinema = new char[numOfRow + 1][numOfSeat + 1];
        for (int indexRow = 0; indexRow <= numOfRow; indexRow++) {
            cinema[indexRow][0] = (char)rowAscii++;
        }
        for (int indexSeat = 0; indexSeat <= numOfSeat; indexSeat++) {
            cinema[0][indexSeat] = (char)seatAscii++;
        }
        cinema[0][0] = 32;
        for (int i = 1; i <= numOfRow; i++) {
            for (int j = 1; j <= numOfSeat; j++) {
                cinema[i][j] = 'S';
            }
        }
        menu();
        int n = scanner.nextInt();
        while (n != 0) {
            switch (n) {
                case 1:
                    show(cinema);
                    break;
                case 2:
                    buy(cinema);
                    break;
                case 3:
                    stat(cinema);
                    break;
                default:
                    break;
            }
            menu();
            n = scanner.nextInt();
        }
    }
}