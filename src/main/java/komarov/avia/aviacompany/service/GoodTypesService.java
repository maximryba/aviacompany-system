package komarov.avia.aviacompany.service;

import java.util.List;
import java.util.Optional;

import komarov.avia.aviacompany.entity.GoodsType;

public interface GoodTypesService {
	
	List<GoodsType> findAll();
	
	Optional<GoodsType> findById(int id);
	
	int create(GoodsType goodsType);
	
	void update(GoodsType goodsType, int id);
	
	void delete(int id);
	
	
	
}
