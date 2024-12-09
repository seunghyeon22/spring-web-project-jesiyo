package com.metacoding.web_project.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    // 로그인 구현 시 경로를 /admin/category 로 변경 예정
    @GetMapping("/category")
    public String category(Model model) {

        List<CategoryResponse.CategoryDTO> DTOList = categoryService.findAllCategory();

        model.addAttribute("model", DTOList);
        return "admin/category";
    }










}
