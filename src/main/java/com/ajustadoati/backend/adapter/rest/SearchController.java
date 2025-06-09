package com.ajustadoati.backend.adapter.rest;

import com.ajustadoati.backend.adapter.rest.dto.SearchDto;
import com.ajustadoati.backend.application.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/searches")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping
    public ResponseEntity<SearchDto> save(@RequestBody SearchDto searchDto){

        return new ResponseEntity<>(searchService.save(searchDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SearchDto>> getAll(){

        return new ResponseEntity<>(searchService.getAll(), HttpStatus.OK);
    }
}
