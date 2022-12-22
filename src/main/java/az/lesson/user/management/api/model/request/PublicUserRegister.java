package az.lesson.user.management.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicUserRegister {
    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    // @MatchPassword
    private String password;

    // @MatchPassword
    private String confirmPassword;
}

