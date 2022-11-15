package com.shoes.service;

import java.util.List;

import com.shoes.entity.Login;
import com.shoes.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsService {
	
	@Autowired
	public UserRepository userepo;
	
	public List<Login> findByKeyword(String keyword){
		return userepo.findByKeyword(keyword);
	}

}
