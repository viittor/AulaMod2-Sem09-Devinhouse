package br.com.viittor.springdata.model;

import javax.persistence.AttributeConverter;

//@Converter(autoApply = true)
public class YesNoBooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean atributo) {
        return atributo ? "S" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String valorDoBanco) {
        return valorDoBanco.equals("S");
    }
}