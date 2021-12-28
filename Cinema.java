package cinema;

import java.util.Locale;
import java.util.Scanner;

public class Cinema {
    public static int qttSold;
    public static int numSeats;
    public static int numRows;
    public static int currentIncome;
    public static int income;
    public static char[][] room;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numSeats = scanner.nextInt();
        room = new char[numRows][numSeats];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numSeats; j++) {
                room[i][j] = 'S';
            }
        }
        qttSold = 0;
        currentIncome = 0;
        setIncome();
        showMenu();
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1 :
                showSeats();
                break;
            case 2 :
                buyTicket();
                break;
            case 3 :
                showStatistics();
                break;
            case 0 :
                break;
        }
    }

    private static void showSeats() {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        int count = 1;
        while (count <= numSeats){
            System.out.print(" " + count);
            count++;
        }
        System.out.println();
        for (int i = 0; i < numRows; i++) {
            int line = i + 1;
            System.out.print(line + " ");
            for (int j = 0; j < numSeats; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
        showMenu();
    }

    private static void buyTicket() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        if (rowNumber > numRows || seatNumber > numSeats) {
            System.out.println();
            System.out.println("Wrong input!");
            buyTicket();
        } else {
            if (room[rowNumber - 1][seatNumber - 1] != 'B') {
                int ticketPrice;
                if (room.length * numSeats < 60) {
                    ticketPrice = 10;
                } else {
                    int firstHalf = numRows / 2;
                    int premiumTicket = 10;
                    int budgetTicket = 8;
                    ticketPrice = (rowNumber <= firstHalf) ? premiumTicket : budgetTicket;
                }
                System.out.println();
                System.out.printf("Ticket price: $%d", ticketPrice);
                System.out.println();
                room[rowNumber - 1][seatNumber - 1] = 'B';
                qttSold++;
                currentIncome += ticketPrice;
                showMenu();
            } else {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                buyTicket();
            }

        }
    }

    private static void showStatistics() {
        System.out.println();
        float percentageSoldTickets = (qttSold == 0) ? 0.00f : (float) qttSold / (numRows * numSeats) * 100;
        System.out.println("Number of purchased tickets: " + qttSold);
        System.out.printf("Percentage: %.2f%%", percentageSoldTickets);
        System.out.println();
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + income);
        showMenu();
    }

    private static void setIncome() {
        if (room.length * numSeats < 60) {
            income = numRows * numSeats * 10;
        } else {
            int firstHalf = room.length / 2;
            int premiumTicket = 10;
            int budgetTicket = 8;
            int secondHalf = numRows - firstHalf;
            income = firstHalf * numSeats * premiumTicket + secondHalf * numSeats * budgetTicket;
        }
    }

}