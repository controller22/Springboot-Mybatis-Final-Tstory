package shop.mtcoding.tstory.model.visit;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitRepository {
    public Visit findById(Integer visitId);

	public List<Visit> findAll();

	public void insert(Visit visit);

	public void update(Visit visit);

	public void delete(Visit visit);
}
