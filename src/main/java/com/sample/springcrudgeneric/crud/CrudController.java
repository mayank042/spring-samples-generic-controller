package com.sample.springcrudgeneric.crud;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public abstract class CrudController<T, ID> {

    private final CrudService<T, ID> service;

    protected CrudController(CrudService<T, ID> service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Iterable<T> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        Optional<T> optionalEntity = service.getById(id);
        return optionalEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        service.update(id, entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<T> partialUpdate(@PathVariable ID id, @RequestBody T updates) {
        return service.getById(id)
                .map(entity -> {
                    // Apply partial updates to entity here
                    service.update(id, entity);
                    return new ResponseEntity<>(entity, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        if (!service.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
