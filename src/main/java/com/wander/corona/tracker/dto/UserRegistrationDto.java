package com.wander.corona.tracker.dto;

import com.wander.corona.tracker.validator.FieldMatch;
import com.wander.corona.tracker.validator.TrimSize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
@Getter
@Setter
public class UserRegistrationDto {

    @NotEmpty
    @TrimSize(min = 1, max = 20,message = "First name maximum length is 20")
    private String firstName;

    @NotEmpty
    @TrimSize(min=1, max = 20,message = "Last name maximum length is 20")
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @AssertTrue
    private Boolean terms;
}
