package pl.rafalmiskiewicz.ADOZL.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.rafalmiskiewicz.ADOZL.constants.AdozlConstants;
import pl.rafalmiskiewicz.ADOZL.hours.Hour;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.utilities.AppdemoUtils;

public class HourAddValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Hour h = (Hour) obj;

        if(h.getOnlyDate_from_string()==null||h.getOnlyDate_from_string().isEmpty())
            errors.rejectValue("onlyDate_from_string", "error.date.empty");

        if(h.getOnlyHour_from_string()==null||h.getOnlyHour_from_string().isEmpty())
            errors.rejectValue("hour_from", "error.hour_to.empty");

        if(h.getOnlyHour_to_string()==null||h.getOnlyHour_to_string().isEmpty())
            errors.rejectValue("hour_to", "error.hour_from.empty");

		if (h.getHour_from()!=null) {
			boolean isMatch = AppdemoUtils.checkDate(AdozlConstants.HOUR_PATTERN, h.getHour_from());
			if(!isMatch) {
				errors.rejectValue("hour_from", "hour.error.dateNotMatch");
			}
		}

    }

}
