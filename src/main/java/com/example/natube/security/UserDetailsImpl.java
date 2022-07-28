package com.example.natube.security;


import com.example.natube.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {  // 회원이 로그인할때 이 클래스는 db에있는 값을 get으로 가져와서 로그인에 쓸값과 비교한다. 따라서 이 클래스는 비교값을 위해 존재한다.

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser(){
        return user;
    }


    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getNickname(){ return user.getNickname();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


}
