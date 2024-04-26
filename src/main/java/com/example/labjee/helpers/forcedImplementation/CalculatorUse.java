package com.example.labjee.helpers.forcedImplementation;

import com.example.labjee.helpers.forcedImplementation.Square;
import com.example.labjee.helpers.forcedImplementation.Trinagle;
import com.example.labjee.helpers.forcedImplementation.Rectangle;

public static class CalculatorUse {
    public String useCalculator() {
        TotalShapeCalculator calculator = new TotalShapeCalculator();
        calculator.pushToList(new Square(2.3));
        calculator.pushToList(new Rectangle(2.3, 5));
        calculator.pushToList(new Triangle(7));
        calculator.pushToList(new Square(1));
        return calculator.getTotalData();
    }
}