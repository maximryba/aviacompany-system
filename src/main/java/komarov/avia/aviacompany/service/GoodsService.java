package komarov.avia.aviacompany.service;

import java.util.List;
import java.util.Optional;

import komarov.avia.aviacompany.entity.Goods;

public interface GoodsService {

	List<Goods> findAll();
	
	Optional<Goods> findById(int id);
	
	int create(Goods good);
	
	void update (Goods good, int id);
	
	void delete(int id);
	
}
