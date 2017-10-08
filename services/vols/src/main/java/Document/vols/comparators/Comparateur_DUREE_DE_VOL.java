package Document.vols.comparators;

import java.util.Comparator;

import Document.vols.vol;

public class Comparateur_DUREE_DE_VOL implements Comparator<vol> {
	@Override
	public int compare(vol arg0, vol arg1) {
		//return 1 if arg.id > arg1.id
		//		 0 if arg.id = arg1.id
		// 		-1 if arg.id < arg1.id
		
		if(arg0.get_duree() > arg1.get_duree())
		{
			return 1;
		}
		else if (arg0.get_duree()<arg1.get_duree())
		{
			return -1;
		}
	
		return 0;
	}
}
