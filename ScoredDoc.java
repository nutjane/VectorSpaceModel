//******************** DO NOT MODIFY THIS FILE ***********************//
public class ScoredDoc implements Comparable{
	public Integer id;
	public Double score;
	public ScoredDoc(Integer _id, Double _score)
	{
		id = _id;
		score = _score;
	}
	
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof ScoredDoc)) return -1;
		double oScore = ((ScoredDoc)o).score;
		if(this.score - oScore < 0) return -1;
		else if(this.score - oScore > 0) return 1;
		return 0;
	}
	
	@Override
	public String toString()
	{
		return "["+id+":"+score+"]";
	}
	
}
