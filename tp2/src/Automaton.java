import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Automaton {
	
	private State m_root = new State("");
	// pour eviter la recursitevite
	private HashSet<String> m_statesList = new HashSet<String>();
	private HashSet<Word> m_words = new HashSet<Word>(); // final states
	private Queue<String> m_mostRecentWords = new LinkedList<String>();
	
	private State m_currentState = m_root;
	
	public Automaton()
	{
	}
	
	public void generateTreeFromFile(String fileName) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try 
		{
			for(String word = br.readLine(); word != null; word = br.readLine()) 
			{
				m_words.add(new Word(word));
				StringBuilder sb = new StringBuilder();
				for(int i = 0 ; i < word.length(); i++) 
				{
					sb.append(word.charAt(i));
					String strToAdd = sb.toString();
					if (!m_statesList.contains(strToAdd)) 
					{
						State newState = new State(strToAdd);
						m_root.addNextState(newState);
						
						//if the state's value is the complete word, then it is a final state
						if(newState.getValue().length() == word.length()) {
							newState.setIsWord(true);
						}
						
						//System.out.println(sb.toString());
						m_statesList.add(strToAdd);
					}
				}
			}
		}
		catch(IOException e) 
		{
			throw e;
		}
		finally
		{
			br.close();
		}	
		
	}
	
	public State getCurrentState() {
		return m_currentState;
	}
	
	public void nextState(char chr) {
		for(State state : m_currentState.getNextStates()) {
			if((m_currentState.getValue() + chr).equals(state.getValue())){
				m_currentState = state;
			}
		}
	}
	
	public void previousState() {
		if(m_currentState == root) {
			
		}
	}
	
	//idk encore pour linkedlist, well see
	public LinkedList<State> getCurrentStatePossibleWords() {
		return new LinkedList<State>();
	}
	
}