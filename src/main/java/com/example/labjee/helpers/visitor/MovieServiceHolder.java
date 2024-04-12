package com.example.labjee.helpers.visitor;

import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.interfaces.ServiceRelationshipPair;

import java.util.ArrayList;

public class MovieServiceHolder implements ServiceElement {
    public ArrayList<ServiceRelationshipPair> serviceRelationshipPairs = new ArrayList<>();

    @Override
    public void accept(Visitor v) {
        for (ServiceRelationshipPair pair : this.serviceRelationshipPairs) {
            pair.getElement().accept(v, pair.getRelationship());
        }
    }

    @Override
    public void accept(Visitor visitor, MovieRelationship relationship) {

    }
}
