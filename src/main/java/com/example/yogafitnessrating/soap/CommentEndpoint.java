package com.example.yogafitnessrating.soap;

import com.example.yogafitnessrating.service.CommentService;
import com.example.yogafitnessrating.soap.gen.AddCommentResponse;
import com.example.yogafitnessrating.soap.gen.AddCommentRequest;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CommentEndpoint {

    //private static final String NAMESPACE_URI = "http://example.com/yogafitness";

    private final CommentService commentService;

    public CommentEndpoint(CommentService commentService) {
        this.commentService = commentService;
    }

    @PayloadRoot(namespace = "http://example.com/yogafitness", localPart = "AddCommentRequest")
    @ResponsePayload
    public AddCommentResponse addComment(@RequestPayload AddCommentRequest request) {
        AddCommentResponse response = new AddCommentResponse();
        try {
            commentService.addComment(request.getPageId(), request.getContent());
            response.setStatus("Comment added successfully.");
        } catch (RuntimeException e) {
            response.setStatus("Failed to add comment: " + e.getMessage());
        }
        return response;
    }
}
