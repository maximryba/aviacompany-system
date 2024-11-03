package komarov.avia.aviacompany.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import komarov.avia.aviacompany.entity.GoodsType;
import komarov.avia.aviacompany.repository.GoodTypesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodTypesServiceImpl implements GoodTypesService {
	
	private final GoodTypesRepository goodTypesRepository;

	@Override
	public List<GoodsType> findAll() {
		return this.goodTypesRepository.findAll();
	}

	@Override
	public Optional<GoodsType> findById(int id) {
		return Optional.ofNullable(this.goodTypesRepository.findById(id).orElseThrow(NoSuchElementException::new));
	}

	@Override
	public int create(GoodsType goodsType) {
		return this.goodTypesRepository.create(goodsType);
	}

	@Override
	public void update(GoodsType goodsType, int id) {
		this.goodTypesRepository.update(goodsType, id);
		
	}

	@Override
	public void delete(int id) {
	this.goodTypesRepository.delete(id);
		
	}
	
	

}
