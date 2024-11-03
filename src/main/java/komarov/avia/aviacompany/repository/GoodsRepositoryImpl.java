package komarov.avia.aviacompany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import komarov.avia.aviacompany.entity.Goods;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GoodsRepositoryImpl implements GoodsRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	private final RowMapper<Goods> goodsRowMapper = (rs, rowNum) -> {
		Goods goods = new Goods();
		goods.setId(rs.getInt("id"));
		goods.setGoodsType(rs.getInt("goods_type"));
		goods.setAmount(rs.getInt("amount"));
		goods.setProfit(rs.getBigDecimal("profit"));
		goods.setFlightId(rs.getInt("flight_id"));
        return goods;
    };
	
	@Override
	public List<Goods> findAll() {
		String sql = "select * from goods";
		return this.jdbcTemplate.query(sql, this.goodsRowMapper);
	}

	@Override
	public Optional<Goods> findById(int id) {
		String sql = "SELECT * FROM goods WHERE id = ?";
        return jdbcTemplate.query(sql, this.goodsRowMapper, id).stream().findFirst();
	}

	@Override
	public int create(Goods good) {
		String sql = "insert into goods (goods_type, flight_id, amount, profit) VALUES(?, ?, ?, ?)";
		return this.jdbcTemplate.update(sql, good.getGoodsType(),
				good.getFlightId(), good.getAmount(), good.getProfit());
	}

	@Override
	public void update(Goods good, int id) {
		String sql = "update goods set goods_type = ?, flight_id = ?, amount = ?, profit = ? where id = ?";
		this.jdbcTemplate.update(sql, good.getGoodsType(),
				good.getFlightId(),
				good.getAmount(),
				good.getProfit(),
				id);
		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from goods where id = ?";
		this.jdbcTemplate.update(sql, id);
		
	}

}
