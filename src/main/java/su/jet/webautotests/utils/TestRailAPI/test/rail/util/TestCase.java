package su.jet.webautotests.utils.TestRailAPI.test.rail.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;


@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE})
public @interface TestCase {
    String name() default "";

    String description() default "";

    String user() default "";

    String password() default "";

    String testId() default "";

    String testRailCaseId() default "";
    boolean testRailDeployEnable() default true;
}
