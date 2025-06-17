package com.example.yogafitnessrating.controller;
import com.example.yogafitnessrating.dto.PageRequest;
import com.example.yogafitnessrating.model.Page;
import com.example.yogafitnessrating.service.PageService;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pages")
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @PostMapping("/add")
    public ResponseEntity<Page> addPage(@RequestBody PageRequest request, Authentication authentication) {
        Page page = pageService.addPage(request, authentication.getName());
        return ResponseEntity.ok(page);
    }
   
    
}
