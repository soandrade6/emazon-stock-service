package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.ArticleRequestDto;

public interface IArticleHandler {

    void createArticle(ArticleRequestDto articleRequestDto);
}
