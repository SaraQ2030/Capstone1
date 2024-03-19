package org.example.amazone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;

@AllArgsConstructor
@Data

public class MerchantStock {
    @NotNull(message = "the id cannot be empty")
    private Integer id ;
   @NotNull(message = "the product id cannot be empty")

    private Integer product_id;
   @NotNull(message = "the merchant id cannot be empty")
 private Integer merchant_id;
 @NotNull(message = "the stock cannot be empty")
   @Min(value = 11 ,message = "the stock should be at least 11 at start ")
   private Integer stock;

}
