package guru.springframework.transform.note;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.NotesBean;
import guru.springframework.model.Notes;
import lombok.Synchronized;

@Component
public class NotesBeanTransformer implements Converter<NotesBean, Notes>{
   
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesBean bean) {
        if (null == bean) {
            return null;
        }
        
        Notes entity = new Notes();
        entity.setId(bean.getId());
        entity.setRecipeNotes(bean.getRecipeNotes());
        return entity;
        
    }

}
