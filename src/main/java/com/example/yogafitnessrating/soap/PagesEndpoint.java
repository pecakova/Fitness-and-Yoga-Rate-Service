package com.example.yogafitnessrating.soap;

import com.example.yogafitnessrating.model.Comment;
import com.example.yogafitnessrating.model.Page;
import com.example.yogafitnessrating.repository.CommentRepository;
import com.example.yogafitnessrating.service.PageService;
import com.example.yogafitnessrating.soap.gen.DeletePageRequest;
import com.example.yogafitnessrating.soap.gen.DeletePageResponse;
import com.example.yogafitnessrating.soap.gen.ListPagesRequest;
import com.example.yogafitnessrating.soap.gen.ListPagesResponse;
import com.example.yogafitnessrating.soap.gen.UpdatePageRequest;
import com.example.yogafitnessrating.soap.gen.UpdatePageResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PagesEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/yogafitness";

    @Autowired
    private PageService pageService;

    private CommentRepository commentRepository;
    @Autowired
    public PagesEndpoint(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdatePageRequest")
    @ResponsePayload
    public UpdatePageResponse updatePage(@RequestPayload UpdatePageRequest request) {
        UpdatePageResponse response = new UpdatePageResponse();

        boolean updated = pageService.updatePage(
                request.getPageId(),
                request.getName(),
                request.getLocation(),
                request.getWorkHours(),
                request.getDescription(),
                request.getCategory(),
                request.getType()
        );

        response.setStatus(updated ? "SUCCESS" : "FAILURE: Page not found");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeletePageRequest")
    @ResponsePayload
    public DeletePageResponse deletePage(@RequestPayload DeletePageRequest request) {
        DeletePageResponse response = new DeletePageResponse();

        boolean deleted = pageService.deletePageById(request.getPageId());
        response.setStatus(deleted ? "SUCCESS" : "NOT_FOUND");

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListPagesRequest")
    @ResponsePayload
    public ListPagesResponse listPages(@RequestPayload ListPagesRequest request) {
        String type = request.getType();
        String category = request.getCategory();
        Double rate = request.getMinRating();

        List<Page> pages;

        if (type == null && category == null && rate == null ) {
            pages = pageService.findAllPages();
        } else if (type != null && category == null && rate == null) {
            pages = pageService.findByType(type);
        } else if (type == null && category != null && rate != null) {
            pages = pageService.findByCategory(category);
        } else if (type == null && category == null && rate != null) {
            pages = pageService.findByRatingAbove(rate);
        }else {
            pages = pageService.findAllPages();
        }

        ListPagesResponse response = new ListPagesResponse();
        for (Page page : pages) {
            ListPagesResponse.Pages soapPage = new ListPagesResponse.Pages();
            soapPage.setId(page.getId());
            soapPage.setName(page.getName());
            soapPage.setLocation(page.getLocation());
            soapPage.setWorkHours(page.getWorkHours());
            soapPage.setDescription(page.getDescription());
            soapPage.setCategory(page.getCategory());
            soapPage.setType(page.getType());
            soapPage.setAverageRating(page.getAverageRating());
            soapPage.setRatingCount(page.getRatingCount());

            List<Comment> comments = commentRepository.findByPageId(page.getId());
            for (Comment comment : comments) {
                ListPagesResponse.Pages.Comments soapComment = new ListPagesResponse.Pages.Comments();
                soapComment.setId(comment.getId());
                soapComment.setContent(comment.getContent());
                soapComment.setCreatedAt(comment.getCreatedAt().toString());
                soapPage.getComments().add(soapComment);
            }

            response.getPages().add(soapPage);
        }

        return response;
    }

}
