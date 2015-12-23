//******************** DO NOT MODIFY THIS FILE ***********************//
import java.util.Arrays;
import java.util.Vector;
import org.tartarus.snowball.ext.PorterStemmer;

public class DocUtils {
	
	/**
	 * 
	 * @param doc
	 * @return
	 */
	public static String[] tokenize(String doc)
	{
		if(doc == null) return null;
		//lower casing and removing punctuation
		String[] terms = doc.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
		PorterStemmer stemmer = new PorterStemmer();
		Vector<String> tokens = new Vector<String>();
		for(String term: terms)
		{
			stemmer.setCurrent(term);
			stemmer.stem();
			term = stemmer.getCurrent();
			if(!term.isEmpty()) tokens.add(term);
		}
		
		return tokens.toArray(new String[0]);
	}
	
	public static void main(String args[])
	{
		System.out.println(Arrays.toString(tokenize("360 MHz measurements of chemical shifts, 3J1'-2', and T1 as a function of temperature for various protons of the hexanucleotide 2'-OMeGpApApYpAppsi from torula yeast tRNAphe have revealed a unique involvement of the Yt base in the structure and conformation of this oligonucleotide. Whereas the adenosine residues in the anticodon triplet are relatively stable to temperature increase, the Yt readily undergoes destacking and a change in ribose conformation. The destacking most likely involves a torsional displacement of the Yt base occasioned by a rotation of the phosphate-ribose backbone. The possible relevance of this unusual behavior to the influence of the Yt residue in tRNA function in protein biosynthesis is discussed.")));
	}
}

