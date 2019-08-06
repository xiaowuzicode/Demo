package com.agan.springboot_es.controller;

import com.agan.springboot_es.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cg
 */
@RestController
public class EsController {


    @Autowired
    private EsService esService;

    @GetMapping("/getEsTest")
    public String getEsTest(@RequestParam String id){
        System.out.println(id);
       // esService.saveValue();
        esService.getValue(id);
        return esService.getValue(id);
    }

    @GetMapping("/deleteEsTest")
    public String deleteEsTest(@RequestParam String id){
        System.out.println(id);
        esService.deleteValue(id);
        return esService.deleteValue(id);
    }

    @GetMapping("/")
    public String hello(){
        return "hello_es";
    }

}
