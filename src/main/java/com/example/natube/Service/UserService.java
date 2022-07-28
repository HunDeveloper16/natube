package com.example.natube.Service;


import com.example.natube.dto.LoginRequestDto;
import com.example.natube.dto.LoginResponseDto;
import com.example.natube.dto.SignupRequestDto;
import com.example.natube.dto.SignupResponseDto;
import com.example.natube.exception.CustomException;
import com.example.natube.model.User;
import com.example.natube.repository.UserRepository;
import com.example.natube.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.example.natube.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder; // WebSecurityConfig에 있는 encoderPassword를 가져온것이라고 생각
    private final UserRepository userRepository;

    private final UserDetailsServiceImpl userDetailsService;
//    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    public SignupResponseDto registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        String unCheck = "^[a-zA-Z0-9._ -]{3,}$";  // 닉네임은 최소3자 이상, 알파벳 대소문자,숫자로 구성
        String pwCheck = "^[a-zA-Z0-9._ -]{4,}$"; //  비밀번호는 최소4자 이상, 닉네임과 같은 값이 포함된 경우 회원가입에 실패

        boolean regex1 = Pattern.matches(unCheck, username);
        boolean regex2 = Pattern.matches(pwCheck, password);

        if(!regex1){
            throw new CustomException(ID_CHECK_CODE);
        }
        if(!regex2 || password.contains(username)){
            throw new CustomException(PASSWORD_CHECK_CODE);
        }
        if(!password.equals(passwordCheck)){
            throw new CustomException(PASSWORD_EQUAL_CODE);
        }
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new CustomException(USERNAME_DUPLICATE_CODE);
        }

        // 닉네임 중복 확인
       String nickname = requestDto.getNickname();
        Optional<User> found2 = userRepository.findByNickname(nickname);
        if (found2.isPresent()) {
            throw new CustomException(NICKNAME_DUPLICATE_CODE);
        }

         //패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, nickname);
        userRepository.save(user);

        return new SignupResponseDto(username, password, passwordCheck, nickname);
    }

//    public Authentication getAuthentication(String username) {  // 회원인증
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

    public LoginResponseDto login(LoginRequestDto requestDto) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUsername());

        String inDBUsername = userDetails.getUsername();
        String inDBPassword = userDetails.getPassword();

        String inputPassword = requestDto.getPassword();

        String username = requestDto.getUsername();
        if (inDBUsername.equals(username)) {
            if (passwordEncoder.matches(inputPassword, inDBPassword)) {

//                SecurityContextHolder.getContext().setAuthentication(getAuthentication(inDBUsername)); // SecurityContextHolder에 로그인 정보 저장

                return new LoginResponseDto(username, inputPassword);
            }
            else{
                throw new CustomException(PASSWORD_EQUAL_CODE);
            }
        }

        return null;
    }

}
