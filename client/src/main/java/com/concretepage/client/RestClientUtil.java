package com.concretepage.client;

import com.concretepage.client.entity.Article;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RestClientUtil {

    public void getArticleByIdDemo()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article/{id}";

        HttpEntity<String> requestEntity =
                new HttpEntity<String>(headers);

        ResponseEntity<Article> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET,
                        requestEntity, Article.class, 2);
        Article article = responseEntity.getBody();

        System.out.println("Id:" + article.getArticleId() + ", Title: " + article.getTitle()
        + ", Category: " + article.getCategory());
    }

    public void getAllArticlesDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/articles";

        HttpEntity<String> requestEntity =
                new HttpEntity<String>(headers);

        ResponseEntity<Article[]> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET,
                        requestEntity, Article[].class);
        Article[] articles = responseEntity.getBody();

        for(Article article : articles)
        {
            System.out.println(
                    "Id: " + article.getArticleId() +
                    ", Title: " + article.getTitle() +
                    ", Category: " + article.getCategory());
        }
    }

    public void addArticleDemo()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article";
        Article objArticle = new Article();
        objArticle.setTitle("Spring REST Security using Hibernate");
        objArticle.setCategory("Spring");

        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);

        System.out.println(uri.getPath());
    }

    public void updateArticleDemo()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article";
        Article objArticle = new Article();

        objArticle.setArticleId(2);
        objArticle.setTitle("Update: Java concurrency");
        objArticle.setCategory("Java");
        objArticle.setCategory("Java");

        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);

        restTemplate.put(url, requestEntity);

        System.out.println("Se ha ejecutado el update correctamente");
    }


    public void deleteArticleDemo()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article/{id}";

        HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 14);

        System.out.println("Se ha ejecutado el delete correctamente");
    }

    public static void main(String[] args)
    {
        RestClientUtil util = new RestClientUtil();
        util.getArticleByIdDemo();
        util.getAllArticlesDemo();
        //util.addArticleDemo();
        util.updateArticleDemo();
        util.deleteArticleDemo();
    }
}
