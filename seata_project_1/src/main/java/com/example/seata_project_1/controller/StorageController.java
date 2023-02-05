package com.example.seata_project_1.controller;

import com.example.seata_project_1.pojo.Storage;
import com.example.seata_project_1.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName StorageController
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 下午 4:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/storage/")
public class StorageController {

    @Resource
    private StorageService storageService;


    @PostMapping("/add")
    public String addStorage(@RequestBody Storage storage) {
        storageService.save(storage);
        return "SUCCESS";
    }
}
