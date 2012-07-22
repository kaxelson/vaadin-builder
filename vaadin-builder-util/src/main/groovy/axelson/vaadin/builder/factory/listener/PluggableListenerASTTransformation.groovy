package axelson.vaadin.builder.factory.listener

import groovyjarjarasm.asm.Opcodes

import java.lang.reflect.Method

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.ClassExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class PluggableListenerASTTransformation implements ASTTransformation {
	@Override
	public void visit(ASTNode[] nodes, SourceUnit source) {
        if (!(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException(
                String.format(
                    "Internal error: wrong types: %s / %s. Expected: AnnotationNode / AnnotatedNode", 
                    nodes[0].getClass(), 
                    nodes[1].getClass())
                )
        }
		
		AnnotationNode node = nodes[0]
		AnnotatedNode parent = nodes[1]

		assert parent instanceof ClassNode
		if (parent instanceof ClassNode) {
			ClassNode classNode = parent
			classNode.superClass = ClassHelper.make(Pluggable)
			classNode.addInterface(ClassHelper.make(Serializable))
			classNode.addField('serialVersionUID', Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL, ClassHelper.long_TYPE, new ConstantExpression(1L, true))
			Expression member = node.getMember('value')
			if (member instanceof ClassExpression) {
				classNode.addInterface(member.type)
				classNode.addMethod(createListenerMethod(member.type))
			}
		}
	}
	
	private MethodNode createListenerMethod(ClassNode listenerType) {
		assert listenerType.isInterface()
		assert listenerType.methods.size() == 1
		MethodNode listenerMethod = listenerType.methods[0]
		assert listenerMethod.parameters.size() == 1
		ClassNode eventType = listenerMethod.parameters[0].type
		
		def nodes = new AstBuilder().buildFromSpec {
		    method(listenerMethod.name, Opcodes.ACC_PUBLIC, void) {
		        parameters {
		            parameter 'event': eventType.getTypeClass()
		        }
		        exceptions {}
		        block {
		            owner.expression.addAll new AstBuilder().buildFromCode {
						strategy.call(event)
						return
//		                onEvent(event)
		            }
		        }
		        annotations {}
		    }
		}
		assert nodes.size() == 1 && nodes[0] instanceof MethodNode
		return nodes[0]
	}
}
