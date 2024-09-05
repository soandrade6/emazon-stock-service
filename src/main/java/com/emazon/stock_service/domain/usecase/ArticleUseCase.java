package com.emazon.stock_service.domain.usecase;

import com.emazon.stock_service.domain.api.IArticleServicePort;
import com.emazon.stock_service.domain.model.Article;
import com.emazon.stock_service.domain.spi.IArticlePersistencePort;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticleServicePort articleServicePort, IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void createArticle(Article article) {
        if (!article.isValidCategoryCount()) {
            throw new IllegalArgumentException("El artículo debe tener entre 1 y 3 categorías.");
        }
        if (!article.hasUniqueCategories()) {
            throw new IllegalArgumentException("Las categorías no pueden repetirse.");
        }

        articlePersistencePort.saveArticle(article);

    }
}
