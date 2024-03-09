package efs.task.syntax;

import java.util.Arrays;
import java.util.Scanner;
public class GuessNumberGame {
    private final int maxNumber;
    private final double maxTries;
    private final int numberToGuess;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            this.maxNumber = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("'" + argument + "'" + " to " +UsefulConstants.WRONG_ARGUMENT+ " - to nie liczba");
            throw new IllegalArgumentException(e);
        }

        if (maxNumber < 1 || maxNumber > 400) {
            System.out.println(argument + " to " +UsefulConstants.WRONG_ARGUMENT+ " - jest spoza zakresu <1," +UsefulConstants.MAX_UPPER_BOUND+ ">");
            throw new IllegalArgumentException();
        }

        this.numberToGuess = (int) (Math.log(maxNumber) / Math.log(2)) + 1;
        this.maxTries = Math.floor(Math.log((double) this.maxNumber) / Math.log(2)) + 1;
    }

    public void play() {
        int countTries = 0;
        String[] progressBar = new String[(int) this.maxTries];
        String number;
        int answer;
        Arrays.fill(progressBar, ".");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," +maxNumber+ ">");
        while (true) {
            if (countTries >= this.maxTries) {
                System.out.println(UsefulConstants.UNFORTUNATELY+ ", wyczerpałeś limit prób (" +this.maxTries+ ") do odgadnięcia liczby " +this.numberToGuess);
                break;
            }

            progressBar[countTries] = "*";
            System.out.print("Twoje próby: [");
            for (int i = 0; i < progressBar.length; i++){
                System.out.print(progressBar[i]);
            }
            System.out.println("]");

            System.out.println(UsefulConstants.GIVE_ME+ " liczbę : ");
            number = scanner.next();

            try {
                answer = Integer.parseInt(number);
            } catch (Exception e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                countTries++;
                continue;
            }

            if (answer > numberToGuess) {
                System.out.println("To " +UsefulConstants.TO_MUCH);
            } else if (answer < numberToGuess) {
                System.out.println("To " +UsefulConstants.TO_LESS);
            } else {
                System.out.println("To " +UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS+ ", " +(countTries + 1)+ " - tyle prób zajeło Ci odgadnięcie liczby " +this.numberToGuess);
                break;
            }
            countTries++;
        }
    }
}