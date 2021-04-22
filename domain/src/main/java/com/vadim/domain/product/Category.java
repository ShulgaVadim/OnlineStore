package com.vadim.domain.product;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Category {

    @NonNull
    protected String categoryName;
    public List<Product> products;
    @NonNull
    public int categoryId;

}

