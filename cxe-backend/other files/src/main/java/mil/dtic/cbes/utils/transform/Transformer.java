package mil.dtic.cbes.utils.transform;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.entities.IEntity;

public interface Transformer {
    
    IDto transform(IEntity entity);
    IEntity transform (IDto dto);

}
