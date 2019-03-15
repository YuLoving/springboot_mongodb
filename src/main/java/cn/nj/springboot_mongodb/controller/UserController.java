package cn.nj.springboot_mongodb.controller;

import cn.nj.springboot_mongodb.dao.UserDao;
import cn.nj.springboot_mongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserDao userdao;

    //添加一个user
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public User adduser(@RequestParam("name") String name,
                @RequestParam("age") Integer age,@RequestParam("gender")
                      String gender  )
                {
                    User user = new User();
                    user.setName(name);
                    user.setAge(age);
                    user.setGender(gender);
                    User aa = userdao.insert(user);
                    return aa;
                }
    //查询所有的user
    @RequestMapping(value="/getall",method = RequestMethod.GET)
    public Page<User> findall(@RequestParam("page") Integer page ){
        /**
         * 添加分页查询 pageable
         */
        Pageable pageable= PageRequest.of(page-1, 2);
        Page<User> all = userdao.findAll(pageable);
        return all;
    }

    //根据自己写的条件查询name
    @RequestMapping(value="/getbyname",method = RequestMethod.GET)
    public  User findbyname(@RequestParam("name") String name){
        User user = userdao.findByName(name);
        return user;
    }
    //根据自己写的条件查询age
    @RequestMapping(value="/getbyage",method = RequestMethod.GET)
    public  List<User> findbyage(@RequestParam("age") Integer age){
        List<User> list = userdao.findByAge(age);
        return list;
    }

    //根据ID来删除
    @RequestMapping(value = "/delbyid", method = RequestMethod.POST)
    public boolean del(@RequestParam("id") String id){
        userdao.deleteById(id);
        return true;
    }

    //根据ID来修改对应的记录
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public User upd(User user){
        Optional<User> user1 = userdao.findById(user.getId());
        if(user.getAge()==null){
            user.setAge(user1.get().getAge());
        }
        if(user.getName()==null){
           user.setName(user1.get().getName());
        }
        if(user.getGender()==null){
            user.setGender(user1.get().getGender());
        }
        //save是属于替换，
        User save = userdao.save(user);
        return save;
    }
}
