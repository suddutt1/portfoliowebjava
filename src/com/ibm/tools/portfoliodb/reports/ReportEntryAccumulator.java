package com.ibm.tools.portfoliodb.reports;

import java.util.List;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;

public interface ReportEntryAccumulator {

	List<ReportLineItem> getConsolidatedLineItems();
	void addLineItem(ProjAssignmentDetails rptData);
}
