package com.ibm.tools.portfoliodb.reports;


import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;

public class BandmixAccumulator implements ReportEntryAccumulator {

	private static Map<String,Double> _BAND_COST = new HashMap<String, Double>(8);
	private static final DecimalFormat _DEC_FMT = new DecimalFormat("0.00");
	private int total =0;
	private Map<String, Double> data = new LinkedHashMap<String, Double>(
			16);
	static
	{
		_BAND_COST.put("6G", new Double(5.5));
		_BAND_COST.put("6A", new Double(6));
		_BAND_COST.put("6V", new Double(5.5));
		_BAND_COST.put("6B", new Double(6.5));
		_BAND_COST.put("7A", new Double(7));
		_BAND_COST.put("7B", new Double(7.5));
		_BAND_COST.put("8", new Double(8));
		_BAND_COST.put("9", new Double(9));
		
	}
	@Override
	public List<ReportLineItem> getConsolidatedLineItems() {
		if(data!=null && data.size()>0)
		{
			List<ReportLineItem> reportLines = new LinkedList<ReportLineItem>();
			double totalBandMix = 0;
			for(String subBand: data.keySet())
			{
				ReportLineItem rptLineItem = new ReportLineItem();
				Map<String,String> rowLine = new HashMap<String, String>(8);
				double w = _BAND_COST.get(subBand);
				int fte = data.get(subBand).intValue();
				double fteRatio = fte/(total*1.0);
				double bandMix = w*fteRatio;
				totalBandMix += bandMix;
				rowLine.put("subBand",subBand);
				rowLine.put("weightage",String.valueOf(w));
				rowLine.put("fte",String.valueOf(fte));
				rowLine.put("fteRatio",_DEC_FMT.format(fteRatio));
				rowLine.put("bandMix",_DEC_FMT.format(bandMix));
				rptLineItem.setRowLine(rowLine);
				reportLines.add(rptLineItem);
			}
			ReportLineItem rptLineItem = new ReportLineItem();
			//Adding a summary line
			Map<String,String> rowLine = new HashMap<String, String>(8);
			rowLine.put("subBand","Total");
			rowLine.put("weightage","-");
			rowLine.put("fte",String.valueOf(total));
			rowLine.put("fteRatio",String.valueOf("1.00"));
			rowLine.put("bandMix",_DEC_FMT.format(totalBandMix));
			rptLineItem.setRowLine(rowLine);
			reportLines.add(rptLineItem);
			return reportLines;
		}
		return Collections.emptyList();
	}

	@Override
	public void addLineItem(ProjAssignmentDetails rptData) {
		// TODO Auto-generated method stub
		if(rptData!=null)
		{
		
			String safeSubband = getSafeString(rptData.getEmpDetails().getSubBand());
			Double dataLineItem = data.get(safeSubband);
			if(dataLineItem==null)
			{
				dataLineItem = new Double(1.0);
				
			}
			else
			{
				dataLineItem = new Double(1.0+ dataLineItem.intValue());
			}
			data.put(safeSubband, dataLineItem);
			total++;
		}
	}

	
	private String getSafeString(String input)
	{
		return (input!=null ? input.trim(): "");
	}
}
