package komarov.avia.aviacompany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import komarov.avia.aviacompany.entity.Resource;
import komarov.avia.aviacompany.entity.ResourceType;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ResourcesRepositoryImpl implements ResourcesRepository {

	
	private final JdbcTemplate jdbcTemplate;
	
	private final RowMapper<Resource> resourcesRowMapper = (rs, rowNum) -> {
		Resource resources = new Resource();
		resources.setId(rs.getInt("id"));
		resources.setResourceType(ResourceType.valueOf(rs.getString("resource_type")));
		resources.setCost(rs.getBigDecimal("cost"));
		resources.setFlightId(rs.getInt("flight_id"));
        return resources;
    };
	
	@Override
	public List<Resource> findAll() {
		String sql = "select * from resources";
		return this.jdbcTemplate.query(sql, this.resourcesRowMapper);
	}

	@Override
	public Optional<Resource> findById(int id) {
		String sql = "select * from resources where id = ?";
		return this.jdbcTemplate.query(sql, this.resourcesRowMapper, id).stream().findFirst();
	}

	@Override
	public int create(Resource resource) {
		String sql = "insert into resources (resource_type, cost, flight_id) values (?, ?, ?)";
		return this.jdbcTemplate.update(sql, resource.getResourceType().name(),
				resource.getCost(),
				resource.getFlightId());
	}

	@Override
	public void update(Resource resource, int id) {
		String sql = "update resources set cost = ? where id = ?";
		this.jdbcTemplate.update(sql,
				resource.getCost(), id);
	}

	@Override
	public void delete(int id) {
		String sql = "delete from resources where id = ?";
		this.jdbcTemplate.update(sql, id);
		
	}

}
