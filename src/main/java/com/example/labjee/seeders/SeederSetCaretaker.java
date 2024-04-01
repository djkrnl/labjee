package com.example.labjee.seeders;

import java.util.ArrayDeque;
import java.util.Deque;

// Tydzień 5 - wzorzec Memento - klasa przechowująca wszystkie zapisane memento dla listy seederów - stanowi historię stanów listy seederów
public class SeederSetCaretaker {
    final Deque<SeederSetMemento> mementos = new ArrayDeque<>();

    public SeederSetMemento getMemento() {
        return mementos.pop();
    }
    public void addMemento(SeederSetMemento memento) {
        mementos.push(memento);
    }
}
// Tydzień 5 - wzorzec Memento - koniec