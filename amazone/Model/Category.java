package org.example.amazone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
public class Category {
    @NotNull(message = "the id cannot be empty")
    private Integer id ;
    @NotEmpty(message = "the name cannot be empty")
    @Length(min = 4,message = "the name should be more than 3 letters")
    private String name;
}
