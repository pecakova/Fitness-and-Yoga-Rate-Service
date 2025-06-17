package com.example.yogafitnessrating.model;

import jakarta.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score; 

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
    public Integer getScore() {
    return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rating{id=" + id + ", score=" + score + ", pageId=" + (page != null ? page.getId() : null) + '}';
    }
}
