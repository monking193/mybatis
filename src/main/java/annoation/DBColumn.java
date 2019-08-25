package annoation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DBColumn {
    String value() default ""; //数据库中的字段名
    String insertIfNull() default "";  // 示例   100,'abc' --字符串注意带引号, defalut ,now()
    String updateIfNull() default "";
}
