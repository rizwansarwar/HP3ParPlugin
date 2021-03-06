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
package com.cisco.matday.ucsd.hp3par.tasks.hosts;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cisco.matday.ucsd.hp3par.constants.HP3ParConstants;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

/**
 * Configuration task for the 3PAR Host creation task
 * <p>
 * This shouldn't be instantiated directly, instead it should be included as a
 * form field or task config
 *
 * @author Matt Day
 *
 */
@PersistenceCapable(detachable = "true", table = "HP3Par_remove_fc_wwn")
public class RemoveFCWWNHostConfig implements TaskConfigIf {
	/**
	 * Task display label
	 */
	public static final String DISPLAY_LABEL = "3PAR Remove FC WWN from Host";

	@Persistent
	private long configEntryId;

	@Persistent
	private long actionId;

	@FormField(label = HP3ParConstants.FCWWN_LIST_FORM_LABEL, help = "FC WWN to remove", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = HP3ParConstants.FCWWN_LIST_FORM_PROVIDER)
	@UserInputField(type = HP3ParConstants.FCWWN_LIST_FORM_TABLE_NAME)
	@Persistent
	private String fcWWN;

	/**
	 * Empty default constructor - this method shouldn't be instantiated
	 * directly
	 */
	public RemoveFCWWNHostConfig() {

	}

	/**
	 * @return Account name
	 */
	public String getAccount() {
		return this.fcWWN.split(";")[0];
	}

	@Override
	public long getActionId() {
		return this.actionId;
	}

	@Override
	public long getConfigEntryId() {
		return this.configEntryId;
	}

	@Override
	public String getDisplayLabel() {
		return DISPLAY_LABEL;
	}

	@Override
	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	@Override
	public void setConfigEntryId(long configEntryId) {
		this.configEntryId = configEntryId;
	}

	/**
	 * @return the iSCSIName
	 */
	public String getFcWWN() {
		return this.fcWWN;
	}

	/**
	 * @param wwn
	 *            the wwn to set
	 */
	public void setFcWWN(String wwn) {
		this.fcWWN = wwn;
	}

}
