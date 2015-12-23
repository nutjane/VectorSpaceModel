import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;

/**
 * This class implements a set of testing commands for Project 2.
 * Student Edition
 * @author Suppawong Tuarob
 *
 */
public class P2tester {
	long startTime = 0;
	String[] terms = {"tail", "cdna", "tamoxifen", "dna"};
	String d57 = "A computer program that aids in recording, editing, and analysis of the base sequences of DNA and RNA is presented. A tape containing copies of the program and the user manual for it are available at cost.";
	String d86 = "A method for cloning mRNAs has been used which results in a high yield of recombinants containing complete 5'-terminal mRNA sequences. It is not dependent on self-priming to generate double-stranded DNA and therefore the S1 nuclease digestion step is not required. Instead, the cDNA is dCMP-tailed at its 3'-end with terminal deoxynucleotidyl transferase (TdT). The synthesis of the second strand is primed by oligo(dG) hybridized to the 3'-tail. Double-stranded cDNA is subsequently tailed with dCTP and annealed to dGMP-tailed vector DNA. This approach overcomes the loss of the 5'-terminal mRNA sequences and the problem of artifacts which may be introduced into cloned cDNA sequences. Chicken lysozyme cDNA was cloned into pBR322 by this procedure with a transformation efficiency of 5 x 10(3) recombinant clones per ng of ds-cDNA. Sequence analysis revealed that at least nine out of nineteen randomly isolated plasmids contained the entire 5'-untranslated mRNA sequence. The data strongly support the conclusion that the 5'-untranslated region of the lysozyme mRNA is heterogeneous in length.Images";
	String[] queries = {"nucleotide sequence mimicking the intron extremities of premessenger",
			"the interaction of daunomycin with b dna double helices of several methylated deoxynucleotides d c g m5c g d m5c g c g d c g m5c g c g and d m5c g c g m5c g in solution was investigated by 1h nmr spectroscopy at 500 mhz at low temperature t less than 20 degrees c for the tetramer and t less than 40 degrees c for the hexamers several daunomycin dna complexes were observed in slow exchange with the drug free dna duplexes the presence of daunomycin in a self complementary double helix cancels the conformational symmetry of the two strands the proton signals can split into several others owing to the difference between free and intercalated duplexes and to the many possible intercalation sites in a duplex three for a tetramer five for an hexamer a model relating the chemical shifts of splitted proton signals to the various intercalated duplex conformations was given the results show that one daunomycin molecule is associated with one duplex and that it can enter any intercalation site with equal probability no side effects were observed even for very short helices of a tetramer in the case of d c g m5c g the association constant and the dissociation and association rates of the intercalated complex were evaluated"};
	
	//for writing down test results to a file
	StringBuilder report = new StringBuilder();
	
	public P2tester()
	{	//Initialize the timer
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * Test term frequency computation 
	 * @param vt
	 */
	public void testTF(VectorSpaceRetriever vt)
	{
		report.append("@@ Testing term frequency.\n");
		
		
		for(String term: terms)
		{
			report.append("TF(\""+term+"\", d57) = "+vt.getTF(term, d57)+"\n"); 
			report.append("TF(\""+term+"\", d86) = "+vt.getTF(term, d86)+"\n"); 
		}
		
	}
	
	/**
	 * Some test cases for IDF computation
	 * @param vt
	 */
	public void testIDF(VectorSpaceRetriever vt)
	{
		report.append("\n@@ Testing inverse document frequency.\n");

		for(String term: terms)
		{
			report.append("IDF(\""+term+"\") = "+vt.getIDF(term)+"\n"); 
		}
	}
	
	/**
	 * Test converting documents to their vector forms
	 * @param vt
	 */
	public void testVectors(VectorSpaceRetriever vt)
	{
		report.append("\n@@ Testing vector conversion.\n");
		report.append("Query 0: "+Arrays.toString(vt.getVector(queries[0]))+"\n");
		report.append("Query 1: "+Arrays.toString(vt.getVector(queries[1]))+"\n");
		report.append("d57: "+Arrays.toString(vt.getVector(d57))+"\n");
		report.append("d86: "+Arrays.toString(vt.getVector(d86))+"\n");
		
	}
	
	/**
	 * Test computing cosine similarity.
	 * @param vt
	 */
	public void testCosine(VectorSpaceRetriever vt)
	{
		report.append("\n@@ Testing cosine similarity.");
		double A[] = {2.305078863749565, 3.5475321886435656, 2.85438500808362, 3.5475321886435656, 2.9982260443095106};
		double B[] = {3.5475321886435656, 3.200958598363593, 3.5475321886435656, 2.651652454029538, 3.5475321886435656};
		double C[] = {1, 0, 1, 0, 1};
		double D[] = {1, 1, 1, 1, 1};
		report.append("Cosine Similarity between A and A: "+vt.getCosineSimScore(A, A)+"\n");
		report.append("Cosine Similarity between A and B: "+vt.getCosineSimScore(A, B)+"\n");
		report.append("Cosine Similarity between A and C: "+vt.getCosineSimScore(A, C)+"\n");
		report.append("Cosine Similarity between A and D: "+vt.getCosineSimScore(A, D)+"\n");
		report.append("Cosine Similarity between B and C: "+vt.getCosineSimScore(B, C)+"\n");
		report.append("Cosine Similarity between B and D: "+vt.getCosineSimScore(B, D)+"\n");
		report.append("Cosine Similarity between C and D: "+vt.getCosineSimScore(C, D)+"\n");
	}
	
	/**
	 * Test retrieving results from the query
	 * @param vt
	 */
	public void testRetrieve(VectorSpaceRetriever vt)
	{
		report.append("\n@@ Test document retrieval.\n");
		ScoredDoc[] r0 = vt.retrieve(queries[0], 10);
		ScoredDoc[] r1 = vt.retrieve(queries[1], 10);
		report.append("Results for query 0:"+Arrays.toString(r0)+"\n");
		report.append("Results for query 1:"+Arrays.toString(r1)+"\n");
	}
	
	/**
	 * Writing down test results 
	 * @param filename
	 */
	public void writeTestResults(String filename)
	{	
		double estimatedTime = (double)(System.currentTimeMillis() - startTime)/1000.0;
		
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
			writer.write(report.toString());
			writer.write("Total Time used: "+estimatedTime+" seconds\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	
	public static void main(String args[])
	{	
		VectorSpaceRetriever vt = new VectorSpaceRetriever();
		vt.loadIndex("p2_index.txt");
		P2tester tester = new P2tester();
		tester.testTF(vt);
		tester.testIDF(vt);
		tester.testVectors(vt);
		tester.testCosine(vt);
		tester.testRetrieve(vt);
		tester.writeTestResults("test_results.txt");
		
	}

	
	
}
