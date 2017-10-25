package com.concretepage.service;

import com.concretepage.client.entity.Article;

import java.util.List;

public interface IArticleService {

    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    boolean addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
}
