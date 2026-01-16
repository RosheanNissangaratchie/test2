package com.summerwork.test2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("BOOK")
public record Book(
        @Id         // Spring Data JDBC annotation
        Integer id,
        String title,
        Integer pages,
        String author
) {}