package com.SpringSecurity.SpringSecurity.authController.DemoController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class DemoController {
    @GetMapping("/api")
    public ResponseEntity<String> sayHello(){

        return ResponseEntity.ok("Saying Heloo .......from the private api");
    }

}
