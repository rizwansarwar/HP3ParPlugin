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
package com.cisco.matday.ucsd.hp3par.tasks.volumesets;

import org.apache.log4j.Logger;

import com.cisco.matday.ucsd.hp3par.account.HP3ParCredentials;
import com.cisco.matday.ucsd.hp3par.constants.HP3ParConstants;
import com.cisco.matday.ucsd.hp3par.exceptions.HP3ParVolumeException;
import com.cisco.matday.ucsd.hp3par.rest.json.HP3ParRequestStatus;
import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.TaskOutputDefinition;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionLogger;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionTriggerContext;

/**
 * Executes a task to create a volume. This should not generally be instantiated
 * by anything other than UCS Director's internal libraries
 *
 * @author Matt Day
 *
 */
public class CreateVolumeSetSnapshotTask extends AbstractTask {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(CreateVolumeSetSnapshotTask.class);

	@Override
	public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger ucsdLogger)
			throws Exception {
		// Obtain account information:
		CreateVolumeSetSnapshotConfig config = (CreateVolumeSetSnapshotConfig) context.loadConfigObject();
		HP3ParCredentials c = new HP3ParCredentials(config.getAccount());

		HP3ParRequestStatus s = HP3ParVolumeSetExecute.snapshot(c, config);

		// If it wasn't created error out
		if (!s.isSuccess()) {
			ucsdLogger.addError("Failed to create volume set snapshot:" + s.getError());
			throw new HP3ParVolumeException("Failed to create volume set snapshot");
		}

		ucsdLogger.addInfo("Created volume set snapshot: " + config.getSnapshotName());

		try {
			final String volumeSetName = c.getAccountName() + ";0@" + config.getAccount() + "@"
					+ config.getSnapshotName() + ";volumeset";
			context.saveOutputValue(HP3ParConstants.VOLUMESET_LIST_FORM_LABEL, volumeSetName);

			final String volAndVolSetName = c.getAccountName() + ";0@set@" + config.getSnapshotName();
			context.saveOutputValue(HP3ParConstants.VOLUME_AND_VOLUMESET_LIST_FORM_LABEL, volAndVolSetName);
		}
		catch (Exception e) {
			ucsdLogger.addWarning("Could not register output value " + HP3ParConstants.ACCOUNT_LIST_FORM_LABEL + ": "
					+ e.getMessage());
		}

	}

	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		return new CreateVolumeSetSnapshotConfig();
	}

	@Override
	public String getTaskName() {
		return CreateVolumeSetSnapshotConfig.DISPLAY_LABEL;
	}

	@Override
	public TaskOutputDefinition[] getTaskOutputDefinitions() {
		TaskOutputDefinition[] ops = {
				// Register output type for the volume created
				new TaskOutputDefinition(HP3ParConstants.VOLUME_LIST_FORM_LABEL,
						HP3ParConstants.VOLUME_LIST_FORM_TABLE_NAME, HP3ParConstants.VOLUME_LIST_FORM_LABEL),

				new TaskOutputDefinition(HP3ParConstants.VOLUME_AND_VOLUMESET_LIST_FORM_LABEL,
						HP3ParConstants.VOLUME_AND_VOLUMESET_LIST_FORM_TABLE_NAME,
						HP3ParConstants.VOLUME_AND_VOLUMESET_LIST_FORM_LABEL),
		};
		return ops;
	}

}
