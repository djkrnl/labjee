package com.example.labjee.seeders;

import java.util.List;

// Tydzień 5 - wzorzec Iterator - implementacja iteratora listy seederów, iterator wykorzystuje przekazaną listę seederów do przechodzenia po niej z użyciem pomocniczej zmiennej pozycji
public class SeederSetIteratorImpl implements SeederSetIterator {
    private List<Seeder> seederList;

    private int pos;

    public SeederSetIteratorImpl(List<Seeder> seederList) {
        this.seederList = seederList;
    }

    @Override
    public Seeder next() {
        Seeder seeder = this.seederList.get(this.pos);
        this.pos++;

        return seeder;
    }

    @Override
    public boolean last() {
        return this.pos >= this.seederList.size();
    }
}
// Tydzień 5 - wzorzec Iterator - koniec