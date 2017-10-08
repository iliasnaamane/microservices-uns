package Document.vols;

import org.apache.commons.collections.Predicate;

public class FlightPredicates implements Predicate  {

	private String from ; 
	private String to;
	private String date;
	
	
	FlightPredicates(String from , String to,String date)
	{
		this.from=from;
		this.to=to;
		this.date=date;
	}
	
	@Override
	public boolean evaluate(Object arg0) {
		vol F = (vol) arg0;
		Boolean result = false;
		if (F.get_origine().equalsIgnoreCase(this.from))
		{
			
			if(F.get_destination().equalsIgnoreCase(this.to))	
			{ 
			
				if(F.get_date().equalsIgnoreCase(date))
			{
				
				result=true;
				return result;
			}
			}
		}
		
		return result;
	}

}
