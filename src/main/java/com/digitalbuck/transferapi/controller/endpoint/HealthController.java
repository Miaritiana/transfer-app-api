package com.digitalbuck.transferapi.controller.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class HealthController {
  @GetMapping("/ping")
  public ResponseEntity<String> pong() {
    return new ResponseEntity<>("pong", OK);
  }
}
