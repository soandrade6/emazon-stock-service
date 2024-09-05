package com.emazon.stock_service.domain.api;

import com.emazon.stock_service.domain.model.Article;

public interface IArticleServicePort {

    void createArticle(Article article);
}
