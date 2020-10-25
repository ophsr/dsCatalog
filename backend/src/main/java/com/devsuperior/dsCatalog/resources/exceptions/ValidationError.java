package com.devsuperior.dsCatalog.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError  extends StandardErro{

    private static final long serialVersionUID = 1L;
    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }
    
    public void addErro(String name, String message){
        errors.add(new FieldMessage(name,message));
    }
}
