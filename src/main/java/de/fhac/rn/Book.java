package de.fhac.rn;

import lombok.Data;

import java.io.Serializable;

@Data
public  class Book extends Author implements Serializable {
    private String ISBN, Titel;
    private Author A;
}
