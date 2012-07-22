package axelson.vaadin.builder.factory.listener

abstract class Pluggable implements Serializable {
	static final long serialVersionUID = 1L
	
	Closure strategy
	
	void execute(def e) {
		strategy.call(e)
	}
}
