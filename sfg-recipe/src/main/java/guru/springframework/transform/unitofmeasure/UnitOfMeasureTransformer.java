package guru.springframework.transform.unitofmeasure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.UnitOfMeasure;

@Component
public class UnitOfMeasureTransformer implements Converter<UnitOfMeasure, UnitOfMeasureBean>{
    
    public UnitOfMeasureBean convert(UnitOfMeasure entity) {
        
        if (null == entity) {
            return null;
        }
        
        UnitOfMeasureBean bean = new UnitOfMeasureBean();
        bean.setId(entity.getId());
        bean.setUom(entity.getUom());
        return bean;
    }

}
