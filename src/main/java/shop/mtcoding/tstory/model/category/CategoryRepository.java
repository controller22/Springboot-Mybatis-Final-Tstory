package shop.mtcoding.tstory.model.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.tstory.dto.user.CheckDto;

@Mapper
public interface CategoryRepository {
    public Category findById(Integer categoryId);

	public List<Category> findAll();

	public void insert(Category category);

	public void update(Category category);

	public void delete(Category category);

    public void insertCategoryTitle(@Param("categoryTitle") String categoryTitle,@Param("userId") Integer userId);

    public CheckDto findByCategoryTitle(@Param("categoryTitle") String categoryTitle, @Param("userId") Integer userId);
}
