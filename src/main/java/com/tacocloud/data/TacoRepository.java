package com.tacocloud.data;

import com.tacocloud.model.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
