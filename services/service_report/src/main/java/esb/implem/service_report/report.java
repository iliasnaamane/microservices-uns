package esb.implem.service_report;

import org.json.JSONArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.JSONObject;



import java.util.List;
import javax.json.JsonObject;
import org.jongo.marshall.jackson.oid.MongoObjectId;
import org.json.JSONObject;

/**
 *
 * @author obisama
 */
public class report {
    private int report_id;
    private double total_cost ;
    private int employee_id;
    private int BTravel_id;
    private int Duree_BT;
	private spending[] evidences;
    private boolean complete;
    private boolean valide;
 
    @MongoObjectId
    String _id;

    public report() {
    	
    	report_id=-1;
    	employee_id=-1;
    	total_cost=0;
    	Duree_BT=0;
    	BTravel_id=-1;
    }

    public report(JSONObject data) {
        this.report_id = data.getInt("report_id");
        this.total_cost = data.getDouble("total_cost");
        this.employee_id = data.getInt("employee_id");
        this.complete=data.getBoolean("complete");
        this.Duree_BT=data.getInt("Duree_BT");
        this.BTravel_id=data.getInt("BTravel_id");
    }
    
    public report(int emp_id,int max_id) {
    	this.report_id=max_id; 
    	this.total_cost=0;
    	this.employee_id=emp_id;
    	this.BTravel_id=-1;
    	this.complete=false;
    	total_cost=0;
    	Duree_BT=0;
    	BTravel_id=-1;
	}
    
    
    public int getDuree_BT() {
		return Duree_BT;
	}

	public void setDuree_BT(int duree_BT) {
		Duree_BT = duree_BT;
	}

    public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	

	JSONObject toJson() {
        return new JSONObject()
                .put("report_id", report_id)
                .put("employee_id", employee_id)
                .put("total_cost", total_cost)
                .put("complete", complete)
                .put("BusnessTravelId", this.BTravel_id)
                .put("duree_BT", this.Duree_BT)
                .put("ETAT",(this.valide)?"Valide":"EN_COURS")
                .put("evidences", evidences);
                
    }

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public spending[] getEvidences() {
		return evidences;
	}

	public void setEvidences(spending[] evidences) {
		this.evidences = evidences;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public int getBTravel_id() {
		return BTravel_id;
	}

	public void setBTravel_id(int bTravel_id) {
		BTravel_id = bTravel_id;
	}
	
	
    
}
