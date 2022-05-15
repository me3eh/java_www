package com.example.store3.data;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor
public class Item {
    @NotNull
    @Size(min=1, max=20, message = "Name must contain minimum 1 and maximum 20 characters")
    private final String name;
    @DecimalMin(value="0.00", message="Minimum 0 for price")
    @DecimalMax(value="1000.00", message="Maximum 1000 for price")
    @Digits(integer=6, fraction=2)
    private final float price;
    private final ItemCategory category;
}
