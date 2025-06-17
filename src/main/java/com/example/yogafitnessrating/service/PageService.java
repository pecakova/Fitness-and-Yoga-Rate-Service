package com.example.yogafitnessrating.service;

import com.example.yogafitnessrating.dto.PageRequest;
import com.example.yogafitnessrating.model.Page;
import com.example.yogafitnessrating.model.Role;
import com.example.yogafitnessrating.model.User;
import com.example.yogafitnessrating.repository.PageRepository;
import com.example.yogafitnessrating.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageService {

    private final PageRepository pageRepository;
    private final UserRepository userRepository;

    public PageService(PageRepository pageRepository, UserRepository userRepository) {
        this.pageRepository = pageRepository;
        this.userRepository = userRepository;
    }

    public Page addPage(PageRequest request, String ownerEmail) {
    Optional<User> userOpt = userRepository.findByEmail(ownerEmail);
    if (userOpt.isEmpty()) {
        throw new RuntimeException("Owner user not found");
    }

    User owner = userOpt.get();

    if (owner.getRole() != Role.OWNER) {
        throw new RuntimeException("Only users with role OWNER can add pages.");
    }

    Page page = new Page();
    page.setName(request.getName());
    page.setLocation(request.getLocation());
    page.setWorkHours(request.getWorkHours());
    page.setDescription(request.getDescription());
    page.setCategory(request.getCategory());
    page.setType(request.getType());
    page.setOwner(owner);

    return pageRepository.save(page);
    }

    public boolean updatePage(Long pageId, String name, String location, String workHours,
                          String description, String category, String type) {
        Optional<Page> optionalPage = pageRepository.findById(pageId);
        if (optionalPage.isEmpty()) {
            return false;
        }
        Page page = optionalPage.get();

        if (name != null) page.setName(name);
        if (location != null) page.setLocation(location);
        if (workHours != null) page.setWorkHours(workHours);
        if (description != null) page.setDescription(description);
        if (category != null) page.setCategory(category);
        if (type != null) page.setType(type);

        pageRepository.save(page);
        return true;
    }

    public boolean deletePageById(Long id) {
        if (pageRepository.existsById(id)) {
            pageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Page> findAllPages() {
        return pageRepository.findAll();
    }

    public List<Page> findByType(String type) {
        return pageRepository.findByTypeIgnoreCase(type);
    }

    public List<Page> findByCategory(String category) {
        return pageRepository.findByCategoryIgnoreCase(category);
    }

    public List<Page> findByTypeAndCategory(String type, String category) {
        return pageRepository.findByTypeIgnoreCaseAndCategoryIgnoreCase(type, category);
    }
    public List<Page> findByRatingAbove(Double minRating) {
        return pageRepository.findByAverageRatingGreaterThanEqual(minRating);
    }
    

}
