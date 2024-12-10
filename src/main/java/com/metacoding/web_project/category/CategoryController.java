package com.metacoding.web_project.category;

import com.metacoding.web_project._core.CommonResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("/category/insert")
    public String categoryInsert(CategoryRequest.CategoryDTO dto) {
        categoryService.insertCategory(dto);

        return "redirect:/category";
    }


    // 일단 임시
    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }

    @ResponseBody
    @GetMapping("/api/v1/category")
    public ResponseEntity<?> category() {
         List<CategoryResponse.CategoryDTO> dto = categoryService.findAllCategory();
         CommonResp<List<CategoryResponse.CategoryDTO>> resp = CommonResp.success(dto);
         return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
