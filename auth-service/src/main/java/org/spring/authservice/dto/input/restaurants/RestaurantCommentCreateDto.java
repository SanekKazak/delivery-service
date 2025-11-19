package org.spring.authservice.dto.input.restaurants;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCommentCreateDto {
    @NotNull(message = "stars is null")
    @Min(value = 0, message = "less than 0")
    @Max(value = 5, message = "more than 5")
    private Integer stars;
    private String content;
    private UUID creator;
}
