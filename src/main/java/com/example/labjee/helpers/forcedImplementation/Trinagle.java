package com.example.labjee.helpers.forcedImplementation;

import com.example.labjee.helpers.forcedImplementation.Shape;
import java.lang.Math; 


public class Trinagle implements Shape {
    float side;

    public Triangle(float side) {
        this.side = side;
    }

    public float calcPerimeter() {
        return side * 3;
    }

    public float calcArea() {
        return side * side * Math.sqrt(3) / 4;
    }
}