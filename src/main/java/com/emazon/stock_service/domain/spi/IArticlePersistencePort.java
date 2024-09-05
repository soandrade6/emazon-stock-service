package com.emazon.stock_service.domain.spi;

import com.emazon.stock_service.domain.model.Article;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
}
