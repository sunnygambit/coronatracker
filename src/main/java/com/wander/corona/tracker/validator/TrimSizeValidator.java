package com.wander.corona.tracker.validator;

import io.micrometer.core.instrument.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrimSizeValidator implements ConstraintValidator<TrimSize, String> {
    private String minVal;
    private String maxVal;

    @Override
    public void initialize(TrimSize requiredValues) {
        minVal = Integer.toString(requiredValues.min());
        maxVal = Integer.toString(requiredValues.max());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean valid = true;

        if (StringUtils.isNotBlank(value) && value.trim().length() > 0) {
            int minSize = Integer.parseInt(minVal);
            int maxSize = Integer.parseInt(maxVal);

            int lengthOfVal = value.trim().length();
            if (!(lengthOfVal >= minSize && lengthOfVal <= maxSize)) {
                valid = false;
            }
        }

        return valid;
    }
}
