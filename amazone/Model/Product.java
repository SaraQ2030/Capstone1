package org.example.amazone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Product {
@NotNull(message = "the id cannot be empty")
    private Integer id;
    @NotEmpty(message = "the name cannot be empty")
    @Length(min = 4,message = "the length should be more than 3")
    private String name;
    @NotNull(message = "the price cannot be empty")
    @Positive
    private double price;
   @NotNull(message = "the category id  cannot be empty")
  private Integer category_id;

}
