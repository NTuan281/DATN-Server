package com.myproject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.main.model.Tag;



public interface TagRepository extends JpaRepository<Tag, Integer>{

}
