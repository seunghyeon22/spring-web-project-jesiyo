package com.metacoding.web_project.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


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

    public User findId(UserResponse.FindIdDTO findIdDTO) {
        return em.createQuery("select username from User u where u.name = :name and u.tel = :tel", User.class)
                .getSingleResult();
    }

    public User findPw(UserResponse.FindPasswordDTO findPasswordDTO) {
        return em.createQuery("select password,id from User u where u.name = :name and u.tel = :tel and u.username = :username", User.class)
                .getSingleResult();
    }
}