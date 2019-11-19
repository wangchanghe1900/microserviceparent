package cn.unicom.microservice.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 王长河
 * @create 2019-11-18 16:59
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -453699984347835429L;
    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
