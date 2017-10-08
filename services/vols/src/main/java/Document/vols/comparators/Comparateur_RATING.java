package Document.vols.comparators;

import java.util.Comparator;

import Document.vols.vol;

public class Comparateur_RATING implements Comparator<vol> {
	@Override
	public int compare(vol arg0, vol arg1) {
		//return 1 if arg.id > arg1.id
		//		 0 if arg.id = arg1.id
		// 		-1 if arg.id < arg1.id
		
		if(arg0.get_rating() > arg1.get_rating())
		{
			return 1;
		}
		else if (arg0.get_rating()<arg1.get_rating())
		{
			return -1;
		}
	
		return 0;
	}
}
