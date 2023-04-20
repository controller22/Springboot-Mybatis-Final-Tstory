package shop.mtcoding.tstory.model.love;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoveRepository {
    public Love findById(Integer loveId);

	public List<Love> findAll();

	public void insert(Love love);

	public void update(Love love);

	public void delete(Love love);
}
