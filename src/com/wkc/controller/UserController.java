package com.wkc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.wkc.bean.User;
import com.wkc.bean.resultJT;
import com.wkc.service.UserService;


@CrossOrigin
@Controller
@RequestMapping(produces = "application/json; charset=utf-8")
public class UserController {
	
	@Autowired
	public UserService userservice;

	//查找所有用户
	@RequestMapping(value = "/listAllUsers",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public resultJT listAllUsers () {
        resultJT resultJT = new resultJT();
        try {
            List<User> users = userservice.listAllUser();
            resultJT.setResult("0");
            resultJT.setList(users);
        }catch(Exception e){
            resultJT.setResult("error");
        }
        return resultJT;
    }
	
	//用户登录
	@RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public resultJT loginUser (String snum,String password) {
        resultJT resultJT = new resultJT();
        try {
        	System.out.println(snum);
            User user = userservice.login(snum);
            //System.out.println(user);
            if(user != null){
            	User user2=userservice.isAdmin(snum);
                if(user.getPassword().equals(password)){
                	if(user2.isRole()) {
                		resultJT.setResult("11");//表示该用户为管理员
                	}else {
                		resultJT.setResult("1");
                	} 
                }else {
                    resultJT.setResult("2");
                }
            }else {
                resultJT.setResult("3");
            }
        }catch(Exception e){
            e.printStackTrace();
            resultJT.setResult("0");
        }
        return resultJT;
    }
    
	
	
//	//用户登录
//	@GetMapping(value="login/snum={snum}&&password={password}")
//    @ResponseBody
//    public String login (
//            @PathVariable("snum") String snum,
//            @PathVariable("password") String password) {
//		String msg = null;
//		try {
//			User user = userservice.login(snum);
//        
//        System.out.println(user.getPassword());
//        System.out.println(password);    
//        	if (user.getPassword().equals(password)){
//            System.out.println("sucess");
//            msg= String.valueOf(user.getId());
//        }
//        else{
//            System.out.println("失败");
//            msg="false";
//        }}
//        	catch(Exception e) {
//        		e.printStackTrace();
//        		msg="not exist";
//        	}
//
//        return msg;
//    }
	
	//用户注册
//	@GetMapping(value="register/snum={snum}&&password={password}")
//    @ResponseBody
//    public String saveUser (
//            @PathVariable("snum") String snum,
//            @PathVariable("password") String password) {
//        User user = new User();
//        user.setSnum(snum);
//        user.setPassword(password);
//        System.out.println(user);
//        userservice.register(user);
//        Gson gson = new Gson();
//        System.out.println(gson.toJson("注册成功"));
//        return gson.toJson("success");
//    }
	
	
	
	//用户注册2
	@RequestMapping(value = "/register",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public resultJT registerUser(String snum,String password) {
        resultJT resultJT = new resultJT();
        try {
        	
        	List<User> users=userservice.findUserBySnum(snum);
        	System.out.println(users);
        	System.out.println(snum);
        	System.out.println(password);
        	if(users.size()==0) {
        		User user = new User();
        		user.setSnum(snum);
        		user.setPassword(password);
        		userservice.register(user);
        		resultJT.setResult("1");
        		
        	}else if(users.size()!=0){
        		resultJT.setResult("2");
        	}
        		
        	

        }catch(Exception e){
            e.printStackTrace();
            resultJT.setResult("0");
        }
        return resultJT;
    }
	
	
	
	
	//用户更新
//	@RequestMapping(value = "/updateUser",method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    @InitBinder
//    public resultJT updateUser(Integer id,String name,String snum,String password,Boolean sex,Date birth,String city) {
//        resultJT resultJT = new resultJT();
//        try{
//            User user = userservice.findUserById(id);
//            if(user != null){
//                user.setName(name);
//                user.setSnum(snum);
//                user.setPassword(password);
//                user.setSex(sex);
//                user.setBirth(birth);
//                user.setCity(city);
//                userservice.updateUser(user);
//                resultJT.setResult("0success");
//            }else{
//                resultJT.setResult("1");
//            }
//        }catch (Exception e){
//            resultJT.setResult("error");
//        }
//        return resultJT;
//    }
	
		
	//用户更新
	@RequestMapping(value = "/updateUser",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public resultJT updateUser(@Valid User user,BindingResult BindingResult) {
        resultJT resultJT = new resultJT();
        try{
            if(user != null){
                userservice.updateUser(user);
                resultJT.setResult("0");
            }else{
                resultJT.setResult("1");
            }
        }catch (Exception e){
            resultJT.setResult("error");
        }
        return resultJT;
    }
	
	//删除用户
	@RequestMapping(value = "/deleteUser",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public resultJT deleteUser(Integer id) {
		resultJT resultJT = new resultJT();
		try {
			userservice.deleteUser(id);
			resultJT.setResult("0");
		}catch(Exception e) {
			e.printStackTrace();
			resultJT.setResult("error");
		}
		return resultJT;
	}
	
	//根据snum查找用户
	@RequestMapping(value = "/findUserBySnum",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public resultJT findUserBySnum(String snum) {
		resultJT resultJT = new resultJT();
		try {
			List<User> users = userservice.findUserBySnum(snum);
			if(users!=null) {
				resultJT.setList(users);
				resultJT.setResult("0");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resultJT.setResult("error");
		}
		return resultJT;
	}
	
	
	@RequestMapping(value = "/imgUpLoad",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public resultJT imgUpLoad(String snum, MultipartFile file) throws Exception, IOException {
		resultJT resultJT = new resultJT();
		System.out.println(snum);
		System.out.println("file"+file.getName());
//		System.out.println(user);
//		System.out.println(file.getName());
//		String filePath="F:\\upload";
//		String originalFilename = file.getOriginalFilename();
//		String newFileName = UUID.randomUUID()+originalFilename;
//		File targetFile = new File(filePath,newFileName); 
//		file.transferTo(targetFile);
//		user.setImg(newFileName);
//		userservice.updateUser(user);
		resultJT.setResult("0");
		return resultJT;
	}
	
	
}
