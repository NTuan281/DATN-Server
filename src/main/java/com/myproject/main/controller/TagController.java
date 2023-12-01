package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myproject.main.model.Tag;
import com.myproject.main.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagRepository tagDAO;

    @Autowired
    public TagController(TagRepository tagDAO) {
        this.tagDAO = tagDAO;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagDAO.findAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable int id) {
        return tagDAO.findById(id).orElse(null);
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagDAO.save(tag);
    }

    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable int id, @RequestBody Tag tag) {
        tag.setId(id);
        return tagDAO.save(tag);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable int id) {
        tagDAO.deleteById(id);
    }
}
