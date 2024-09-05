package com.emazon.stock_service.infraestruture.out.jpa.adapter;

import com.emazon.stock_service.domain.model.Article;
import com.emazon.stock_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_service.infraestruture.out.jpa.entity.ArticleEntity;
import com.emazon.stock_service.infraestruture.out.jpa.mapper.IArticleEntityMapper;
import com.emazon.stock_service.infraestruture.out.jpa.repository.IArticleRepository;

public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;

    public ArticleJpaAdapter(IArticleRepository articleRepository, IArticleEntityMapper articleEntityMapper) {
        this.articleRepository = articleRepository;
        this.articleEntityMapper = articleEntityMapper;
    }

    @Override
    public void saveArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        articleRepository.save(articleEntity);
    }
}
