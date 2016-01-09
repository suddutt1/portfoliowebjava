package com.ibm.tools.portfoliodb.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;

public class TenuredResourceAccumulator implements ReportEntryAccumulator {

	private Date refDate = new Date();
	private Map<String, Map<String, Double>> data = new LinkedHashMap<String, Map<String, Double>>(
			16);

	@Override
	public List<ReportLineItem> getConsolidatedLineItems() {
		if(data!=null && data.size()>0)
		{
			List<ReportLineItem> reportLines = new ArrayList<ReportLineItem>();
			for(String uomName: data.keySet())
			{
				ReportLineItem rptLineItem = new ReportLineItem();
				rptLineItem.setUomName(uomName);
				Map<String, Double> lineEntry = data.get(uomName);
				
				if(lineEntry!=null)
				{
					Map<String,String> outputMap = new HashMap<String, String>();
					for(String category: lineEntry.keySet())
					{
						outputMap.put(category,getSafeCount(lineEntry.get(category))+"");
					}
					rptLineItem.setRowLine(outputMap);
					reportLines.add(rptLineItem);
				}
			}
			return reportLines;
		}
		return Collections.emptyList();
	}

	@Override
	public void addLineItem(ProjAssignmentDetails rptData) {
		// TODO Auto-generated method stub
		if(rptData!=null)
		{
		
			String safeUOMName = getSafeString(rptData.getUomDetails().getUomName());
			Map<String, Double> dataLineItem = data.get(safeUOMName);
			if(dataLineItem==null)
			{
				dataLineItem =  buildDefaultLineEntry();
				data.put(safeUOMName, dataLineItem);
			}
			addColumnEntry(dataLineItem, rptData,refDate.getTime());
			
		}
	}

	private Map<String, Double> buildDefaultLineEntry()
	{
		Map<String, Double> dataLineItem = new LinkedHashMap<String, Double>();
		dataLineItem.put("LE18MONTHS", new Double(0));
		dataLineItem.put("LE36MONTHS", new Double(0));
		dataLineItem.put("LE60MONTHS", new Double(0));
		dataLineItem.put("GE60MONTHS", new Double(0));
		return dataLineItem;
	}
	private void addColumnEntry(Map<String, Double> lineData ,ProjAssignmentDetails rptData,long time)
	{
		Date dateOfJoining = rptData.getStartDate();
		String safeColumnName ="";
		if(dateOfJoining!=null)
		{
				//TODO: Following calculation need to be more accurate
				
				long timeDiffrence = time-dateOfJoining.getTime();
				long days = timeDiffrence/86400000;
				if(days< 540)
				{
					safeColumnName = "LE18MONTHS";
				} else if(days>=540 && days< 1080)
				{
					safeColumnName = "LE36MONTHS";
				}
				else if (days>=1080 && days< 1800)
				{
					safeColumnName = "LE60MONTHS";
				}
				else
				{
					safeColumnName = "GE60MONTHS";
				}
				Double count = lineData.get(safeColumnName);
				if(count==null)
				{
					count = new Double(1.0);
				}
				else
				{
					count = new Double(count.intValue()+ 1);
				}
				lineData.put(safeColumnName, count);
		}
	}
	
	private String getSafeString(String input) {
		return (input != null ? input.trim() : "");
	}
	
	private int getSafeCount(Double dblNumber) {
		return (dblNumber != null ? dblNumber.intValue() : 0);
	}
}
