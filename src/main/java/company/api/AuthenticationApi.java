package company.api;

import company.dto.request.SignInRequest;
import company.dto.request.SignUpRequest;
import company.dto.response.AuthResponse;
import company.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationApi {
    private final AuthenticationService authenticationService;
@PostMapping("/signUp")
    public AuthResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    public AuthResponse signIn(@RequestBody SignInRequest signInRequest){
    return authenticationService.signIn(signInRequest);
    }
}
