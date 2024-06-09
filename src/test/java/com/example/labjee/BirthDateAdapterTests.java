package com.example.labjee;

import com.example.labjee.helpers.BirthDateAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BirthDateAdapterTests {
    @Test
    void birthDateAdapterWithTodaysDateIsCreated() {
        BirthDateAdapter birthDateAdapter = new BirthDateAdapter(new Date());
        assertThat(birthDateAdapter).isNotNull();
    }

    @Test
    void birthDateAdapterWithNullDateIsCreated() {
        BirthDateAdapter birthDateAdapter = new BirthDateAdapter(null);
        assertThrows(NullPointerException.class, birthDateAdapter::toString);
    }

    @Test
    void birthDateAdapterReturnsDateString() {
        Date date = new Date();

        BirthDateAdapter birthDateAdapter = new BirthDateAdapter(new Date());
        String dateStringFromAdapter = birthDateAdapter.toString();

        assertThat(dateStringFromAdapter).isEqualTo(date.toString());
    }

    @Test
    void birthDateAdapterReturnsDateWhen() {
        Date date = new Date();
        BirthDateAdapter birthDateAdapter = new BirthDateAdapter(date);

        String birthDateWhen = birthDateAdapter.getBirthDateWhenText();
        assertThat(birthDateWhen).isEqualTo("Born on " + date.toString());
    }

    @Test
    void birthDateAdapterReturnsDateHowLongAgo() {
        Date date = new Date();
        BirthDateAdapter birthDateAdapter = new BirthDateAdapter(date);

        String birthDateHowLongAgo = birthDateAdapter.getBirthDateHowLongAgoText();
        assertThat(birthDateHowLongAgo).isEqualTo("Born 0 years ago");
    }
}
