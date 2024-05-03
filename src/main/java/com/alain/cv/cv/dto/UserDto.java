package com.alain.cv.cv.dto;
import com.alain.cv.cv.entities.Audit;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;
    
    @NotEmpty(message = "El email es requerido")
    @Email(message = "El formato del correo debe ser válido")
    private String email;  

    @NotEmpty(message = "La contraseña es requerida")
    private String password;

    private Audit audit;

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }
}
