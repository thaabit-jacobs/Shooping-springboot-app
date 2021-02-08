package com.shopping.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductsList {

    @Size(min = 1, message = "You must at least choose 1 product")
    @NotNull
    private List<Long> productIds;
}
