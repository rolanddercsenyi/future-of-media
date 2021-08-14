package futureofmedia.trial.validator;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Saját telefonszám validátor @annotationnal
 */
public class PhoneNumberValidator implements ConstraintValidator<ContactPhoneNumber, String> {

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public void initialize(ContactPhoneNumber contactNumber) {
    }

    /**
     * Validálja a telefonszámot, lehet-e parsolni, utána valid-e a telefonszám
     *
     * @param constraintField feldolgozandó érték
     * @param cxt             nincs hasnzálatban
     * @return true ha valid, false ha nem valid
     */
    @Override
    public boolean isValid(String constraintField, ConstraintValidatorContext cxt) {
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(constraintField, "HU");
            return phoneNumberUtil.isValidNumberForRegion(phoneNumber, "HU");
        } catch (NumberParseException e) {
            return false;
        }
    }

}