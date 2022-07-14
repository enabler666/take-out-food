package com.enabler.takeFood.dto;

import com.enabler.takeFood.entity.Dish;
import com.enabler.takeFood.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enabler
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
