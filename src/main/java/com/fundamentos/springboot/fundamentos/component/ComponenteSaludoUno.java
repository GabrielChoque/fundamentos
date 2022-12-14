package com.fundamentos.springboot.fundamentos.component;

public class ComponenteSaludoUno implements SaludarUno{
    @Override
    public int SaludarGaboUno(int numero) {
          return (numero*(numero +1))/2;

    }
}
