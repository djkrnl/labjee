package com.example.labjee.helpers;

import java.time.Year;
import java.util.Calendar;
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
    public String getBirthDateWhenText() {
        return transformDateText("when");
    }

    @Override
    public String getBirthDateHowLongAgoText() {
        return transformDateText("how_long_ago");
    }

    private String transformDateText(String mode) {
        return switch (mode) {
            case "when" -> "Born on " + toString();
            case "how_long_ago" -> {
                int currentYear = Year.now().getValue();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int birthYear = calendar.get(Calendar.YEAR);

                int years = currentYear - birthYear;
                yield "Born " + years + " years ago";
            }
            default -> toString();
        };
    }
}
