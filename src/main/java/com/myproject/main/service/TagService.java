package com.myproject.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.main.model.Tag;
import com.myproject.main.repository.TagRepository;



@Service
public class TagService {
	 private final TagRepository tagDao;

	    @Autowired
	    public TagService(TagRepository tagDao) {
	        this.tagDao = tagDao;
	    }

	    public List<Tag> getAllTags() {
	        return tagDao.findAll();
	    }

	    public Tag getTagById(Integer id) {
	        return tagDao.findById(id).orElse(null);
	    }

	    public Tag createTag(Tag tag) {
	        return tagDao.save(tag);
	    }

	    public Tag updateTag(Integer id, Tag tag) {
	        tag.setId(id);
	        return tagDao.save(tag);
	    }

	    public void deleteTag(Integer id) {
	    	tagDao.deleteById(id);
	    }
}
