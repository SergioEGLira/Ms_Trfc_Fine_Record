package ApiUser.configs.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserSenhaConstraintImpl implements ConstraintValidator<UserSenhaConstraint, String> {

	@Override
	public void initialize(UserSenhaConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
		if (password == null || password.trim().isEmpty() || password.contains(" ")) {
			return false;
		}
		return true;
	}
}
