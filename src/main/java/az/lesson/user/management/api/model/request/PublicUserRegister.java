package az.lesson.user.management.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicUserRegister {
    private String username;
    private String fullName;
    private String password;
}