package com.example.labjee.helpers.command;

// Tydzień 5 - wzorzec Command - klasa kontrolera poleceń, pozwalająca na wywoływanie odpowiednich poleceń przekazanego do metody (execute()) i wycofywanie (odwracanie) działania ostatniego polecenia (undo())
public class SessionCommandControl {
    CommandBase undoCommand;

    public void execute(CommandBase command) {
        command.execute();
        undoCommand = command;
    }

    public void undo() {
        undoCommand.undo();
    }
}
// Tydzień 5 - wzorzec Command - koniec