package esb.implem.service_report;

public class Tarifs {
	private static double Tarif_fix=95;
	private static String devise="eur";
	
	public static boolean verif_rembo(report rp)//verifie le remboursement
	{
		System.out.println(rp.getTotal_cost() +" > "+ (Tarif_fix*rp.getDuree_BT()));
		if( rp.getTotal_cost() > (Tarif_fix*rp.getDuree_BT()))
		{
			
			return false;
		}
		else 
		{
			return true;
		}
	}
	
}
