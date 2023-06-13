package company.service;

import company.dto.request.SignInRequest;
import company.dto.request.SignUpRequest;
import company.dto.response.AuthResponse;

public interface AuthenticationService {

    AuthResponse signUp(SignUpRequest signUpRequest);
    AuthResponse signIn(SignInRequest signInRequest);
}
