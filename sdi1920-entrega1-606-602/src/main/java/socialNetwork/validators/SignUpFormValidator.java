package socialNetwork.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import socialNetwork.entities.User;
import socialNetwork.services.UsersService;

@Component
public class SignUpFormValidator implements Validator {
	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
		
		if (usersService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Error.signup.email.duplicate");
		}
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
		}
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			System.out.println("Hola");
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
		}
	}
}
