package com.metacoding.web_project.user;

import com.metacoding.web_project._core.error.ex.Exception401;
import com.metacoding.web_project.useraccount.UserAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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

    public void join2(UserAccount userAccount) {
        em.persist(userAccount);
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

    public UserAccount findInfo(int id) {
        Query q = em.createQuery("select u from UserAccount u right join fetch u.user where u.user.id = :id ", UserAccount.class);
        q.setParameter("id", id);
        return (UserAccount) q.getSingleResult();
    }

    @Transactional
    public void update(int id, String tel, String postNum, String addr, String addrDetail, String account) {
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


        String Sql2 = """
            update UserAccount u
            set u.account = :account
            where u.user.id = :id
            """;

        Query qu = em.createQuery(Sql2);
        qu.setParameter("id", id);
        qu.setParameter("account", account);
        qu.executeUpdate();
    }

    @Transactional
    public void changePw(int id, String enPassword, String enNewPassword) {
        System.out.println("repository"+enPassword);
        System.out.println("repository"+enNewPassword);
        Query q = em.createQuery("update User u set u.password = :newPassword where u.password = :password and u.id = :id");
        q.setParameter("newPassword", enNewPassword);
        q.setParameter("password", enPassword);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Transactional
    public Optional<UserAccount> findByIdUserInfo(int id) {
        String sql = """
                    select u from UserAccount u join fetch u.user where u.user.id = :id 
                    """;
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
            try{
                UserAccount userAccount = (UserAccount) q.getSingleResult();
                return Optional.ofNullable(userAccount);
            }catch (RuntimeException e){
                return Optional.empty();
            }
      }


}