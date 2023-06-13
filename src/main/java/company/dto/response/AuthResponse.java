package company.dto.response;

import company.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String token;
    private Role role;
    private String email;

}
