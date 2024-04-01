package com.example.labjee.helpers.command;

// Tydzień 5 - wzorzec Command - polecenie wywołujące zmianę rozmiaru czcionki na zwykły z użyciem obiektu Font (wstrzykiwanego w konstruktorze)
public class NormalFontCommand implements CommandBase {
    private Font font;

    public NormalFontCommand(Font font) {
        this.font = font;
    }

    @Override
    public void execute() {
        font.normal();
    }

    @Override
    public void undo() {
        font.large();
    }
}
// Tydzień 5 - wzorzec Command - koniec