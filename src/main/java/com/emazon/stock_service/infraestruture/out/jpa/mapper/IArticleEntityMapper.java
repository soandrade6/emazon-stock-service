package com.emazon.stock_service.infraestruture.out.jpa.mapper;

import com.emazon.stock_service.domain.model.Article;
import com.emazon.stock_service.infraestruture.out.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleEntityMapper {
    ArticleEntity toEntity(Article article);
    Article toArticle(ArticleEntity articleEntity);
}
