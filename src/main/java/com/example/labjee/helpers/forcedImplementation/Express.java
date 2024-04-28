package com.example.labjee.helpers.forcedImplementation;


import java.util.ArrayList;

public class Express {
    ArrayList<Man> list = new ArrayList<>();

    public void pushToList(Man man) {
        this.list.add(man);
    }
    
    public String serveQueue() {
        list.sort((man1, man2) -> {
            if (man1.importance > man2.importance)
                return 1;
            else if (man1.importance == man2.importance)
                return 0;
            return -1;
        });
        String coffeesServed = "";
        for (Man man : list) {
            if (man.importance > 0) {
                coffeesServed = coffeesServed.concat(man.getCoffee());
            }
        }
        return coffeesServed;
    }
}
