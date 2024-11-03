package komarov.avia.aviacompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import komarov.avia.aviacompany.entity.Resource;
import komarov.avia.aviacompany.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {
	
	private final ResourcesRepository resourcesRepository;
	
	@Override
	public List<Resource> findAll() {
		return this.resourcesRepository.findAll();
	}

	@Override
	public Optional<Resource> findById(int id) {
		return this.resourcesRepository.findById(id);
	}

	@Transactional
	@Override
	public int create(Resource resource) {
		return this.resourcesRepository.create(resource);
	}

	@Transactional
	@Override
	public void update(Resource resource, int id) {
		this.resourcesRepository.update(resource, id);
	}

	@Transactional
	@Override
	public void delete(int id) {
		this.resourcesRepository.delete(id);
	}

}
