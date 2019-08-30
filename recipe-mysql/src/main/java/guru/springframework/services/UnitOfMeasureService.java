package guru.springframework.services;

import java.util.List;
import java.util.Set;

import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.UnitOfMeasure;

public interface UnitOfMeasureService {
	Set<UnitOfMeasureBean> listAllUoms();
}
