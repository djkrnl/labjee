package com.example.labjee.helpers.visitor;

import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.services.*;

public class ServiceVisitor implements Visitor {
    @Override
    public void visit(MovieActorService element, MovieRelationship relationship) {
        element.deleteLink(relationship);
    }

    @Override
    public void visit(MovieCountryService element, MovieRelationship relationship) {

    }

    @Override
    public void visit(MovieDirectorService element, MovieRelationship relationship) {

    }

    @Override
    public void visit(MovieGenreService element, MovieRelationship relationship) {

    }

    @Override
    public void visit(MovieWriterService element, MovieRelationship relationship) {

    }
}
