package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义手机号约束注解关联验证器
 * @author cbpro
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

   /**
    * 自定义校验逻辑方法
    * @param value
    * @param context
    * @return
    */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            value = "";
        }
       String check = "158\\d{8}";
       Pattern regex = Pattern.compile(check);
       Matcher matcher = regex.matcher(value);
       return matcher.matches();
    }
}
