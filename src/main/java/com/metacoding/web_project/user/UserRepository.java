package com.metacoding.web_project.user;

import com.metacoding.web_project._core.error.ex.Exception401;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findByUsername(String username) {
        Query q = em.createQuery("select u from User u where u.username = :username", User.class);
        q.setParameter("username", username);

        try {
            return (User) q.getSingleResult();
        } catch (RuntimeException e) {
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
        }
    }

    public void join(User user) {
        em.persist(user);
    }


    public User login(String username, String password) {
        Query q = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        q.setParameter("username",username );
        q.setParameter("password", password);
        try{
            return (User) q.getSingleResult();
        }catch(RuntimeException e){
            throw new Exception401("존재하지 않는 아이디 입니다.");
        }
    }

    public User findInfo(int id) {
        Query q = em.createQuery("select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }

    @Transactional
    public void update(int id, String tel, String postNum, String addr, String addrDetail) {
        String sql = """
                update User u
                set u.tel = :tel,
                    u.postNum = :postNum,
                    u.addr = :addr,
                    u.addrDetail = :addrDetail
                where u.id = :id
                """;

        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        q.setParameter("tel", tel);
        q.setParameter("postNum", postNum);
        q.setParameter("addr", addr);
        q.setParameter("addrDetail", addrDetail);
        q.executeUpdate();
    }

    @Transactional
    public void changePw(int id, String enPassword, String enNewPassword) {
        Query q = em.createQuery("update User u set u.password = :newPassword where u.password = :password and u.id = :id");
        q.setParameter("newPassword", enNewPassword);
        q.setParameter("password", enPassword);
        q.setParameter("id", id);
        q.executeUpdate();
    }
}