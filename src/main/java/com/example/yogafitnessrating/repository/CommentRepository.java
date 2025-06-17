package com.example.yogafitnessrating.repository;

import com.example.yogafitnessrating.model.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPageId(Long pageId);
}
