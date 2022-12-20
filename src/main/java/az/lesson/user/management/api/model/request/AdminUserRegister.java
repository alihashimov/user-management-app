package az.lesson.user.management.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRegister {
    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    // @MatchPassword
    private String password;

    // @MatchPassword
    private String confirmPassword;

    @NotNull
    private Long roleId;
}