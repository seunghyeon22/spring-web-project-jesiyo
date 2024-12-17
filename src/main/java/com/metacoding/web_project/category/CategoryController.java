package com.metacoding.web_project.category;

import com.metacoding.web_project._core.CommonResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/admin/category")
    public String category(Model model) {

        List<CategoryResponse.CategoryDTO> DTOList = categoryService.findAllCategory(null);

        model.addAttribute("model", DTOList);
        return "admin/category";
    }

    @PostMapping("/admin/category/insert")
    public String categoryInsert(CategoryRequest.CategoryDTO dto) {
        categoryService.insertCategory(dto);

        return "redirect:/admin/category";
    }

    @ResponseBody
    @GetMapping("/api/v1/category")
    public ResponseEntity<?> category() {
         List<CategoryResponse.CategoryDTO> dto = categoryService.findAllCategory(null);
         CommonResp<List<CategoryResponse.CategoryDTO>> resp = CommonResp.success(dto);
         return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
