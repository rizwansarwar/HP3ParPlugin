package com.cisco.matday.ucsd.hp3par.reports.cpg.drilldown;

import org.apache.log4j.Logger;

import com.cisco.matday.ucsd.hp3par.account.HP3ParCredentials;
import com.cisco.matday.ucsd.hp3par.rest.cpg.HP3ParCPGInfo;
import com.cisco.matday.ucsd.hp3par.rest.cpg.json.CPGResponseMember;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.ReportNameValuePair;
import com.cloupia.model.cIM.SnapshotReport;
import com.cloupia.model.cIM.SnapshotReportCategory;
import com.cloupia.service.cIM.inframgr.SnapshotReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;

/**
 * Implementation of the pie chart
 * 
 * @author Matt Day
 *
 */
public class CpgMappedSpacePieChartImpl implements SnapshotReportGeneratorIf {

	private static Logger logger = Logger.getLogger(CpgMappedSpacePieChartImpl.class);

	@Override
	public SnapshotReport getSnapshotReport(ReportRegistryEntry reportEntry, ReportContext context) throws Exception {

		SnapshotReport report = new SnapshotReport();
		report.setContext(context);

		report.setReportName(reportEntry.getReportLabel());

		report.setNumericalData(true);

		report.setDisplayAsPie(true);

		report.setPrecision(0);

		HP3ParCredentials credentials = new HP3ParCredentials(context);

		String cpgName = null;
		// Split out hidden field in the format:
		// AccountName;id@AccountName@volumeName
		try {
			cpgName = context.getId().split(";")[1].split("@")[2];
		}
		catch (Exception e) {
			logger.warn("Could not get ID from context ID: " + context.getId());
			throw new Exception("Could not get ID from context");
		}

		// Get volume info:
		CPGResponseMember cpg = new HP3ParCPGInfo(credentials, cpgName).getMember();

		double userSpace = cpg.getUsrUsage().getTotalMiB();
		double adminSpace = cpg.getSAUsage().getTotalMiB();
		double snapSpace = cpg.getSDUsage().getTotalMiB();

		ReportNameValuePair[] rnv = new ReportNameValuePair[3];
		rnv[0] = new ReportNameValuePair("User Space (GiB)", (userSpace / 1024d));
		rnv[1] = new ReportNameValuePair("Snapshot Space (GiB)", (snapSpace / 1024d));
		rnv[2] = new ReportNameValuePair("Admin Space (GiB)", (adminSpace / 1024d));
		SnapshotReportCategory cat = new SnapshotReportCategory();

		cat.setCategoryName("Mapped Space");
		cat.setNameValuePairs(rnv);

		report.setCategories(new SnapshotReportCategory[] {
				cat
		});

		return report;

	}

}