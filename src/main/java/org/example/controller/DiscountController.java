package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DiscountDto;
import org.example.service.DiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("/makeDiscount")
    public String makeDiscount(DiscountDto discountDto) {
        if (
            discountService.makeDiscountByCategory(discountDto.getDiscountAmount(),
                                                    discountDto.getCategory(),
                                                    discountDto.getFinishedAt())
        ) {
            return "redirect:/Profile?successAdded=true";
        }
        return "redirect:/Profile?successAdded=false";
    }
}
