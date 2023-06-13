package company.service.serviceImpl;

import company.config.JwtFilter;
import company.config.JwtService;
import company.dto.request.SignInRequest;
import company.dto.request.SignUpRequest;
import company.dto.response.AuthResponse;
import company.entity.User;
import company.repository.UserRepository;
import company.service.AuthenticationService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
 private final UserRepository userRepository;
 private final PasswordEncoder passwordEncoder;
 private final JwtService jwtService;
    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
if (userRepository.existsByEmail(signUpRequest.getEmail())){
    throw new EntityExistsException("User with email: " + signUpRequest.getEmail() + "already exists!");
}
    User user = User
            .builder()
            .firstName(signUpRequest.getFirstName())
            .lastName(signUpRequest.getLastName())
            .email(signUpRequest.getEmail())
            .password(passwordEncoder.encode(signUpRequest.getPassword()))
            .createdDate(ZonedDateTime.now())
            .updatedDate(null)
            .role(signUpRequest.getRole())
            .build();
        String jwtToken = jwtService.generateToken(user);
        userRepository.save(user);
        return AuthResponse
                .builder()
                .id(user.getId())
                .token(jwtToken)
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }
    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email: " +
                        signInRequest.getEmail() + " doesn't found!"));
        if(signInRequest.getEmail().isBlank()){
            throw new BadCredentialsException("Email doesn't exist");}
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Incorrect password! ");
        }
        String jwtToken =  jwtService.generateToken(user);

        return AuthResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }
}
