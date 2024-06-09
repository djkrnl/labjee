package com.example.labjee;

import com.example.labjee.seeders.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SeederSetTests {
	@Test
	void emptySeederSetIsCreated() {
		SeederSet seederSet = new SeederSet();

		assertThat(seederSet).isNotNull();
	}

	@Test
	void seederSetIteratorIsReturned() {
		SeederSet seederSet = new SeederSet();
		SeederSetIterator seederSetIterator = seederSet.getIterator();
		assertThat(seederSetIterator).isInstanceOf(SeederSetIteratorImpl.class);
	}

	@Test
	void seederSetMementoIsReturned() {
		SeederSet seederSet = new SeederSet();
		SeederSetMemento seederSetMemento = seederSet.saveToMemento();
		assertThat(seederSetMemento).isInstanceOf(SeederSetMemento.class);
	}

	@Test
	void seederIsAddedToSet() {
		SeederSet seederSet = new SeederSet();
		seederSet.add(new CountrySeeder());

		SeederSetIterator seederSetIterator = seederSet.getIterator();
		Seeder seeder = seederSetIterator.next();

		assertThat(seeder).isNotNull();
	}

	@Test
	void seederIsRemovedFromSet() {
		SeederSet seederSet = new SeederSet();

		CountrySeeder countrySeeder = new CountrySeeder();
		seederSet.add(countrySeeder);
		GenreSeeder genreSeeder = new GenreSeeder();
		seederSet.add(genreSeeder);

		SeederSetIterator seederSetIterator = seederSet.getIterator();
		Seeder seeder = seederSetIterator.next();
		assertThat(seeder).isInstanceOf(CountrySeeder.class);

		seederSet.remove(countrySeeder);

		seederSetIterator = seederSet.getIterator();
		seeder = seederSetIterator.next();
		assertThat(seeder).isInstanceOf(GenreSeeder.class);
	}
}
