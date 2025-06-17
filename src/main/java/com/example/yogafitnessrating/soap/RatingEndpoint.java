package com.example.yogafitnessrating.soap;

import com.example.yogafitnessrating.soap.gen.RatePageRequest;
import com.example.yogafitnessrating.soap.gen.RatePageResponse;
import com.example.yogafitnessrating.model.Page;
import com.example.yogafitnessrating.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.Optional;

@Endpoint
public class RatingEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/yogafitness";

    private final PageRepository pageRepository;

    @Autowired
    public RatingEndpoint(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RatePageRequest")
    @ResponsePayload
    public RatePageResponse ratePage(@RequestPayload RatePageRequest request) {
        RatePageResponse response = new RatePageResponse();

        long pageId = request.getPageId();
        System.out.println("[DEBUG] Received pageId: " + pageId + ", score: " + request.getScore());

        Optional<Page> optionalPage = pageRepository.findById(pageId);

        if (optionalPage.isEmpty()) {
            System.out.println("[DEBUG] Page NOT found with id: " + pageId);
            response.setStatus("Page not found");
            return response;
        }

        Page page = optionalPage.get();
        System.out.println("[DEBUG] Page found: " + page.getName());

        page.addRating(request.getScore());
        pageRepository.save(page);

        response.setStatus("Rating added successfully");
        return response;
    }

}

