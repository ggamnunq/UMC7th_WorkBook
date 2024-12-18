package umc.spring.validation.annotation;

import io.swagger.v3.oas.annotations.Parameter;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(hidden = true)
public @interface PageValid {

    String name() default "page";
    int defaultValue() default 0;
    boolean required() default false;
}
