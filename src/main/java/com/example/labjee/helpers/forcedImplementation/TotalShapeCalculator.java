package com.example.labjee.helpers.forcedImplementation;

import com.example.labjee.helpers.forcedImplementation.Shape;
import java.util.ArrayList;

public class TotalShapeCalculator {
    ArrayList<Shape> list = new ArrayList();

    public void pushToList(Shape shape) {
        this.list.push(shape);
    }
    
    public String getTotalData() {
        float perimeter = 0;
        float area = 0;
        for (Shape shape : this.list) {
            perimeter += shape.calcPerimeter();
            area += shape.calcArea();
        }
        return "Perimeter = " + Integer.toString(perimeter) + "; Area = " + Integer.toString(area);
    }
}
