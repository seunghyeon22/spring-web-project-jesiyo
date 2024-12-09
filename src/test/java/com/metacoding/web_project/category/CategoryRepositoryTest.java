package com.metacoding.web_project.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;


@Import(CategoryRepository.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findAllCategory_test() {
        List<Category> list = categoryRepository.findAllCategory();
        System.out.println(list.size());
    }
}
