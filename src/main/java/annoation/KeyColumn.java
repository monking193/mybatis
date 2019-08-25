package annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface KeyColumn {
    /**
     * 是否使用生成的主键,默认false
     *
     */
    boolean useGeneratedKeys() default false;
}
