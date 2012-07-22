package axelson.vaadin.builder.factory.listener

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

import org.codehaus.groovy.transform.GroovyASTTransformationClass

@java.lang.annotation.Documented
@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.TYPE])
@GroovyASTTransformationClass(classes = [PluggableListenerASTTransformation])
public @interface PluggableListener {
	Class value()
}
