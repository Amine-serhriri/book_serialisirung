package de.fhac.rn;


import lombok.Data;

import java.io.Serializable;

@Data
public class Author implements Serializable {
    private String Name, Vorname;
}
