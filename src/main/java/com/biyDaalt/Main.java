package com.biyDaalt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("****FLASHCARD****");

        for (String arg : args) {
            if (arg.equals("--help")) {
                printHelp();
                return;
            }
        }

        if (args.length == 0) {
            System.out.println("File: ");
            return;
        }

        String fileName = args[0];
        int reps = 1;
        boolean invert = false;
        String order = "random";

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("--repetitions") && i + 1 < args.length) reps = Integer.parseInt(args[++i]);
            if (args[i].equals("--invertCards")) invert = true;
            if (args[i].equals("--order") && i + 1 < args.length) order = args[++i];
        }
        List<Card> cards = loadCards(fileName);
        if (cards.isEmpty()) {
            System.out.println("File is empty.");
            return;
        }

        play(cards, reps, invert, order);
    }

    private static List<Card> loadCards(String fileName) {
        List<Card> cards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) cards.add(new Card(parts[0].trim(), parts[1].trim()));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return cards;
    }

    private static void play(List<Card> cards, int reps, boolean invert, String order) {
        Scanner sc = new Scanner(System.in);
        CardOrganizer organizer;

        switch (order) {
            case "random":
                organizer = new RandomSorter();
                break;
            case "recent-mistakes-first":
                organizer = new RecentMistakesFirstSorter();
                break;
            case "worst-first":
                organizer = new WorstFirstSorter();
                break;
            default:
                System.out.println("Unknown order: " + order);
                return;
        }

        while (true) {
            long startTime = System.currentTimeMillis();
            int totalQuestions = 0;

            cards = organizer.organize(cards);
            for (Card card : cards) {
                String q = invert ? card.getAnswer() : card.getQuestion();
                String a = invert ? card.getQuestion() : card.getAnswer();

                System.out.print("Question: " + q + " -> ");
                String userAns = sc.nextLine();
                totalQuestions++;

                if (userAns.equalsIgnoreCase(a)) {
                    System.out.println("Correct!");
                    card.recordResult(true);
                } else {
                    System.out.println("Incorrect! Correct answer: " + a);
                    card.recordResult(false);
                }
            }
            long endTime = System.currentTimeMillis();
            double avgTime = ((endTime - startTime) / 1000.0) / totalQuestions;

            new AchievementManager().check(cards, avgTime);


            System.out.println("Type '-1' to stop or press Enter to continue");
            String input = sc.nextLine();
            if (input.equals("-1")) {
                break;
            }
            System.out.println("\n Next round starting...\n");
        }
    }

    private static void printHelp() {
        System.out.println("Ашиглах заавар: flashcard <cards-file> [options]");
        System.out.println("--help                Тусламж харуулах");
        System.out.println("--order <order>       Дараалал (random, worst-first, recent-mistakes-first)");
        System.out.println("--repetitions <num>   Зөв хариулах оролдлогын тоо");
        System.out.println("--invertCards         Асуулт, хариултыг солих");
    }
}
