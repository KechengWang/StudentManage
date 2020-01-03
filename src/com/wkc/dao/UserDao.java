package com.wkc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkc.bean.Page;
import com.wkc.bean.User;

public interface UserDao {
	
	//�û���¼
	public User login(String snum);
	
	//���������û�
	public List<User> listAllUser();
	
	//����û�
	public void addUser(User user);
	
	
	//ע��
	public void register(User user);
	
	//����ID�����û�
	public User findUserById(Integer id);
	
	//�����û�(�û��޸��Լ���Ϣ)
	public void updateUser(User user);
	
	//ɾ���û�
	public void deleteUser(Integer id);
	
	//����ѧ�Ų���
	public List<User> findUserBySnum(String snum);
	
	//�ж��Ƿ����Ա
	public User isAdmin(String snum);
	
	
	//����  ����ͼƬ
	public void imgUpLoad(User user);

}
