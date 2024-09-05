package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.ArticleRequestDto;
import com.emazon.stock_service.application.mapper.IArticleRequestMapper;
import com.emazon.stock_service.domain.api.IArticleServicePort;

public class ArticleHandler  implements IArticleHandler{
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;

    public ArticleHandler(IArticleServicePort articleServicePort, IArticleRequestMapper articleResquestMapper) {
        this.articleServicePort = articleServicePort;
        this.articleRequestMapper = articleResquestMapper;
    }

    @Override
    public void createArticle(ArticleRequestDto articleRequestDto) {
        articleServicePort.createArticle(articleRequestMapper.toArticle(articleRequestDto));
    }
}
