package com.politecnico.edu.inventario.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Datos {
    private int id;
    private int stock;
}
