package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    Map<Long, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }
    @GetMapping
    public List<Koala> listKoalas() {
        return koalas.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Koala findById(@PathVariable long id) {
        Koala koala = koalas.get(id);
        return koala;
    }
    @PostMapping
    public Koala saveKoala(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);
        return koala;
    }
    @PutMapping("/{id}")
    public Koala update(@PathVariable long id, @RequestBody Koala koala ){
        koalas.put(id, koala);
        return koala;
    }
    @DeleteMapping("/{id}")
    public Koala remove(@PathVariable long id) {
        koalas.remove(id);
        return koalas.get(id);
    }
}
