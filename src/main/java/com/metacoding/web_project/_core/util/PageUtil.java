package com.metacoding.web_project._core.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    // 현재 페이지와 한번에 출력할 개수를 매개 변수로 전달, sql에 사용할 offset 값을 반환합니다.
    public static Integer offsetCount(String page, int printCount) {
        int offset = 0;
        if (!page.equals("")) {
            offset = Integer.parseInt(page) * printCount - printCount;
        }
        return offset;
    }

    // 현재 페이지, 행의 총 개수를 매개 변수로 전달, PaginationDTO를 반환, 반환 값을 model에 pagination이름으로 addAttribute 하세요
    public static PaginationDTO returnToPageDTO(String pageNow, Integer rowCount) {
        if (pageNow == null || pageNow.equals("")) {
            pageNow = "1";
        }

        int currentPage = Integer.parseInt(pageNow); // 현재 페이지
        Integer totalPages = pageCalculation(rowCount); // total 페이지
        List<Page> pages = new ArrayList<>();

        if (totalPages < 9) {
            for (int i = 1; i <= totalPages; i++) {
                pages.add(new Page(i, i == currentPage));
            }
            return new PaginationDTO(currentPage, totalPages, pages);
        }
        if (currentPage <= 5) {
            for (int i = 1; i <= 9; i++) {
                pages.add(new Page(i, i == currentPage));
            }
        } else if (currentPage < totalPages - 4) {
            for (int i = currentPage - 4; i <= currentPage + 4; i++) {
                pages.add(new Page(i, i == currentPage));
            }
        } else {
            for (int i = currentPage - 8 + (totalPages - currentPage); i <= totalPages; i++) {
                pages.add(new Page(i, i == currentPage));
            }
        }
        return new PaginationDTO(currentPage, totalPages, pages);
    }


    @Data
    private static class PaginationDTO {
        private int currentPage;
        private int totalPages;
        private List<Page> pages;

        public PaginationDTO(int currentPage, int totalPages, List<Page> pages) {
            this.currentPage = currentPage;
            this.totalPages = totalPages;
            this.pages = pages;
        }
    }

    @Data
    private static class Page {
        private int pageNumber;
        private boolean isActive;

        public Page(int pageNumber, boolean isActive) {
            this.pageNumber = pageNumber;
            this.isActive = isActive;
        }
    }

    private static Integer pageCalculation(int rowCount) {
        int pageCount = 1;
        if (rowCount > 10) {
            while(rowCount > 10) {
                pageCount++;
                rowCount -= 10;
            }
        }
        return pageCount;
    }


}
