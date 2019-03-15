package cn.nj.springboot_mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
/**
   * 测试mongodb的实体类
  */
@Data
@Document(collection = "stus")
public class User implements Serializable {
    /**
          * id：该字段用于mongodb的"_id"索引
          * 1、需要@Id注解
          * 2、取名无所谓，反正在mongodb中最后都会转化为"_id"
          * 3、定义为String类型，如果定义为Integer可能索引只会是0，会出现key重复导致数据库插不进去的情况；
          * 4、该类型也是MongoRepository泛型中主键的ID
     */
    @Id
    private String id;
    private String name;
    private Integer age;
    private String gender;

}
