package com.crudapi.springboot.controller;

import com.crudapi.springboot.model.Test;
import com.crudapi.springboot.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    @Autowired
    TestRepository testRepository;

    @GetMapping("/tests")
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @GetMapping("/tests/{id}")
    public Test getTestById(@PathVariable(value = "id") Long testId) {
        return testRepository.findById(testId).orElse(null);
    }

    @PostMapping("/tests")
    public Test createTest(@RequestBody Test test) {
        return testRepository.save(test);
    }

    @DeleteMapping("/tests/{id}")
    public void deleteTest(@PathVariable(value = "id") Long testId) {
        testRepository.delete(getTestById(testId));
    }
}
