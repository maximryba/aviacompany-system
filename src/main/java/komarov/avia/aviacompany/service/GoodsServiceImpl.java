package komarov.avia.aviacompany.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import komarov.avia.aviacompany.entity.Goods;
import komarov.avia.aviacompany.entity.GoodsType;
import komarov.avia.aviacompany.repository.GoodTypesRepository;
import komarov.avia.aviacompany.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

	private final GoodsRepository goodsRepository;
	
	private final GoodTypesRepository goodTypesRepository;
	
	@Override
	public List<Goods> findAll() {
		return this.goodsRepository.findAll();
	}

	@Override
	public Optional<Goods> findById(int id) {
		return this.goodsRepository.findById(id);
	}

	@Transactional
	@Override
	public int create(Goods good) {
		GoodsType goodsType = this.goodTypesRepository.findById(good.getGoodsType()).get();
		BigDecimal newProfit = goodsType.getProfit().multiply(BigDecimal.valueOf(good.getAmount()));
		good.setProfit(newProfit);
		return this.goodsRepository.create(good);
	}

	@Transactional
	@Override
	public void update(Goods good, int id) {
		GoodsType goodsType = this.goodTypesRepository.findById(good.getGoodsType()).get();
		BigDecimal newProfit = goodsType.getProfit().multiply(BigDecimal.valueOf(good.getAmount()));
		good.setProfit(newProfit);
		this.goodsRepository.update(good, id);
	}

	@Transactional
	@Override
	public void delete(int id) {
		this.goodsRepository.delete(id);
		
	}

}
