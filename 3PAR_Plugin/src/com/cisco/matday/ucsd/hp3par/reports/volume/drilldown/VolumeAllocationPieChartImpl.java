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
package com.cisco.matday.ucsd.hp3par.reports.volume.drilldown;

import org.apache.log4j.Logger;

import com.cisco.matday.ucsd.hp3par.account.HP3ParCredentials;
import com.cisco.matday.ucsd.hp3par.account.inventory.HP3ParInventory;
import com.cisco.matday.ucsd.hp3par.exceptions.HP3ParVolumeException;
import com.cisco.matday.ucsd.hp3par.rest.volumes.json.VolumeResponseMember;
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
public class VolumeAllocationPieChartImpl implements SnapshotReportGeneratorIf {

	private static Logger logger = Logger.getLogger(VolumeAllocationPieChartImpl.class);

	@Override
	public SnapshotReport getSnapshotReport(ReportRegistryEntry reportEntry, ReportContext context) throws Exception {

		SnapshotReport report = new SnapshotReport();
		report.setContext(context);

		report.setReportName(reportEntry.getReportLabel());

		report.setNumericalData(true);

		report.setDisplayAsPie(true);

		report.setPrecision(0);

		HP3ParCredentials credentials = new HP3ParCredentials(context);

		String volName = null;
		// Split out hidden field in the format:
		// AccountName;id@AccountName@volumeName
		try {
			volName = context.getId().split(";")[1].split("@")[2];
		}
		catch (@SuppressWarnings("unused") Exception e) {
			logger.warn("Could not get ID from context ID: " + context.getId());
			throw new HP3ParVolumeException("Could not get volume from context");
		}

		// VolumeResponseMember volume = new HP3ParVolumeInfo(credentials,
		// volName).getMember();
		VolumeResponseMember volume = HP3ParInventory.getVolumeInfo(credentials, volName);

		double adminSpace = volume.getAdminSpace().getUsedMiB();
		double userSpace = volume.getUserSpace().getUsedMiB();
		double snapSpace = volume.getSnapshotSpace().getUsedMiB();

		ReportNameValuePair[] rnv = new ReportNameValuePair[3];
		rnv[0] = new ReportNameValuePair("User Space (GiB)", (userSpace / 1024d));
		rnv[1] = new ReportNameValuePair("Snapshot Space (GiB)", (snapSpace / 1024d));
		rnv[2] = new ReportNameValuePair("Admin Space (GiB)", (adminSpace / 1024d));
		SnapshotReportCategory cat = new SnapshotReportCategory();

		cat.setCategoryName("Virtual Volume Allocation");
		cat.setNameValuePairs(rnv);

		report.setCategories(new SnapshotReportCategory[] {
				cat
		});

		return report;

	}

}
