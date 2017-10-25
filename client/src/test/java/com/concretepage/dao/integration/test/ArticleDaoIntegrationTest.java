package com.concretepage.dao.integration.test;

import com.concretepage.client.dao.IArticleDAO;
import com.concretepage.client.entity.Article;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleDaoIntegrationTest  {

    @Autowired
    private IArticleDAO articleDAO;

    private static int idAlumno = 0;

    private static int idArticle = 0;

    @Test
    public void testGetAllArticles() throws Exception {
        List<Article> articleList = articleDAO.getAllArticles();
        assertTrue(articleList.size() > 0);
    }

    @Test
    //@Transactional
    //@Rollback(false)
    public void test1AddArticle() throws Exception {
        Article article = new Article();
        article.setCategory("news");
        article.setTitle("new title");
        articleDAO.addArticle(article);

        idArticle = article.getArticleId();

        assertTrue(article.getArticleId() > 0);
    }

    @Test
    //@Transactional
    //@Rollback(false)
    public void test2UpdateArticle() {
        Article article = articleDAO.getArticleById(idArticle);
        article.setTitle("update");
        article.setCategory("update");

        articleDAO.updateArticle(article);

        Article article2 = articleDAO.getArticleById(idArticle);

        assertTrue(article.getTitle() == article2.getTitle());
        //assertSame(article.getTitle() + "," + article2.getTitle(), article.getTitle() , article2.getTitle());
    }

    @Test
    //@Transactional
    //@Rollback(false)
    public void test4deleteArticle()
    {
        Article article = articleDAO.getArticleById(idArticle);

        articleDAO.deleteArticle(article.getArticleId());

        assertNull(articleDAO.getArticleById(article.getArticleId()));
    }
}

