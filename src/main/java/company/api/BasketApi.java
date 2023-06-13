package company.api;

import company.dto.response.SimpleResponse;
import company.service.BasketService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baskets")
@RequiredArgsConstructor
public class BasketApi {
    private final BasketService basketService;
    @PermitAll
    @PostMapping
    public SimpleResponse createBasket(){
       return basketService.createBasket();
    }
}
