package com.example.labjee.primarykeys;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
public class MovieActorPrimaryKey implements Serializable {
    @Getter
    @Setter
    private int movie;
    
    @Getter
    @Setter
    private int actor;
}