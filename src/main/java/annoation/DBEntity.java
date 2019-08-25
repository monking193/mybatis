package annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DBEntity {
    /**
     * 数据库库名
     * @return
     */
    String database() default "";
    /**
     * 数据库表名
     * @return
     */
    String value();

}

