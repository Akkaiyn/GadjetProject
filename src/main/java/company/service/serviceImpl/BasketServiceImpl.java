package company.service.serviceImpl;

import company.dto.response.SimpleResponse;
import company.entity.Basket;
import company.entity.User;
import company.repository.BasketRepository;
import company.repository.UserRepository;
import company.service.BasketService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

    private User getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.getUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + "don't exists!"));

    }
    @Override
    public SimpleResponse createBasket() {
        Basket basket = Basket.builder()
                .productList(null)
                .user(getAuthentication())
                .build();
        basketRepository.save(basket);
        getAuthentication().setBasket(basket);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Basket with id: " + basket.getId()+ "was created for user with id: " +
                        getAuthentication().getId() + " and role " + getAuthentication().getRole()))
                .build();
    }
}
