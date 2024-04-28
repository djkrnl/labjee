package com.example.labjee.helpers.forcedImplementation;

import java.util.ArrayList;

public class TotalShapeCalculator {
    ArrayList<Shape> list = new ArrayList();

    public void pushToList(Shape shape) {
        this.list.add(shape);
    }
    
    public String getTotalData() {
        double perimeter = 0;
        double area = 0;
        for (Shape shape : this.list) {
            perimeter += shape.calcPerimeter();
            area += shape.calcArea();
        }
        return "Perimeter = " + perimeter + "; Area = " + area;
    }
}
