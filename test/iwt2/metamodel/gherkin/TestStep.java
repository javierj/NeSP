package iwt2.metamodel.gherkin;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStep {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createGivenStep() {
		String stepText = "Given X";
		Step step = Step.createStep(stepText);
		
		assertTrue((step instanceof Given));
		assertEquals("X", step.getAction());
	}

	@Test
	public void createWhenStep() {
		String stepText = "When X";
		Step step = Step.createStep(stepText);
		
		assertTrue((step instanceof When));
		assertEquals("X", step.getAction());
	}
	
	
	@Test
	public void createThenStep() {
		String stepText = "Then X";
		Step step = Step.createStep(stepText);
		
		assertTrue((step instanceof Then));
		assertEquals("X", step.getAction());
	}

	
	@Test
	public void createIsCaseIndependent() {
		
		assertTrue((Step.createStep("Then X") instanceof Then));
		assertTrue((Step.createStep("then X") instanceof Then));
		assertTrue((Step.createStep("THEN X") instanceof Then));
		
	}

}
