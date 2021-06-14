package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    static int selectedRow = 0;
    static int selectedSeat = 0;
    static List<SeatLocation> selSeats = new ArrayList<>();
    static int currentIncome = 0;
    static int totalTicket = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        while (true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int input = scanner.nextInt();

            if (input == 1) {
                printSeats(rows, seats);
            } else if (input == 2) {
                buyTicket(rows, seats);
            } else if (input == 3) {
                Statistics(rows, seats);
            } else if (input == 0) {
                break;
            } else {
                System.out.println("wrong input! Enter the correct command:");
            }
            System.out.println();
        }
    }


    public static void buyTicket(int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a row number:");
        selectedRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        selectedSeat = scanner.nextInt();
        if (selectedRow > rows || selectedSeat > seats) {
            System.out.println("Wrong Input! Please Input the correct seat:");
            buyTicket(rows, seats);
        } else {
            SeatLocation seatLoc =  new SeatLocation(selectedRow, selectedSeat);
            if (selSeats.contains(seatLoc)) {
                System.out.println("That ticket has already been purchased!");
                buyTicket(rows, seats);
            } else {
                selSeats.add(seatLoc);
                int firstHalf = (int) Math.floor(rows / 2);

                if (rows * seats <= 60 || selectedRow <= firstHalf) {
                    System.out.println("Ticket price: $10");
                    totalTicket +=1;
                    currentIncome += 10;
                } else {
                    System.out.println("Ticket price: $8");
                    totalTicket +=1;
                    currentIncome += 8;
                }
            }
        }

    }

    public static void printSeats(int rows, int seats) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1 ; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 1 ; i <= rows ; i++) {
            System.out.print(i);
            for (int j = 1; j <= seats; j++) {
                SeatLocation testSeat = new SeatLocation(i,j);
                if (selSeats.contains(testSeat)) {
                    System.out.print(" B");
                } else {
                    System.out.print(" S");
                }
            }

            System.out.println();
        }
    }

    public static void Statistics(int rows, int seats) {
        int totalProfit;
        int allSeats = rows * seats;
        if (allSeats <= 60) {
            totalProfit = allSeats * 10;
        } else {
            int firstHalf = (int) Math.floor(rows / 2);
            int secondHalf = rows - firstHalf;
            totalProfit = (firstHalf * seats * 10) + (secondHalf * seats * 8);
        }
        float ticketSold = totalTicket;
        float totalSeats = rows * seats;
        float percentage = ticketSold/totalSeats * 100;
        System.out.println();
        System.out.println("Number of purchased tickets: " + totalTicket);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalProfit);
    }

}