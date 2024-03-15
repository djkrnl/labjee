package com.example.labjee.helpers;

import java.time.Year;
import java.util.Date;
// Tydzień 3 - wzorzec Adapter - Implementacja

public class BirthDateAdapter extends Date implements IBirthDateAdapter {

    Date date;

    public BirthDateAdapter(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public String getBirthWhenDateText() {
        String dateText = toString();
        return transformDateText(dateText, "when");
    }

    @Override
    public String getBirthDateAgoText() {
        String dateText = toString();
        return transformDateText(dateText, "how_long_ago");
    }

    private String transformDateText(String text, String mode) {
        return switch (mode) {
            case "when" -> "Born on " + text;
            case "how_long_ago" -> {
                int currentYear = Year.now().getValue();
                int birthYear = Integer.parseInt(text.substring(0, 4));
                int years = currentYear - birthYear;
                yield "Born " + years + " years ago";
            }
            default -> text;
        };
    }
}
