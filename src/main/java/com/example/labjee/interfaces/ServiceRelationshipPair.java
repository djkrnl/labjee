package com.example.labjee.interfaces;

import com.example.labjee.helpers.visitor.ServiceElement;
import lombok.Getter;

public class ServiceRelationshipPair {
    @Getter
    ServiceElement element;

    @Getter
    MovieRelationship relationship;

    public ServiceRelationshipPair(ServiceElement element, MovieRelationship relationship) {
        this.element = element;
        this.relationship = relationship;
    }
}
