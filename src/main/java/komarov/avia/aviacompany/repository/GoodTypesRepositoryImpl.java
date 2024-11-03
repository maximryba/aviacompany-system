package komarov.avia.aviacompany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import komarov.avia.aviacompany.entity.GoodsType;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GoodTypesRepositoryImpl implements GoodTypesRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	private final RowMapper<GoodsType> goodsTypeRowMapper = (rs, rowNum) -> {
		GoodsType goodsType = new GoodsType();
		goodsType.setId(rs.getInt("id"));
		goodsType.setType(rs.getString("type"));
		goodsType.setProfit(rs.getBigDecimal("profit"));
        return goodsType;
    };

	@Override
	public List<GoodsType> findAll() {
		String sql = "select * from goods_types";
		return this.jdbcTemplate.query(sql, goodsTypeRowMapper);
	}

	@Override
	public Optional<GoodsType> findById(int id) {
		String sql = "SELECT * FROM goods_types WHERE id = ?";
        return jdbcTemplate.query(sql, goodsTypeRowMapper, id).stream().findFirst();
	}

	@Override
	public int create(GoodsType goodsType) {
		String sql = "insert into goods_types (type, profit) values (?, ?)";
		return this.jdbcTemplate.update(sql, goodsType.getType(), goodsType.getProfit());
	}

	@Override
	public void update(GoodsType goodsType, int id) {
		String sql = "update goods_types set type = ?, profit = ? where id = ?";
		this.jdbcTemplate.update(sql, goodsType.getType(), goodsType.getProfit(), id);
	}

	@Override
	public void delete(int id) {
		String sql = "delete from goods_types where id = ?";
		this.jdbcTemplate.update(sql, id);
	}
    
    
    
    

}
