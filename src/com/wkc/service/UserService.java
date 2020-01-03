package com.wkc.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkc.bean.Page;
import com.wkc.bean.User;
import com.wkc.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	public UserDao userdao;
	
	public User login(String snum) {
		return userdao.login(snum);
	}
	
	public List<User> listAllUser(){
		return userdao.listAllUser();
	}
	
	public void addUser(User user) {
		userdao.addUser(user);
	}
	
	public void register(User user) {
		userdao.register(user);
	}
	
	public User findUserById(Integer id) {
		return userdao.findUserById(id);
	}
	
	public void updateUser(User user) {
		userdao.updateUser(user);
	}
	
	public void deleteUser(Integer id) {
		userdao.deleteUser(id);
	}
	
	public List<User> findUserBySnum(String snum){
		return userdao.findUserBySnum(snum);
	}
	
	public User isAdmin(String snum) {
		return userdao.isAdmin(snum);
	}
	
	

}
