package com.emrebalikci.connecthub.core.entities;

public interface BaseEntity<IdType> {

    IdType getId();

    void setId(IdType id);

}
