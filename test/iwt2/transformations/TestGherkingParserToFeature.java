package iwt2.transformations;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.events.GherkinDocumentEvent;
import gherkin.events.SourceEvent;
import gherkin.stream.GherkinEvents;
import iwt2.metamodel.gherkin.Feature;
import iwt2.metamodel.gherkin.Scenario;


public class TestGherkingParserToFeature {

	ScenarioDefinition scenario;
	gherkin.ast.Feature gherkin_feature;
	final String featName = "Sign up in league";
	final String featDescription = "As a new player named Newt \n"+
			"I want to sign myself into the league \n"+
			"In order to play matches and have a classification";
	
	@Before
	public void setUp() throws Exception {
		
		String textFeature = "Feature: "+featName+" \n"+
				
			featDescription + " \n" + 	
			"Scenario: Bad battletag id  \n"+
			"	Given Signing up a new user  \n"+
			"	When Newt introduces Abs#123 as battletag  \n"+
			"	Then System abort fue the battletag lack one figure";
		
		SourceEvent sourceEvent = new SourceEvent("No URI", textFeature);
		
		 //GherkinDocument gherkinDocument = parser.parse(sourceEvent.data, matcher);
       
       GherkinEvents gherkinEvents = new GherkinEvents(false, true, false);
       
		GherkinDocumentEvent event = (GherkinDocumentEvent)gherkinEvents.iterable(sourceEvent).iterator().next();
		

		
		gherkin_feature =  event.document.getFeature();
		scenario =gherkin_feature.getChildren().get(0);
		//System.out.println();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFeatureToFeature() {
		Feature my_feat = transform(/*gherkin_feature*/);
		assertEquals(featName, my_feat.getName());
		
		assertEquals("I want to sign myself into the league", my_feat.getI_want());
		
		assertEquals("In order to play matches and have a classification", my_feat.getInOrderTo());
		
		assertEquals(featDescription, my_feat.getDescription());
	}
	
	@Test
	public void scenarioDefinitionToScenario() {
		Feature my_feat = transform();
		
		assertEquals(1, my_feat.scenarios().size());
	}
	
	
	private Feature transform() {
		GherkinParserToFeature transform = new GherkinParserToFeature();
		Feature my_feat = transform.transform(gherkin_feature);
		return my_feat;
	}

}
