package demos;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class JtwigDemoScenario {

	    public static void main(String[] args) {
	        JtwigTemplate template = JtwigTemplate.classpathTemplate("resources/templates/example.twig");
	        JtwigModel model = JtwigModel.newModel().with("var", "World");

	        template.render(model, System.out);
	    }
	
}
