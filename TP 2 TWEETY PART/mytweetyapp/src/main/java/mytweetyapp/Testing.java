package mytweetyapp;

import java.io.IOException;
import java.util.ArrayList;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.ml.reasoner.SimpleMlReasoner;
import org.tweetyproject.logics.ml.syntax.MlBeliefSet;
import org.tweetyproject.logics.commons.syntax.Predicate;
import org.tweetyproject.logics.fol.syntax.FolFormula;
import org.tweetyproject.logics.fol.syntax.FolSignature;
import org.tweetyproject.logics.ml.parser.MlParser;

/**
 * Some examples for testing ModalParser and NaiveModalReasoner. Shows how to
 * construct a modal logic knowledge base programmatically and how to query it
 * using the naive reasoner.
 * 
 * @author Anna Gessler
 */
public class Testing {

	public static void main(String[] args) throws ParserException, IOException {
		/*Parse simple BeliefBase from file
		MlParser parser = new MlParser();
		MlBeliefSet b1 = parser.parseBeliefBaseFromFile("src/main/resources/examplebeliefbase2.mlogic");
		FolFormula f1 = (FolFormula) parser.parseFormula("<>(A&&B)");
		System.out.println("Parsed belief base:" + b1 + "\nSignature of belief base:" + b1.getMinimalSignature());
		System.out.println("Parsed formula:" + f1);

		// Parse simple BeliefBase from string
		parser = new MlParser();
		MlBeliefSet b2 = parser.parseBeliefBase("Animal = {penguin,eagle} \n type(Flies(Animal)) \n (Flies(eagle))");
		FolFormula f2 = (FolFormula) parser.parseFormula("(Flies(penguin)) || (!(Flies(penguin)))");
		System.out.println("Parsed belief base:" + b2);
		System.out.println("Parsed formula:" + f2);

		// Parse more complex BeliefBase from file
		parser = new MlParser();
		MlBeliefSet b3 = parser.parseBeliefBaseFromFile("src/main/resources/examplebeliefbase.mlogic");
		System.out.println("Parsed belief base:" + b3 + "\nSignature of belief base:" + b3.getMinimalSignature());

		// Reasoner examples
		SimpleMlReasoner reasoner = new SimpleMlReasoner();
		System.out.println("Answer to query: " + reasoner.query(b1, f1));
		System.out.println("Answer to query: " + reasoner.query(b2, f2));
		*/
		MlParser parser = new MlParser();
		FolSignature sig = new FolSignature();
		sig.add(new Predicate("p",0));
		sig.add(new Predicate("q",0));
		sig.add(new Predicate("a",0));
		sig.add(new Predicate("b",0));
		parser.setSignature(sig);
		MlBeliefSet w1 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n"+"(q) \n");
		MlBeliefSet w2 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n");
		MlBeliefSet w3 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n"+"(q) \n");
		MlBeliefSet w4 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(q) \n");
		MlBeliefSet w5 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n");
		SimpleMlReasoner reasoner = new SimpleMlReasoner();
		FolFormula f1 = (FolFormula) parser.parseFormula("p&&q");
		FolFormula f2 = (FolFormula) parser.parseFormula("[](p)");
		FolFormula f3 = (FolFormula) parser.parseFormula("[](p=>q)");
		FolFormula f4 = (FolFormula) parser.parseFormula("[](q&&(<>(!p)))");		
		MlBeliefSet[] Relation1= {w2,w3,w4};
		MlBeliefSet[] Relation2= {w5};
		MlBeliefSet[] Relation3= {w4};
		MlBeliefSet[] Relation5= {w4};
		System.out.println("========================EXERCICE 2 SERIE 3 : LEOGIQUE MODALE=============================================");
		boolean correct=false;
		for(MlBeliefSet w : Relation1) {
			if(reasoner.query(w, f1)) {
				correct=true;
			}
		}
		System.out.println("M,w1 |== <>(p&&q) : " + correct);
		correct=false;		
		for(MlBeliefSet w : Relation2) {
			if(reasoner.query(w, f2)) {
				correct=true;
			}
		}
		System.out.println("M,w2 |== 7[](p) : " + !correct);		
		System.out.println("M,w3 |== [](p=>q) : "+ reasoner.query(w4, f3));		
		System.out.println("M,w5 |== [](q && <>7p) : "+ reasoner.query(w4, f4));
		
		/*System.out.println("\n========================EXERCICE 4 SERIE 3 : LEOGIQUE MODALE=============================================");
		
		w1= parser.parseBeliefBase("type (a)\n type(b)\n"+"(a)\n"+"(b) \n");
		w2= parser.parseBeliefBase("type (a)\n type(b)\n"+"(a)\n"+"(b) \n");
		w3= parser.parseBeliefBase("type (a)\n type(b)\n"+"(b) \n");
		w4= parser.parseBeliefBase("type (a)\n type(b)\n"+"(b) \n");
		w5= parser.parseBeliefBase("type (a)\n type(b)\n"+"(a) \n");
		
		f1 = (FolFormula) parser.parseFormula("[] (!(a) || !(b))");
		f2 = (FolFormula) parser.parseFormula("[](<>(!b))");
		f3 = (FolFormula) parser.parseFormula("!(<>(a => b))");
		f4 = (FolFormula) parser.parseFormula("[](<>(b))");
		
		MlBeliefSet[] R1= {w3,w5};
		MlBeliefSet[] R2= {w3,w1};
		MlBeliefSet[] R3= {w4,w5};
		System.out.println("M,t1 |== G(7a || 7b) : " + reasoner.query(w5, f1));
		System.out.println("M,t3 |== HF7b : "+ reasoner.query(w3, f2));		
		System.out.println("M,t2 |==7F(a=>b) : "+reasoner.query(w2, f3));
		System.out.println("M,t5|== G7Fb : "+reasoner.query(w5, f4));*/

		
		
	}

}