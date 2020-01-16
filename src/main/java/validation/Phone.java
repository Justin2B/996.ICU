package validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义手机号注解
 * @author cbpro
 */
@Documented
//注解的作用目标
@Target({ElementType.FIELD})
//注解的保留策略
@Retention(RetentionPolicy.RUNTIME)
//与注解关联的验证器
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    /**
     * 约束注解验证时的输出信息
     */
    String message() default "手机号校验错误";

    /**
     * 约束注解在验证时所属组
     */
    Class<?>[] groups() default {};

    /**
     * 约束注解的有效负载
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}
