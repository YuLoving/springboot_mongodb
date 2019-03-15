package cn.nj.springboot_mongodb.dao;

import cn.nj.springboot_mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Near;

import java.util.List;

/**
 * MongoRepository<User, Integer>
   * 第一个参数：T 操作的vo
   * 第二个参数：ID T的主键类型
   * 作用：该接口实现了CRUD方法
   *
   * 注意：
    * 1、由于boot使用了spring-data-mongodb，所以我们不需要写该接口的实现，
    *   当我们运行程序的时候，spring-data-mongodb会动态创建
    * 2、findByname命名是有讲究的，name（是Customer的属性）若改为lastname就会报找不到属性lastname的错误
 */
public interface UserDao extends MongoRepository<User,String> {
    public User  findByName(String name);
    public List<User> findByAge(Integer age);
    /**
     * 一些自定义的写法
     * 自定义查询方法，格式为“findBy+字段名+方法后缀”，
     * 方法传进的参数即字段的值，此外还支持分页查询，通过传进一个Pageable对象，返回Page集合。
     */

    /**
     * Like（模糊查询）
     * {"username" : name} ( name as regex)
     *  List<User> findByUsernameLike(String username);
     * */


    /**
     * Like（模糊查询）
     * {"username" : name}
     * List<User> findByUsername(String username);
     * */


    /**
     * GreaterThan(大于)
     * {"age" : {"$gt" : age}}
     * List<User> findByAgeGreaterThan(int age);
     * */

    /**
     * LessThan（小于）
     * {"age" : {"$lt" : age}}
     *List<User> findByAgeLessThan(int age);
     *  */

    /**
     * Between（在...之间）
     * {{"age" : {"$gt" : from, "$lt" : to}}
     * List<User> findByAgeBetween(int from, int to);
     * */


    /**
     * IsNotNull, NotNull（是否非空）
     *  {"username" : {"$ne" : null}}
     *   List<User> findByUsernameNotNull();
     * */


    /**
     * IsNull, Null（是否为空）
     *   {"username" : null}
     *     List<User> findByUsernameNull();
     * */



    /**
     * Not（不包含）
     *    {"username" : {"$ne" : name}}
     *     List<User> findByUsernameNot(String name);
     * */




    /**
     *  Near（查询地理位置相近的）
     *  {"location" : {"$near" : [x,y]}}
     * */
    // findByLocationNear(Point point)


    /**
     * Within（在地理位置范围内的）
     *   {"location" : {"$within" : {"$center" : [ [x, y], distance]}}}
     * */
    //findByLocationWithin(Circle circle)


    /**
     *   Within（在地理位置范围内的）
     *     {"location" : {"$within" : {"$box" : [ [x1, y1], x2, y2]}}}
     * */
    // findByLocationWithin(Box box)
}
