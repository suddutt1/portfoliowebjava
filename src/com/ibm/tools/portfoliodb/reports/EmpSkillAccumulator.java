package com.ibm.tools.portfoliodb.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ReferenceDataHelper;

public class EmpSkillAccumulator implements ReportEntryAccumulator {

	private Map<String, Double> data = new LinkedHashMap<String, Double>(
			16);
	@Override
	public List<ReportLineItem> getConsolidatedLineItems() {
		if(data!=null && data.size()>0)
		{
			List<ReportLineItem> reportLines = new ArrayList<ReportLineItem>();
			for(String skillCode: data.keySet())
			{
				ReportLineItem rptLineItem = new ReportLineItem();
				rptLineItem.setUomName(ReferenceDataHelper.getDisplayName("skill", skillCode));
				rptLineItem.setTotalCount(data.get(skillCode).intValue());
				reportLines.add(rptLineItem);
			}
			return reportLines;
		}
		return Collections.emptyList();
	}

	@Override
	public void addLineItem(ProjAssignmentDetails rptData) {
		if(rptData!=null)
		{
		
			String skillCode = getSafeString(rptData.getEmpDetails().getPrimarySkill());
			Double dataLineItem = data.get(skillCode);
			if(dataLineItem==null)
			{
				dataLineItem = new Double(1.0);
				
			}
			else
			{
				dataLineItem = new Double(1.0+ dataLineItem.intValue());
			}
			data.put(skillCode, dataLineItem);
			
		}

	}

	private String getSafeString(String input)
	{
		return (input!=null ? input.trim(): "");
	}
}
