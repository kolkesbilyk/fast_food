package com.fastfood.fastfood.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

@Named("UI")
@ApplicationScoped
public class UI {
    public void resetData(String id){
        PrimeFaces.current().resetInputs(id);
    }
}
