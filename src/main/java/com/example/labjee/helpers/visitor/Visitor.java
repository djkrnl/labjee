package com.example.labjee.helpers.visitor;

import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.services.*;

public interface Visitor {
    public void visit(MovieActorService element, MovieRelationship relationship);
    public void visit(MovieCountryService element, MovieRelationship relationship);
    public void visit(MovieDirectorService element, MovieRelationship relationship);
    public void visit(MovieGenreService element, MovieRelationship relationship);
    public void visit(MovieWriterService element, MovieRelationship relationship);

}
