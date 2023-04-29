package shop.mtcoding.tstory.model.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.tstory.dto.user.CheckDto;

@Mapper
public interface CategoryRespository {
    public Category findById(Integer categoryId);

	public List<Category> findAll();

	public void insert(Category category);

	public void update(Category category);

	public void delete(Category category);

    public CheckDto findByCategoryTitle(String categoryTitle, Integer userId);

    public void insertCategoryTitle(String categoryTitle, Integer userId);
}
