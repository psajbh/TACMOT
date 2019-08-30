package guru.springframework.transform.note;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.NotesBean;
import guru.springframework.model.Notes;
import lombok.Synchronized;

@Component
public class NotesTransformer implements Converter<Notes, NotesBean>{
    
    @Synchronized
    @Nullable
    @Override
    public NotesBean convert(Notes source) {
        if (null == source) {
            return null;
        }
        
        final NotesBean bean = new NotesBean();
        bean.setId(source.getId());
        bean.setRecipeNotes(source.getRecipeNotes());
        return bean;
    }

}
