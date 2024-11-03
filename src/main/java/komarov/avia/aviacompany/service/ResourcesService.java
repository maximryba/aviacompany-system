package komarov.avia.aviacompany.service;

import java.util.List;
import java.util.Optional;

import komarov.avia.aviacompany.entity.Resource;

public interface ResourcesService {

	List<Resource> findAll();
	
	Optional<Resource> findById(int id);
	
	int create(Resource resource);
	
	void update(Resource resource, int id);
	
	void delete(int id);
}