package br.com.projeto.userdept.dto;

import br.com.projeto.userdept.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @NotNull
    @NotBlank(message = "O nome não pode ser vazio ou em branco.")
    private String nome;

    @NotNull
    @NotBlank(message = "O email não pode ser vazio ou em branco.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotNull
    private DepartmentDTO departmentDTO;

    public static UserDTO from(User user) {
        final var hasDepartment = nonNull(user.getDepartment());

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setNome(user.getName());
        userDTO.setDepartmentDTO(hasDepartment ? DepartmentDTO.from(user.getDepartment()) : null);
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        final var hasDepartment = nonNull(userDTO.getDepartmentDTO());

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getNome());
        user.setDepartment(hasDepartment ? DepartmentDTO.toEntity(userDTO.getDepartmentDTO()) : null);
        return user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }
}
