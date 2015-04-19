package es.udc.tfg_es.clubtriatlon.web.services;
/* BSD License */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthenticationPolicy {
	AuthenticationPolicyType value() default AuthenticationPolicyType.ALL_USERS;
}
