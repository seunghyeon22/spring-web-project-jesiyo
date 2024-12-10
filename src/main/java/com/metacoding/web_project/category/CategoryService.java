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

    public List<CategoryResponse.CategoryDTO> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAllCategory();

        List <CategoryResponse.CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDTOList.add(new CategoryResponse.CategoryDTO(category));
        }

        return categoryDTOList;
    }

    @Transactional
    public void insertCategory(CategoryRequest.CategoryDTO dto) {

        String url = FileUtil.fileSave(dto.getData());
        categoryRepository.insertCategory(dto.toEntity(url));
<<<<<<< HEAD
=======



>>>>>>> 2e50445 ([feat] 카테고리 추가 기능 구현, 더미데이터 추가)
    }
}
