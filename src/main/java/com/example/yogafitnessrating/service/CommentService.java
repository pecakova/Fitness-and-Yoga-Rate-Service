package com.example.yogafitnessrating.service;

import com.example.yogafitnessrating.model.Comment;
import com.example.yogafitnessrating.model.Page;
import com.example.yogafitnessrating.repository.CommentRepository;
import com.example.yogafitnessrating.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PageRepository pageRepository;

    public CommentService(CommentRepository commentRepository, PageRepository pageRepository) {
        this.commentRepository = commentRepository;
        this.pageRepository = pageRepository;
    }

    public Comment addComment(Long pageId, String content) {
        Page page = pageRepository.findById(pageId)
                .orElseThrow(() -> new RuntimeException("Page not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPage(page);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForPage(Long pageId) {
        return commentRepository.findByPageId(pageId);
    }
    public List<Comment> getCommentsByPageId(Long pageId) {
        return commentRepository.findByPageId(pageId);
    }

}
