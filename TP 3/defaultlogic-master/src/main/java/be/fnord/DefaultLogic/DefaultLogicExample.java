package be.fnord.DefaultLogic;

import java.util.HashSet;

import be.fnord.util.logic.DefaultReasoner;
import be.fnord.util.logic.WFF;
import be.fnord.util.logic.defaultLogic.DefaultRule;
import be.fnord.util.logic.defaultLogic.RuleSet;
import be.fnord.util.logic.defaultLogic.WorldSet;

/**
 * Default logic examples. Most of the examples here are from R. Reiter (1980).
 * A logic for default reasoning. Artificial Intelligence, 13:81-132. Notes on
 * output format - if you see the string eeee anywhere in the output take it as
 * an EMPTY set or value. Default rules are written a:b ==> c where a is the
 * prerequisite, b is justification and c is conclusion.
 * ------------------------
 * ------------------------------------------------------
 * ------------------------------------------------
 * <p/>
 * I have made every attempt to test this reasoner; however, have not documented
 * the decision proceedure yet so please take results with a grain of salt.
 *
 * @author Evan Morrison edm92@uowmail.edu.au http://www.fnord.be Apache
 *         License, Version 2.0, Apache License Version 2.0, January 2004
 *         http://www.apache.org/licenses/
 */
public class DefaultLogicExample {

	public static void EXO1W1() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("~A");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("A");
		rule1.setJustificatoin("B");
		rule1.setConsequence("C");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite("A");
		rule2.setJustificatoin("~C");
		rule2.setConsequence("D");
		
		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	// Example 2.11 commitment to assumptions
	// J. P. Delgrande, T. Schaub, and W. K. Jackson (1994). Alternative
	// approaches to default logic. Artificial Intelligence, 70:167-237.

	/**
	 * Output Given the world: (A | B) And the rules [([]):(A) ==> (A)] , [((A |
	 * B)):(~A) ==> (~A)] Possible Extensions Ext: Th(W U (~A)) Ext: Th(W U (A))
	 * Though in Delgrande's paper the solutions is : Th(W U {A}) and Th(W U
	 * {~A, B}).
	 */
	public static void EXO2() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("A");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("A");
		rule1.setJustificatoin("~B");
		rule1.setConsequence("B");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		
		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	// Example 2.11 commitment to assumptions
	// J. P. Delgrande, T. Schaub, and W. K. Jackson (1994). Alternative
	// approaches to default logic. Artificial Intelligence, 70:167-237.

