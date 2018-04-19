package org.apache.nifi.components;

/**
 *
 * @author unattributed
 */
public interface Validator {

    /**
     * Validator object providing validation behavior in which validation always
     * fails
     */
    Validator INVALID = new Validator() {
        @Override
        public ValidationResult validate(final String subject, final String input, final ValidationContext context) {
            return new ValidationResult.Builder().subject(subject).explanation(String.format("'%s' is not a supported property", subject)).input(input).build();
        }
    };

    /**
     * Validator object providing validation behavior in which validation always
     * passes
     */
    Validator VALID = new Validator() {
        @Override
        public ValidationResult validate(final String subject, final String input, final ValidationContext context) {
            return new ValidationResult.Builder().subject(subject).input(input).valid(true).build();
        }
    };

    /**
     * @param subject what is being validated
     * @param input the string to be validated
     * @param context the ValidationContext to use when validating properties
     * @return ValidationResult
     * @throws NullPointerException of given input is null
     */
    ValidationResult validate(String subject, String input, ValidationContext context);
}
