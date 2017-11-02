/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Helpers;

import org.apache.camel.dataformat.csv.CsvDataFormat;

/**
 *
 * @author iliasnaamane
 */
public class CsvFormat {
    public static CsvDataFormat buildCsvFormat() {
        //System.out.print("csv data format");
        CsvDataFormat format = new CsvDataFormat();
        format.setDelimiter(",".charAt(0));
        format.setSkipHeaderRecord(true);
        format.setUseMaps(true);
        return format;
    }
}