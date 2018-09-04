package md.ramaiana.ramaianawebapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";

    public void initialize(ValidPassword constraint) {
    }

    public boolean isValid(final String password, ConstraintValidatorContext context) {
        return password.matches(PASSWORD_PATTERN);
    }

}
