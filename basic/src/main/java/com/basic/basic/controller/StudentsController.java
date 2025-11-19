package com.basic.basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping ("api/students")
    public class StudentsController {

        @RequestMapping(value = "", method = RequestMethod.GET)
        public ResponseEntity<?> hello() {
            return new ResponseEntity("Hello students!", HttpStatus.OK);
        }
    }
