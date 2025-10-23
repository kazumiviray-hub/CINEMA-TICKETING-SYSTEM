/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package viray;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author kazum
 */
public class VIRAY {

    /**
     * @param args the command line arguments
     */
    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        final double TICKET_PRICE = 200.00;
        Scanner sc = new Scanner(System.in);
        
        String ProgramTitle = "Cinema Ticketing System!";
        String Separator = "=".repeat(60);
        
        System.out.println(Separator);
        System.out.println("Welcome to the " + ProgramTitle);
        System.out.println(Separator);
        
        input_loop:
        while(true) {
            int Age = -1;
            int ticketQuantity = -1;
            DayOfWeek Date = null;
            
            try{
                System.out.print("Please enter your age: ");
                Age = sc.nextInt();
                sc.nextLine();
                
                if(Age < 0) {
                    System.out.println("Age cannot be a negative number.");
                    continue input_loop;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid whole number. Starting over.");
                sc.nextLine();
                continue input_loop;
            }
            
            System.out.print("Enter day of the week: ");
            String DateStr = sc.nextLine().trim();
            String UpdatedDateStr = DateStr.toUpperCase();
            
            if (DateStr.equals("EXIT")) {
                break input_loop;
            }
            
            try {
                Date = DayOfWeek.valueOf(UpdatedDateStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid day entered. Please type a valid  day of week.");
                continue input_loop;
            }
            
            try {
                System.out.print("Enter number of tickets to be purchased: ");
                    ticketQuantity = sc.nextInt();
                    sc.nextLine();
            
                if (ticketQuantity <= 0) {
                    System.out.println("Number of tickets must be greater than zero.");
                    continue input_loop;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue input_loop;
            }
            
            double DiscountRate = 0.00;
            String DiscountName = "No Discount (0%)";
            
            if (Age < 7) {
                DiscountRate = 1.00;
                DiscountName = "Child Discount (100%)";
            } else if (Date == DayOfWeek.WEDNESDAY) {
                DiscountRate = 0.50;
                DiscountName = "Wednesday Funday Discount (50%)";
            } else if (Age >= 60) {
                DiscountRate = 0.20;
                DiscountName = "Senior Citizen (20%)";
            }
            
            double pricePerTicket = TICKET_PRICE * (1.0 - DiscountRate);
            String FirstDiscountApplied = DiscountName;
            double FirstDiscountRate = DiscountRate;
            double subTotal = pricePerTicket * ticketQuantity;
            double FinalTotal = subTotal;
            
            String SecondDiscountMessage = "No Discount (0%)";
            double SecondDiscountAmount = 0.0;
            
            if(FinalTotal > 1000.00) {
                double thousandDiscount = 0.10;
                SecondDiscountAmount = FinalTotal * thousandDiscount;
                FinalTotal -= SecondDiscountAmount;
                SecondDiscountMessage = "Larger Purchase Discount (10%)";
            }
            
            if(FinalTotal < 200.0) {
                System.out.println("\n======Transaction failed!======");
                System.out.println("Total purchase amount (" + FinalTotal + "Php) is less than 200Php.");
                System.out.println("Minimum purchase not reached. Try again.");
                continue input_loop;
            }
            
            System.out.println("\n" + Separator);
            System.out.println("TICKET RECEIPT");
            System.out.println(Separator);
            System.out.println("Customer's Age: " + Age);
            System.out.println("Day of Week: " + Date);
            System.out.println("Number of Tickets: " + ticketQuantity);
            System.out.println(Separator);
            
            System.out.println("Base Ticket Price: " + TICKET_PRICE);
            System.out.println("Applied Primary Discount: " + FirstDiscountApplied + "(" + TICKET_PRICE * DiscountRate + "Php saved per ticket)");
            System.out.println("Effective Ticket Price: " + pricePerTicket);
            System.out.println("Ticket Breakdown: " + ticketQuantity + " tickets x " + pricePerTicket + "Php = " + subTotal + "Php");
            
            if (SecondDiscountAmount > 0) {
                System.out.println(Separator);
                System.out.println("Extra Discount Applied: " + SecondDiscountMessage);
                System.out.println("Discount Amount: " + SecondDiscountAmount);
                System.out.println(Separator);
            }
            
            System.out.println("FINAL AMOUNT TO PAY: " + FinalTotal + "Php");
            System.out.println(Separator);
            
            System.out.print("Do you want to process another transaction? (yes/no): ");
            String option = sc.nextLine().toLowerCase();
            System.out.println();
            
            if (option.equals("no") || option.equals("n") || option.equals("exit")) {
                break input_loop;
            }
            
        }
        System.out.println("Thank you for using " + ProgramTitle);
        sc.close();
    }
    
}