	/**
	 * Output Given the world: ((A) & (B -> C)) And the rules [(A):(B) ==> (B)]
	 * , [(A):(~B) ==> (~B)] , [(~C):(C) ==> (C)] Possible Extensions Ext: Th(W
	 * U (B))
	 */
	public static void EXO1W3() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("A");
		myWorld.addFormula("(~C | ~D)");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("A");
		rule1.setJustificatoin("B");
		rule1.setConsequence("C");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite("A");
		rule2.setJustificatoin("~C");
		rule2.setConsequence("D");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}

	}

	// Example 2.10 continued From
	// J. P. Delgrande, T. Schaub, and W. K. Jackson (1994). Alternative
	// approaches to default logic. Artificial Intelligence, 70:167-237.

	/**
	 * Output Given the world:
	 * <p/>
	 * And the rules [([]):(B) ==> (C)] , [([]):(~B) ==> (D)] Possible
	 * Extensions Ext: Th(W U (C & D))
	 */
	public static void EXO1W2() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("A");
		myWorld.addFormula("~B");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("A");
		rule1.setJustificatoin("B");
		rule1.setConsequence("C");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite("A");
		rule2.setJustificatoin("~C");
		rule2.setConsequence("D");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();
		a.e.println("This example is titled Commitment to assumptions");
		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}

	}

	// Example 2.9 continued From
	// J. P. Delgrande, T. Schaub, and W. K. Jackson (1994). Alternative
	// approaches to default logic. Artificial Intelligence, 70:167-237.

	/**
	 * Output Given the world:
	 * <p/>
	 * And the rules [([]):(B) ==> (C)] , [([]):(~B) ==> (D)] , [([]):(~C) ==>
	 * (E)] , [([]):(~C) ==> (E)] Possible Extensions Ext: Th(W U (C & D)) A
	 * family may decide to do one of two things, go to the beach or to a movie
	 * (C or D) on the weekend, depending on whether it is sunny (B) or not.
	 */
	public static void EXO1W4() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("A");
		myWorld.addFormula("~B & C");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("A");
		rule1.setJustificatoin("B");
		rule1.setConsequence("C");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite("A");
		rule2.setJustificatoin("~C");
		rule2.setConsequence("D");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();
		}

	}

	// Example 2.9 From
	// J. P. Delgrande, T. Schaub, and W. K. Jackson (1994). Alternative
	// approaches to default logic. Artificial Intelligence, 70:167-237.

	public static void EXO3W1() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("A");
		myWorld.addFormula("B");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("(A & B)");
		rule1.setJustificatoin("~C");
		rule1.setConsequence("~C");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	// Example 2.3 From
	// A Logic for Default Reasoning, R. Reiter -- R. Reiter (1980). A logic for
	// default reasoning. Artificial Intelligence, 13:81-132.

	// Example
	public static void EXO3W2() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("A");
		myWorld.addFormula("B");
		myWorld.addFormula("C");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("(A & B)");
		rule1.setJustificatoin("~C");
		rule1.setConsequence("~C");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	// Example 2.2 From
	// A Logic for Default Reasoning, R. Reiter -- R. Reiter (1980). A logic for
	// default reasoning. Artificial Intelligence, 13:81-132.

	// Example 2.6 from
	// A Logic for Default Reasoning, R. Reiter -- R. Reiter (1980). A logic for
	// default reasoning. Artificial Intelligence, 13:81-132.
	public static void example4() {
		WorldSet myWorld = new WorldSet(); // Empty World

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite(a.e.EMPTY_FORMULA);
		rule1.setJustificatoin("A");
		rule1.setConsequence(a.e.NOT + "A");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions ");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	// Example 2.1 From
	// A Logic for Default Reasoning, R. Reiter -- R. Reiter (1980). A logic for
	// default reasoning. Artificial Intelligence, 13:81-132.

	// From
	// A Logic for Default Reasoning, R. Reiter -- R. Reiter (1980). A logic for
	// default reasoning. Artificial Intelligence, 13:81-132.
	public static void example5() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("Spouse_Fred_Mary & Hometown_Fred_Toronto & Employer_Bill_Mary & Hometown_Bill_Vancouver");
		myWorld.addFormula("Hometown_Mary_Vancouver -> ~Hometown_Mary_Toronto");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("Spouse_Fred_Mary & Hometown_Fred_Toronto");
		rule1.setJustificatoin("Hometown_Mary_Toronto");
		rule1.setConsequence("Hometown_Mary_Toronto");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite("Employer_Bill_Mary & Hometown_Bill_Vancouver");
		rule2.setJustificatoin("Hometown_Mary_Vancouver");
		rule2.setConsequence("Hometown_Mary_Vancouver");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	/**
	 * Output Applyig rules to the world Effect:eeee & (B -> (~A & ~C)) ==== (~C
	 * | ~B) & eeee & (~A | ~B) Given the world: (B -> (~A & ~C)) And the rules
	 * [([]):(A) ==> (A)] , [([]):(B) ==> (B)] , [([]):(C) ==> (C)] Possible
	 * Extensions Ext: Th(W U (B)) Ext: Th(W U (A & C))
	 */
	public static void example6() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula("(B -> (~A & ~C))");

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite(a.e.EMPTY_FORMULA);
		rule1.setJustificatoin("A");
		rule1.setConsequence("A");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite(a.e.EMPTY_FORMULA);
		rule2.setJustificatoin("B");
		rule2.setConsequence("B");

		DefaultRule rule3 = new DefaultRule();
		rule3.setPrerequisite(a.e.EMPTY_FORMULA);
		rule3.setJustificatoin("C");
		rule3.setConsequence("C");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);
		myRules.addRule(rule3);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	/**
	 * Output Applyig rules to the world Effect:eeee & eeee ==== eeee Given the
	 * world:
	 * <p/>
	 * And the rules [([]):(C) ==> (~D)] , [([]):(D) ==> (~E)] , [([]):(E) ==>
	 * (~F)] Possible Extensions Ext: Th(W U (~D & ~F))
	 */
	public static void example7() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula(a.e.EMPTY_FORMULA);

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite(a.e.EMPTY_FORMULA);
		rule1.setJustificatoin("C");
		rule1.setConsequence("~D");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite(a.e.EMPTY_FORMULA);
		rule2.setJustificatoin("D");
		rule2.setConsequence("~E");

		DefaultRule rule3 = new DefaultRule();
		rule3.setPrerequisite(a.e.EMPTY_FORMULA);
		rule3.setJustificatoin("E");
		rule3.setConsequence("~F");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);
		myRules.addRule(rule3);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	/**
	 * Output Applyig rules to the world Effect:eeee & eeee ==== eeee Given the
	 * world:
	 * <p/>
	 * And the rules [([]):(C) ==> (~D)] , [([]):(D) ==> (~C)] Possible
	 * Extensions Ext: Th(W U (~D)) Ext: Th(W U (~C))
	 */
	public static void example8() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula(a.e.EMPTY_FORMULA);

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite(a.e.EMPTY_FORMULA);
		rule1.setJustificatoin("C");
		rule1.setConsequence("~D");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite(a.e.EMPTY_FORMULA);
		rule2.setJustificatoin("D");
		rule2.setConsequence("~C");

		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	/**
	 * Output Given the world:
	 * <p/>
	 * And the rules [([]):(A & ~B) ==> (A)] , [([]):(~A & B) ==> (~A)] Possible
	 * Extensions Ext: Th(W U (~A)) Ext: Th(W U (A))
	 */
	public static void EXO6() {
		WorldSet myWorld = new WorldSet(); // Empty World
		myWorld.addFormula(a.e.EMPTY_FORMULA);

		DefaultRule rule1 = new DefaultRule();
		rule1.setPrerequisite("a");
		rule1.setJustificatoin("b");
		rule1.setConsequence("b");

		DefaultRule rule2 = new DefaultRule();
		rule2.setPrerequisite(a.e.EMPTY_FORMULA);
		rule2.setJustificatoin("a");
		rule2.setConsequence("a");

		DefaultRule rule3 = new DefaultRule();
		rule3.setPrerequisite(a.e.EMPTY_FORMULA);
		rule3.setJustificatoin("~a");
		rule3.setConsequence("~a");
		
		RuleSet myRules = new RuleSet();
		myRules.addRule(rule1);
		myRules.addRule(rule2);
		myRules.addRule(rule3);

		DefaultReasoner loader = new DefaultReasoner(myWorld, myRules);
		HashSet<String> extensions = loader.getPossibleScenarios();

		a.e.println("Given the world: \n\t" + myWorld.toString()
				+ "\n And the rules \n\t" + myRules.toString());

		a.e.println("Possible Extensions");
		for (String c : extensions) {
			a.e.println("\t Ext: Th(W U (" + c + "))");
			// Added closure operator
			a.e.incIndent();
			WFF world_and_ext = new WFF("(( " + myWorld.getWorld() + " ) & ("
					+ c + "))");
			a.e.println("= " + world_and_ext.getClosure());
			a.e.decIndent();

		}
	}

	public static void main(String[] args) {

		// Turn on the removal of empty effects from print statements
		a.e.HIDE_EMPTY_EFFECTS_IN_PRINT = true;

		long start = System.currentTimeMillis();
		a.e.println("Default logic examples. \n Most of the examples here are from  R. Reiter (1980). A logic for default reasoning. Artificial Intelligence, 13:81-132.\n Notes on output format - if you see the string eeee anywhere in the output take it as an EMPTY set or value.\n Default rules are written a:b ==> c  where a is the prerequisite, b is justification and c is conclusion.\n------------------------------------------------------------------------------------------------------------------------------\n\n");
		
		EXO1W1();
		a.e.println("--------------------------------------------------------------");		
		EXO1W2();
		a.e.println("--------------------------------------------------------------");
		EXO1W3();
		a.e.println("--------------------------------------------------------------");
		//EXO1W4();
		a.e.println("--------------------------------------------------------------");
		EXO2();
		a.e.println("--------------------------------------------------------------");
		EXO3W1();
		a.e.println("--------------------------------------------------------------");
		EXO3W2();
		a.e.println("--------------------------------------------------------------");
		EXO6();

		long end = System.currentTimeMillis();
		System.out.println("Execution time was " + (end - start) + " ms.");

	}

}
