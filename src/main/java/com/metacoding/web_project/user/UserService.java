package com.metacoding.web_project.user;

import com.metacoding.web_project._core.error.ex.Exception401;
import com.metacoding.web_project._core.error.ex.Exception404;
import com.metacoding.web_project.useraccount.UserAccount;
import com.metacoding.web_project.useraccount.UserAccountRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    // TODO : passwordEncoder를 이용해 변환한 비밀번호를 DB에 저장하여야 합니다.
    private final PasswordEncoder passwordEncoder;


    // POST 요청
    // /login 일때 호출 됨
    // key 값 -> username, password
    // Content-Type -> x-www-form-urlencoded
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User user = joinDTO.toEntity(passwordEncoder);
        userRepository.join(user);
        UserAccount userAccount = joinDTO.toEntity(user);
        userRepository.join2(userAccount);
    }

/*    @Transactional
    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User userPS = userRepository.login(loginDTO.getUsername(),loginDTO.getPassword());
        if(!userPS.getPassword().equals(loginDTO.getPassword())){
            throw new Exception401("아이디나 비밀번호가 맞지 않습니다.");
        }
        return userPS;
    }*/

    @Transactional
    public UserResponse.InfoDTO 유저정보보기(int id) {
        UserAccount user = userRepository.findInfo(id);
        return new UserResponse.InfoDTO(user);
    }


    @Transactional
    public void 유저정보수정하기(int id, UserRequest.UpdateDTO updateDTO) {
        userRepository.update(id,updateDTO.getTel(),
                                 updateDTO.getPostNum(),
                                 updateDTO.getAddr(),
                                 updateDTO.getAddrDetail(),
                                updateDTO.getAccount());
    }

    @Transactional
    public void 비밀번호변경(int id, UserRequest.ChangePwDTO changePwDTO) {

        String enPassword = passwordEncoder.encode(changePwDTO.getPassword());
        String enNewPassword = passwordEncoder.encode(changePwDTO.getNewPassword());

        userRepository.changePw(id, enPassword, enNewPassword);
        System.out.println(enPassword);
        System.out.println(enNewPassword);
    }

//    public UserResponse.CreditDTO 내정보보기(int id){
//        Optional<User> user = userRepository.findById(id);
//        return new UserResponse.CreditDTO();
//    }
    @Transactional
    public UserResponse.CreditDTO 내정보보기(int id) {
         UserAccount userAccount = userRepository.findByIdUserInfo(id)
                .orElseThrow(()-> new Exception404("0"));

         return new UserResponse.CreditDTO(userAccount);
    }


}



