package com.biyDaalt;

import java.util.List;

public class AchievementManager {
    public void check(List<Card> cards, double avgTime) {
        System.out.println("\n=== RESULT ===");
        if (avgTime < 5.0) {
            System.out.println("★ Хурдан! (avg < 5 сек)");
        }

        boolean repeat = cards.stream().anyMatch(c -> c.getTotalAttempts() > 5);
        if (repeat) {
            cards.stream()
                .filter(c -> c.getTotalAttempts() > 5)
                .forEach(c -> System.out.println("★ REPEAT: " + c.getQuestion()));
        }

        boolean confident = cards.stream().anyMatch(c -> c.getCorrectCount() >= 3);
        if (confident) {
            System.out.println("★ CONFIDENT: Сайн мэддэг боллоо");
        }

        boolean allCorrect = cards.stream()
                .allMatch(c -> c.getTotalAttempts() > 0 &&
                               c.getCorrectCount() == c.getTotalAttempts());
        if (allCorrect && !cards.isEmpty()) {
            System.out.println("★ CORRECT: Бүх картыг зөв хийлээ!");
        }
    }
}
