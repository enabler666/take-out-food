package com.enabler.takeFood.dto;

import com.enabler.takeFood.entity.Setmeal;
import com.enabler.takeFood.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
