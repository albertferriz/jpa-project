package com.concretepage.dao.integration.test;

import com.concretepage.client.dao.IArticleDAO;
import com.concretepage.client.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleDaoIntegrationTest  {

    @Autowired
    private IArticleDAO articleDAO;

    @Test
    public void testGetAllArticles() throws Exception {
        List<Article> articleList = articleDAO.getAllArticles();
        assertTrue(articleList.size() > 0);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddArticle() throws Exception {
        Article article = new Article();
        article.setCategory("news");
        article.setTitle("new title");
        articleDAO.addArticle(article);

        assertTrue(article.getArticleId() > 0);
    }

}

