/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data;

import java.util.List;

/**
 *
 * @author obisama
 */
public class VolResponse {
     List<vol> Outbound ;

    public VolResponse(List<vol> Outbound) {
        this.Outbound = Outbound;
    }

    public VolResponse() {
    }

    public List<vol> getOutbound() {
        return Outbound;
    }

    public void setOutbound(List<vol> Outbound) {
        this.Outbound = Outbound;
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder("VolResponse{");
        
        for(int i=0 ; i<Outbound.size();i++)
        {
            s.append("\n"+Outbound.get(i).toString());
        }
        s.append("}");
        
        return s.toString();
    }
     
    
}
