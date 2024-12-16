package com.metacoding.web_project.user;

import com.metacoding.web_project._core.error.ex.Exception400;
import com.metacoding.web_project._core.error.ex.Exception404;
import com.metacoding.web_project.useraccount.UserAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    @Transactional
    public UserResponse.InfoDTO 유저정보보기(int id) {
        UserAccount user = userRepository.findInfo(id);
        return new UserResponse.InfoDTO(user);
    }

    @Transactional
    public void 유저정보수정하기(int id, UserRequest.UpdateDTO updateDTO, UserRequest.UpdateUserAccountDTO updateUserAccountDTO) {
        userRepository.updateUser(id,updateDTO.getTel(),
            updateDTO.getPostNum(),
            updateDTO.getAddr(),
            updateDTO.getAddrDetail()
        );
        userRepository.updateUserAccount(id,
            updateUserAccountDTO.getAccount().replaceAll("[^a-zA-Z0-9]", "").trim()
        );
    }

    @Transactional
    public void 비밀번호변경(int id, UserRequest.ChangePwDTO changePwDTO) {
        String newPassword = passwordEncoder.encode(changePwDTO.getNewPassword());

        User userPS = userRepository.findById(id); //repository에서 select해서 db에서 가져온 비번

        boolean isSame = passwordEncoder.matches(changePwDTO.getPassword(), userPS.getPassword());

        if(isSame){
            userPS.changePassword(newPassword);
        }
    } // 더티체킹

    @Transactional
    public UserResponse.CreditDTO 내정보보기(int id) {
        UserAccount userAccount = userRepository.findByIdUserInfo(id)
            .orElseThrow(()-> new Exception404("0"));
        return new UserResponse.CreditDTO(userAccount);
    }

    @Transactional
    public int 아이디중복확인(UserRequest.CheckIdDTO checkIdDTO) {
        return userRepository.checkId(checkIdDTO.getUsername());
    }

    @Transactional
    public String 아이디찾기(UserRequest.FindUserDTO findUserDTO) {
        try {
            return userRepository.findUserId(findUserDTO.getTel(),findUserDTO.getName());
        } catch (RuntimeException e) {
            throw new Exception400("입력하신 회원정보가 존재하지 않습니다.");
        }
    }

    @Transactional
    public Integer 비번찾기(UserRequest.FindPwDTO findPwDTO) {
        return userRepository.findPassword(findPwDTO.getTel(),findPwDTO.getName(),findPwDTO.getUsername());
    }

    @Transactional
    public void 비번변경(int id, UserRequest.ChPwDTO pwDTO) {
        String newPassword = passwordEncoder.encode(pwDTO.getPassword());
        userRepository.changePassword(id,newPassword);
    }

    @Transactional
    public void 출금하기(int id, UserRequest.WithdrawDTO withdrawDTO) {
        Integer hasMoney = (Integer) withdrawDTO.getHasPrice();
        Integer outMoney = (Integer) withdrawDTO.getOutMoney();
        try {
            if(hasMoney < outMoney){
                throw new Exception404("잔액이 부족합니다. 현재 잔액: " + hasMoney + ", 출금 요청 금액: " + outMoney);
            }
            Integer leftMoney = hasMoney - outMoney;
            userRepository.withdraw(id, leftMoney);
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }

    @Transactional
    public void 충전하기(Integer id, UserRequest.ChargeDTO chargeDTO) {
        Integer hasPrice = (Integer) chargeDTO.getHasPrice();
        Integer inMoney = (Integer) chargeDTO.getInMoney();

        Integer afterMoney = hasPrice + inMoney;
        userRepository.charge(id,afterMoney);
    }
}



