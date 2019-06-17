package guru.springframework.transform.unitofmeasure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.UnitOfMeasure;

@Component
public class UnitOfMeasureBeanTransformer implements Converter<UnitOfMeasureBean, UnitOfMeasure>{
    
    @Override
    public UnitOfMeasure convert(UnitOfMeasureBean bean) {
    	
        if (null == bean) {
            return null;
        }
        
        UnitOfMeasure entity = new UnitOfMeasure();
        entity.setId(bean.getId());
        entity.setUom(bean.getUom());
        return entity;
        
    }

}
