package com.example.labjee.helpers.visitor;

import com.example.labjee.interfaces.MovieRelationship;

public interface ServiceElement {
    public abstract void accept(Visitor visitor);

    public abstract void accept(Visitor visitor, MovieRelationship relationship);
}
