package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(CreateDocumentsTest.class);
		suite.addTestSuite(CreateMembresTest.class);
		suite.addTestSuite(DeconnectionTest.class);
		suite.addTestSuite(DocumentARenoverTest.class);
		suite.addTestSuite(DocumentEmpruntableTest.class);
		suite.addTestSuite(DocumentPresentTest.class);
		suite.addTestSuite(EstEnRetardTest.class);
		suite.addTestSuite(GetDocumentByIdTest.class);
		suite.addTestSuite(GetIndexEmpruntByIdTest.class);
		suite.addTestSuite(GetIndexMediathequeByIdTest.class);
		suite.addTestSuite(GetMembreByIdTest.class);
		suite.addTestSuite(QuestiontionTest.class);
		suite.addTestSuite(RendreDocumentTest.class);
		suite.addTestSuite(RendreRetardTest.class);
		suite.addTestSuite(RetardTest.class);
		suite.addTestSuite(RetourTest.class);
		suite.addTestSuite(SelectionTest.class);
		//$JUnit-END$
		return suite;
	}

}
