package com.biyDaalt;

import java.util.List;

public class WorstFirstSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards) {

        cards.sort((a, b) -> {
            int wrongA = a.getTotalAttempts() - a.getCorrectCount();
            int wrongB = b.getTotalAttempts() - b.getCorrectCount();
            return Integer.compare(wrongB, wrongA);
        });

        return cards;
    }
}