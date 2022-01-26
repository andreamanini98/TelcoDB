package it.polimi.telcodb.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ServiceTypeConverter implements AttributeConverter<ServiceType, String> {

    @Override
    public String convertToDatabaseColumn(ServiceType serviceType) {
        return serviceType.getType();
    }


    @Override
    public ServiceType convertToEntityAttribute(String dbType) {
        return ServiceType.fromType(dbType);
    }

}
