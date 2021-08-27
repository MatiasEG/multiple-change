package incisionFunctions;

import java.util.ArrayList;
import java.util.List;

import sets.CredibilityElement;

public class TSF<A, C> implements IncisionFunction<A, C> {
	
	public TSF() { }
	
	@Override
	public List<CredibilityElement<A>> select(List<List<CredibilityElement<A>>> kernelSet) {
		
		List<CredibilityElement<A>> toReturn = new ArrayList<CredibilityElement<A>>();
		for(List<CredibilityElement<A>> kernel : kernelSet) {
			for(CredibilityElement<A> ce : kernel) {
				toReturn.add(ce);
			}
		}
		
		return toReturn;
	}

}
