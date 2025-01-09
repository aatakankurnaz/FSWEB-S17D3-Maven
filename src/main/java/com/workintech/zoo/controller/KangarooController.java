package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }
    @GetMapping
    public List<Kangaroo> listKangaroos() {
        return kangaroos.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable long id) {
        if(id <= 0) {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(id >= 999 ) {
            throw new ZooException("Id NOT FOUND", HttpStatus.NOT_FOUND);
        }

        Kangaroo kangaroo = kangaroos.get(id);
        return kangaroo;

    }
    @PostMapping
    public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() <= 0) {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }
    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable long id, @RequestBody Kangaroo kangaroo ){
        if(id <= 0) {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Animal with given id is not exist" + id, HttpStatus.NOT_FOUND);
        }
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }
    @DeleteMapping("/{id}")
    public Kangaroo remove(@PathVariable long id) {
        if(id <= 0) {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Animal with given id is not exist" + id, HttpStatus.NOT_FOUND);
        }
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }
}
