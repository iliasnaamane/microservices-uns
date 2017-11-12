package esb.implem.service_report;

import javax.json.JsonObject;

import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author obisama
 */
public class spending {
    int evidence_no ; 
    String evidence_desc;
    double amount;

	
    
    public int getEvidence_no() {
		return evidence_no;
	}
	public void setEvidence_no(int evidence_no) {
		this.evidence_no = evidence_no;
	}
	public String getEvidence_desc() {
		return evidence_desc;
	}
	public void setEvidence_desc(String evidence_desc) {
		this.evidence_desc = evidence_desc;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public spending(int evidence_no, String evidence_desc, double amount) {
		super();
		this.evidence_no = evidence_no;
		this.evidence_desc = evidence_desc;
		this.amount = amount;
	}
	public spending() {
		super();
	}
	public spending(JSONObject obj) {
		evidence_no=obj.getInt("evidence_no");
		evidence_desc=obj.getString("evidence_desc");
		amount=obj.getDouble("amount");
	}
    
    
}
