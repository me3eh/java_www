package com.example.store3.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemCategory {
    @NotNull
    @Size(min=1, max=20, message="Length of category name must be minimum 1 and maximum 20")
    private final String name;
    public String toString(){
        return name;
    }
}
