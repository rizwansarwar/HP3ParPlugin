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
import com.cisco.matday.ucsd.hp3par.constants.HP3ParConstants;
import com.cisco.matday.ucsd.hp3par.rest.volumes.json.VolumeResponse;
import com.cisco.matday.ucsd.hp3par.rest.volumes.json.VolumeResponseMember;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

/**
 * Implementation of tabular volume list
 *
 * @author Matt Day
 *
 */
public class VolumeSnapshotReportImpl implements TabularReportGeneratorIf {

	private static Logger logger = Logger.getLogger(VolumeSnapshotReportImpl.class);

	@Override
	public TabularReport getTabularReportReport(ReportRegistryEntry reportEntry, ReportContext context)
			throws Exception {
		TabularReport report = new TabularReport();

		report.setGeneratedTime(System.currentTimeMillis());
		report.setReportName(reportEntry.getReportLabel());
		report.setContext(context);

		TabularReportInternalModel model = new TabularReportInternalModel();
		// Internal ID is hidden from normal view and is used by tasks later
		model.addTextColumn("Internal ID", "Internal ID", true);
		model.addTextColumn("ID", "ID");
		model.addTextColumn("Name", "Name");
		model.addTextColumn("Size GiB", "Size GiB");
		model.addTextColumn("Protection", "Protection");
		model.addTextColumn("Comment", "Comment");

		model.completedHeader();

		HP3ParCredentials credentials = new HP3ParCredentials(context);

		int id = -1;
		// Split out hidden field in the format:
		// AccountName;id@AccountName@volumeName
		try {
			id = Integer.parseInt(context.getId().split(";")[1].split("@")[0]);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			logger.warn("Could not get ID from context ID: " + context.getId());
		}

		VolumeResponse list = HP3ParInventory.getVolumeResponse(credentials);

		for (VolumeResponseMember volume : list.getMembers()) {
			// Only interested in snapshots here:
			if ((volume.getProvisioningType() != HP3ParConstants.PROVISION_SNAPSHOT)
					&& (volume.getBaseId() == volume.getId())) {
				continue;
			}
			// We only want to match on this volume's children
			if (volume.getBaseId() != id) {
				continue;
			}

			// Internal ID, format:
			// accountName;volumeid@accountName@volumeName;cpgname;copycpgname
			// TODO This needs cleaning up and/or putting in its own library
			// (e.g. GetInternalId.volume(context))
			model.addTextValue(credentials.getAccountName() + ";" + volume.getId() + "@" + credentials.getAccountName()
					+ "@" + volume.getName() + ";" + volume.getUserCPG() + ";" + volume.getCopyCPG());
			// Volume ID
			model.addTextValue(Integer.toString(volume.getId()));
			// Name of this volume
			model.addTextValue(volume.getName());

			// Round off the size to gb with double precision
			double volSize = (volume.getSizeMiB() / 1024d);
			model.addTextValue(Double.toString(volSize));

			model.addTextValue(volume.isReadOnlyAsText());

			model.addTextValue(volume.getComment());

			model.completedRow();
		}
		model.updateReport(report);

		return report;
	}

}
