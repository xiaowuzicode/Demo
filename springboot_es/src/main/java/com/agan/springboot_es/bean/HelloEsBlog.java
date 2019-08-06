package com.agan.springboot_es.bean;

import io.searchbox.annotations.JestId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author cg
 */
@Data
@Entity
@Table(name = "cg_es_helloesblog")
public class HelloEsBlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JestId  // 主键
    private Integer id;


    private String title;

    private String context;

    private Date creattime;

    private Integer status;

    private String author;


}
