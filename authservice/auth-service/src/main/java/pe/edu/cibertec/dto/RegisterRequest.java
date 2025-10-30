package pe.edu.cibertec.dto;

import lombok.Data;
import pe.edu.cibertec.model.Role;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role = Role.ROLE_USER;
}