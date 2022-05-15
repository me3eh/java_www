package com.example.store3.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor
public class Item {
    private final String name;
    private final float price;
    private final ItemCategory category;
}
