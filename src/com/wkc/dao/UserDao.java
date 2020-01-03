package com.wkc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkc.bean.Page;
import com.wkc.bean.User;

public interface UserDao {
	
	//用户登录
	public User login(String snum);
	
	//查找所有用户
	public List<User> listAllUser();
	
	//添加用户
	public void addUser(User user);
	
	
	//注册
	public void register(User user);
	
	//根据ID查找用户
	public User findUserById(Integer id);
	
	//更新用户(用户修改自己信息)
	public void updateUser(User user);
	
	//删除用户
	public void deleteUser(Integer id);
	
	//根据学号查找
	public List<User> findUserBySnum(String snum);
	
	//判断是否管理员
	public User isAdmin(String snum);
	
	
	//测试  保存图片
	public void imgUpLoad(User user);

}
