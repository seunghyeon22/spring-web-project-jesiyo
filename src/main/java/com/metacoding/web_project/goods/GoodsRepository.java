package com.metacoding.web_project.goods;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GoodsRepository {
    private final EntityManager em;

    public Optional<Goods> findById(Integer id) {
        return Optional.ofNullable(em.find(Goods.class, id));
    }

    // 제품 등록
    public void save(Goods goods) {
        em.persist(goods);
    }
    // 카테고리 아이디로 경매중인 물품 리스트 조회
    public Optional<List<Goods>> findByCategoryId(Integer categoryId, Integer page, Integer line) {
        String sql = """
                select g from Goods g left join fetch g.seller left join fetch g.category where  g.status=:status and g.category.id=:categoryId order by g.id desc limit :line offset :page
                """;
        Query query = em.createQuery(sql);
        query.setParameter("status", 0);
        query.setParameter("categoryId", categoryId);
        query.setParameter("line", line);
        query.setParameter("page", (page-1)*line);
        return Optional.ofNullable(query.getResultList());
    }
    // 제목 및 내용으로 경매중인 물품 리스트 조회
    public Optional<List<Goods>> searchGoods(GoodsRequest.SeacherGoodsDTO dto) {
        String sql = "select g from Goods g left join fetch g.seller left join fetch g.category where g.status=:status AND ";

        if(dto.getSelect().equals("title")){ // select가 제목이면 제목으로 물품 리스트를 검색
            sql += "g.title like :keyword";
        }else if(dto.getSelect().equals("content")){ // select가 content이면 내용으로 검색
            sql += "g.content like :keyword";
        }else {
            sql += "g.title like :keyword OR g.content like :keyword";
        }
        sql += " order by g.id desc limit :line offset :page ";

        Query query = em.createQuery(sql);
        query.setParameter("status", 0);
        query.setParameter("keyword", "%"+dto.getKeyword()+"%");
        query.setParameter("line", dto.getLine());
        query.setParameter("page", (dto.getPage()-1)*dto.getLine());

        return Optional.ofNullable(query.getResultList());
    }
    // 유저 아이디로 경매중인 물품 리스트 조회
//    public Goods findByUserId(){
//    }

}
