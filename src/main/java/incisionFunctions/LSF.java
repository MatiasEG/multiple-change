package incisionFunctions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import sets.CredibilityElement;

public class LSF<A, C> implements IncisionFunction<A, C> {
	
	private Comparator<A> comparator;
	
	public LSF(Comparator<A> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public List<CredibilityElement<A>> select(List<List<CredibilityElement<A>>> kernelSet) {
		Iterator<CredibilityElement<A>> it;
		CredibilityElement<A> actual;
		List<CredibilityElement<A>> toReturn;
		CredibilityElement<A> min;
		int compValue;
		
		toReturn = new ArrayList<CredibilityElement<A>>();
		for(List<CredibilityElement<A>> kernel : kernelSet) {
			it = kernel.iterator();
			min = it.next();
			while(it.hasNext()) {
				actual = it.next();
				compValue = comparator.compare(min.getMostCredible(), actual.getMostCredible());
				if (compValue > 0 ) {
					min = actual;
				}else {
					if (compValue == 0) {
						compValue = comparator.compare(min.getLessCredible(), actual.getLessCredible());
						if (compValue > 0) {
							min = actual;
						}
					}
				}
			}
			toReturn.add(min);
		}
		
		return toReturn;
	}

}
