package com.example.gfg_blr_9.controllers;

import com.example.gfg_blr_9.annotations.InitSalary;
import com.example.gfg_blr_9.annotations.JsonSerializableField;
import com.example.gfg_blr_9.models.Employee;
import com.example.gfg_blr_9.models.Records;
import com.example.gfg_blr_9.services.RedisDriver;
import com.example.gfg_blr_9.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@RestController
public class HealthController {



    private static final Logger log = LoggerFactory.getLogger(HealthController.class);
    @Autowired // Field Injection
    private UserService userService;


//    @Autowired
//    private RedisDriver redisDriver;

    public HealthController(){
        log.info("HealthController getting initiated");
    }



    /*@Autowired // Constructor Injection -> required dependecies
    public HealthController(UserService userService){
        this.userService = userService;
    }*/
    @Autowired //setter Injection -> optional dependecies
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/healthCheck")
    public String checkHealth(){
        return "Ok";
    }
    @GetMapping("/welcome/{version}")
    public String welcome(@RequestParam(name = "q") String name, @PathVariable String version){
       return "Welcome "+name +" to "+ version;
    }

    @PostMapping("/welcome/kyc")
    public ResponseEntity<Records> welcomeKyc(@RequestBody Records record){

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Accept","application/json");
        headers.add("tid","abcd");
        return new ResponseEntity<>(record, headers, HttpStatus.ACCEPTED);
    }
    @GetMapping("/serializedEmployee")
    public String serializedEmployee() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*Employee employee = new Employee(1,"sksk","lst",25,25000);
        Class<?> cls = employee.getClass();
        String title = cls.getDeclaredFields()[1].getAnnotation(JsonSerializableField.class).value();
        Object obj = cls.getDeclaredMethod("getEmployeeDetails").invoke(employee, title);
        */
        Employee employee = new Employee(1,"sush","s",25,0);
        Class<?> cls = Employee.class;
        int salary = 0;
        for(Method m : cls.getDeclaredMethods()){
            if(m.getAnnotation(InitSalary.class) != null){
                salary = m.getAnnotation(InitSalary.class).value();
                m.invoke(employee,salary);
            }

        }

        return "ok";

    }
}
