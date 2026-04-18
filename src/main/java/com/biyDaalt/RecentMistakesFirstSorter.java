package com.biyDaalt;

import java.util.ArrayList;
import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer{
    @Override
    public List<Card> organize(List<Card> cards) {

        List<Card> failedLast = new ArrayList<>();
        List<Card> others = new ArrayList<>();

        for (Card card : cards) {
            if (card.getTotalAttempts() > 0 && !card.isLastAttemptCorrect()) {
                failedLast.add(card);
            } else {
                others.add(card);
            }
        }
        List<Card> result = new ArrayList<>();

        for (int i = failedLast.size() - 1; i >= 0; i--) {
            result.add(failedLast.get(i));
        }

        result.addAll(others);
        return result;
    }
}