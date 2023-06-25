package ApiUser.configs.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UserSenhaConstraintImpl.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserSenhaConstraint {
	String message() default "A senha não pode ter espaço em branco...";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
