/*******************************************************************************
 * Copyright (c) 2016 Cisco and/or its affiliates
 * @author Matt Day
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package com.cisco.matday.ucsd.hp3par.reports.hosts;

import com.cisco.matday.ucsd.hp3par.constants.HP3ParConstants;
import com.cisco.matday.ucsd.hp3par.reports.hosts.actions.CreateHostAction;
import com.cisco.matday.ucsd.hp3par.reports.hosts.actions.DeleteHostAction;
import com.cisco.matday.ucsd.hp3par.reports.hosts.actions.EditHostAction;
import com.cisco.matday.ucsd.hp3par.reports.hosts.actions.HostAddFCAction;
import com.cisco.matday.ucsd.hp3par.reports.hosts.actions.HostAddiSCSIAction;
import com.cisco.matday.ucsd.hp3par.reports.hosts.actions.HostRemoveFCAction;
import com.cisco.matday.ucsd.hp3par.reports.hosts.actions.HostRemoveiSCSIAction;
import com.cisco.matday.ucsd.hp3par.reports.hosts.drilldown.HostPathReport;
import com.cisco.matday.ucsd.hp3par.reports.hosts.drilldown.HostSummaryReport;
import com.cisco.matday.ucsd.hp3par.reports.hosts.drilldown.HostVlunReport;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReport;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.DrillableReportWithActions;
import com.cloupia.service.cIM.inframgr.reports.simplified.actions.DrillDownAction;

/**
 * Host report
 *
 * @author Matt
 *
 */
public class HostReport extends DrillableReportWithActions {
	/**
	 * Unique identifier for this report
	 */
	public final static String REPORT_NAME = "com.cisco.matday.ucsd.hp3par.reports.hosts.HostReport";
	/**
	 * User-friendly report name
	 */
	private final static String REPORT_LABEL = "Hosts";

	// This MUST be defined ONCE!
	private CloupiaReport[] drillable = new CloupiaReport[] {
			new HostSummaryReport(), new HostVlunReport(), new HostPathReport(),

	};

	private CloupiaReportAction[] actions = new CloupiaReportAction[] {
			new CreateHostAction("create"), new EditHostAction(), new DeleteHostAction("Delete", "delete"),
			new HostAddFCAction(), new HostRemoveFCAction(), new HostAddiSCSIAction(), new HostRemoveiSCSIAction(),
			new DrillDownAction(),
	};

	/**
	 * Create Host report
	 */
	public HostReport() {
		super();
		// This sets what column to use as the context ID for child drilldown
		// reports
		this.setMgmtColumnIndex(0);
		// This sets what to show in the GUI in the top
		this.setMgmtDisplayColumnIndex(2);
	}

	@Override
	public CloupiaReport[] getDrilldownReports() {
		return this.drillable;
	}

	@Override
	public Class<HostReportImpl> getImplementationClass() {
		return HostReportImpl.class;
	}

	@Override
	public CloupiaReportAction[] getActions() {
		return this.actions;
	}

	@Override
	public String getReportLabel() {
		return REPORT_LABEL;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME;
	}

	@Override
	public boolean isEasyReport() {
		return false;
	}

	@Override
	public boolean isLeafReport() {
		return false;
	}

	@Override
	public int getMenuID() {
		return 51;
	}

	@Override
	public int getContextLevel() {
		DynReportContext context = ReportContextRegistry.getInstance()
				.getContextByName(HP3ParConstants.HOST_LIST_DRILLDOWN);
		return context.getType();
	}

	@Override
	public ContextMapRule[] getMapRules() {
		DynReportContext context = ReportContextRegistry.getInstance()
				.getContextByName(HP3ParConstants.INFRA_ACCOUNT_TYPE);
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName(context.getId());
		rule.setContextType(context.getType());

		ContextMapRule[] rules = new ContextMapRule[1];
		rules[0] = rule;

		return rules;
	}

}
