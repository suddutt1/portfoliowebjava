package com.ibm.tools.portfoliodb.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;

public class GenderAccumulator implements ReportEntryAccumulator {

	
	private Map<String, Map<String, Double>> data = new LinkedHashMap<String, Map<String, Double>>(
			16);

	private Map<String, Double> totalCountMap = new LinkedHashMap<String, Double>();
	
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
					int rowTotal = 0;
					for(String category: lineEntry.keySet())
					{
						Double value = lineEntry.get(category);
						rowTotal+=value.intValue();
						outputMap.put(category,getSafeCount(value)+"");
						addToTotalCount(category, value);
					}
					rptLineItem.setRowLine(outputMap);
					rptLineItem.setTotalCount(rowTotal);
					reportLines.add(rptLineItem);
				}
			}
			
			reportLines.add(getTotalLineItemCount());
			return reportLines;
		}
		return Collections.emptyList();
	}

	private ReportLineItem getTotalLineItemCount()
	{
		ReportLineItem totalLineItem = new ReportLineItem();
		totalLineItem.setUomName("Total");
		Map<String,String> rowLine = new HashMap<String, String>();
		int totalHeadCount  = 0;
		for(String category: totalCountMap.keySet())
		{
			int count = getSafeCount(totalCountMap.get(category));
			totalHeadCount +=count;
			rowLine.put(category, count+"");
		}
		totalLineItem.setTotalCount(totalHeadCount);
		totalLineItem.setRowLine(rowLine);
		return totalLineItem;	
	}
	private void addToTotalCount(String category,Double value)
	{
		if(totalCountMap.size()==0)
		{
			//Add all the entries
			totalCountMap.put("Male", new Double(0));
			totalCountMap.put("Female", new Double(0));
		}
		Double existing  = totalCountMap.get(category);
		totalCountMap.put(category,existing + value);
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
			addColumnEntry(dataLineItem, rptData);
			
		}
	}

	private Map<String, Double> buildDefaultLineEntry()
	{
		Map<String, Double> dataLineItem = new LinkedHashMap<String, Double>();
		dataLineItem.put("Male", new Double(0));
		dataLineItem.put("Female", new Double(0));
		return dataLineItem;
	}
	private void addColumnEntry(Map<String, Double> lineData ,ProjAssignmentDetails rptData)
	{
		String empGender = rptData.getEmpDetails().getGender();
		if(empGender!=null)
		{
				Double count = lineData.get(empGender);
				if(count==null)
				{
					count = new Double(1.0);
				}
				else
				{
					count = new Double(count.intValue()+ 1);
				}
				lineData.put(empGender, count);
		}
	}
	private String getSafeString(String input)
	{
		return (input!=null ? input.trim(): "");
	}
	private int getSafeCount(Double dblNumber) {
		return (dblNumber != null ? dblNumber.intValue() : 0);
	}
}
