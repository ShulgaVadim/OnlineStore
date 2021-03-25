package com.vadim.domain.product;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public abstract class Category {

    @NonNull
    protected String categoryName;
    public List<Product> products;
}

