package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myproject.main.model.Tag;
import com.myproject.main.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable int id) {
        return tagService.getTagById(id);
    }

    @PostMapping
    public Tag addTag(@RequestBody String tagName) {
        return tagService.createTag(tagName);
    }

    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable int id, @RequestBody Tag tag) {
        tag.setId(id);
        return tagService.updateTag(id,tag);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable int id) {
        tagService.deleteTag(id);
    }
}
