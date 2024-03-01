package com.example.labjee.primarykeys;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
public class MovieRatingPK implements Serializable {
    @Getter
    @Setter
    private String user;
    
    @Getter
    @Setter
    private int movie;
}