package com.ibm.tools.portfoliodb.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ReferenceDataHelper;

public class RoleAccumulator implements ReportEntryAccumulator {

	private Map<String, Double> data = new LinkedHashMap<String, Double>(
			16);
	
	@Override
	public List<ReportLineItem> getConsolidatedLineItems() {
		if(data!=null && data.size()>0)
		{
			int total = 0;
			List<ReportLineItem> reportLines = new ArrayList<ReportLineItem>();
			for(String roleCde: data.keySet())
			{
				ReportLineItem rptLineItem = new ReportLineItem();
				rptLineItem.setUomName(ReferenceDataHelper.getDisplayName("role", roleCde));
				int value = data.get(roleCde).intValue();
				rptLineItem.setTotalCount(value);
				total += value;
				reportLines.add(rptLineItem);
			}
			ReportLineItem totalLine = new ReportLineItem();
			totalLine.setUomName("Total");
			totalLine.setTotalCount(total);
			reportLines.add(totalLine);
			return reportLines;
		}
		return Collections.emptyList();
	}

	@Override
	public void addLineItem(ProjAssignmentDetails rptData) {
		// TODO Auto-generated method stub
		if(rptData!=null)
		{
		
			String safeEmpRole = getSafeString(rptData.getRole());
			Double dataLineItem = data.get(safeEmpRole);
			if(dataLineItem==null)
			{
				dataLineItem = new Double(1.0);
				
			}
			else
			{
				dataLineItem = new Double(1.0+ dataLineItem.intValue());
			}
			data.put(safeEmpRole, dataLineItem);
			
		}
	}

	
	private String getSafeString(String input)
	{
		return (input!=null ? input.trim(): "");
	}
}
