package com.example.labjee.helpers.forcedImplementation;

import com.example.labjee.helpers.forcedImplementation.Man;
import java.util.ArrayList;

public class Express {
    ArrayList<Man> list = new ArrayList();

    public void pushToList(Man man) {
        this.list.push(man);
    }
    
    public String serveQueue() {
        list.sort((man1, man2) -> man1.importance > man2.importance);
        String coffeesServed = "";
        for (Man man : list) {
            if (man.importance > 0) {
                coffeesServed.concat(man.getCoffee());
            }
        }
        return coffeesServed;
    }
}
