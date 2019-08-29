package guru.springframework.services;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureTransformer;


@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureTransformer unitOfMeasureTransformer;

	@Autowired
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, 
			UnitOfMeasureTransformer unitOfMeasureTransformer) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureTransformer = unitOfMeasureTransformer;
	}
	
	@Override
	public Set<UnitOfMeasureBean> listAllUoms(){
		Set<UnitOfMeasureBean> uomBeans = new HashSet<>();
		Iterable<UnitOfMeasure> uoms = unitOfMeasureRepository.findAll();
		//StreamSupport converts an Iterable to something we can stream against
		//Spliterator gives us a Java 8 stream
		uomBeans = StreamSupport.stream(uoms.spliterator(),false).map(unitOfMeasureTransformer::convert).collect(Collectors.toSet());
		return uomBeans;
	}

}
