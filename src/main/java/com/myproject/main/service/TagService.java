package com.myproject.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.main.model.Tag;
import com.myproject.main.repository.TagRepository;



@Service
public class TagService {
	 private final TagRepository tagRepository;

	    @Autowired
	    public TagService(TagRepository tagRepository) {
	        this.tagRepository = tagRepository;
	    }

	    public List<Tag> getAllTags() {
	        return tagRepository.findAll();
	    }

	    public Tag getTagById(Integer id) {
	        return tagRepository.findById(id).orElse(null);
	    }

	    public Tag createTag(String tag) {
	    	Tag newTag = new Tag();
	        newTag.setNameTag(tag);
	        return tagRepository.save(newTag);
	    }

	    public Tag updateTag(Integer id, Tag tag) {
	        tag.setId(id);
	        return tagRepository.save(tag);
	    }

	    public void deleteTag(Integer id) {
	    	tagRepository.deleteById(id);
	    }
}
