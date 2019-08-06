package com.agan.springboot_es.dao;


import com.agan.springboot_es.bean.HelloEsBlog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cg
 */
public interface HelloEsInterface extends JpaRepository<HelloEsBlog,Integer> {

}
