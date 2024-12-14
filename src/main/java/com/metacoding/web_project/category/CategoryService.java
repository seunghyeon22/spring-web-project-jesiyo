package com.metacoding.web_project.category;

import com.metacoding.web_project._core.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse.CategoryDTO> findAllCategory(Integer categoryId) {
        List<Category> categoryList = categoryRepository.findAllCategory();

        List <CategoryResponse.CategoryDTO> categoryDTOList = new ArrayList<>();

        if (categoryId != null) {
            Integer count = 0;
            for (Category category : categoryList) {
                count++;
                if (categoryId.equals(count)) {
                    categoryDTOList.add(new CategoryResponse.CategoryDTO(category, true));
                } else {
                    categoryDTOList.add(new CategoryResponse.CategoryDTO(category, false));
                }
            }
            return categoryDTOList;
        }

        for (Category category : categoryList) {
            categoryDTOList.add(new CategoryResponse.CategoryDTO(category, false));
        }

        return categoryDTOList;
    }

    @Transactional
    public void insertCategory(CategoryRequest.CategoryDTO dto) {

        String url = FileUtil.fileSave(dto.getData());
        categoryRepository.insertCategory(dto.toEntity(url));
    }
}
