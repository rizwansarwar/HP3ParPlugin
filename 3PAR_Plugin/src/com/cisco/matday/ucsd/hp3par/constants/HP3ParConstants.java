/*******************************************************************************
 * Copyright (c) 2016 Matt Day, Cisco and others
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
package com.cisco.matday.ucsd.hp3par.constants;

/**
 * All constants should be placed here
 * 
 * @author Matt Day
 *
 */
public class HP3ParConstants {
	/**
	 * UCSD internal account type. This value should NOT be changed between
	 * releases - doing so breaks all existing accounts!
	 */
	public static final String INFRA_ACCOUNT_TYPE = "HP3PAR";
	/**
	 * User-friendly label for this account type. This can be changed between
	 * releases.
	 */
	public static final String INFRA_ACCOUNT_LABEL = "HP 3PAR";

	/**
	 * Accounts must have a magic number in the converged view. The docs say to
	 * use something "over 1000". Let's hope no one else uses this value!
	 */
	public static final int INFRA_ACCOUNT_MAGIC_NUMBER = 19832701;

	/**
	 * Special snowflake pod type this can register with if you want to use it -
	 * recommend adding to the GenericPod type
	 */
	public static final String POD_TYPE = "HP 3PAR Pod";

	/**
	 * Category to put all the workflows
	 */
	public static final String WORKFLOW_CATEGORY = "HP 3PAR";
	/**
	 * Folder to put the tasks in
	 */
	public static final String TASK_PREFIX = "HP 3PAR Tasks";

	/**
	 * Default port to communicate with a 3PAR array - this is usually 8080
	 * unless https is off by default, then it should be 8008
	 */
	public final static int DEFAULT_PORT = 8080;

	/**
	 * User-friendly label for account list table
	 */
	public static final String ACCOUNT_LIST_FORM_LABEL = "HP 3PAR Account";
	/**
	 * User-friendly label for CPG list table
	 */
	public static final String CPG_LIST_FORM_LABEL = "CPG";

	/**
	 * User-friendly label for copy CPG list table
	 */
	public static final String COPY_CPG_LIST_FORM_LABEL = "Copy CPG";
	/**
	 * User-friendly label for Volume list table
	 */
	public static final String VOLUME_LIST_FORM_LABEL = "Volume";

	/**
	 * Account list form name
	 */
	public static final String ACCOUNT_LIST_FORM_NAME = "HP3ParAccountList";
	/**
	 * CPG list form name
	 */
	public static final String CPG_LIST_FORM_NAME = "HP3ParCPGList";
	/**
	 * Volume list form name
	 */
	public static final String VOLUME_LIST_FORM_NAME = "HP3ParVolumeList";

	/**
	 * Account list internal UCSD data type name
	 */
	public static final String ACCOUNT_LIST_FORM_TABLE_NAME = "HP3ParAccountList_table";
	/**
	 * CPG list internal UCSD data type name
	 */
	public static final String CPG_LIST_FORM_TABLE_NAME = "HP3ParCPGList_table";
	/**
	 * Volume list internal UCSD data type name
	 */
	public static final String VOLUME_LIST_FORM_TABLE_NAME = "HP3ParVolumeList_table";

	/**
	 * Account list internal UCSD provider name
	 */
	public static final String ACCOUNT_LIST_FORM_PROVIDER = "HP3ParAccountList_provider";
	/**
	 * CPG list internal UCSD provider name
	 */
	public static final String CPG_LIST_FORM_PROVIDER = "HP3ParCPGList_provider";
	/**
	 * Volume list internal UCSD provider name
	 */
	public static final String VOLUME_LIST_FORM_PROVIDER = "HP3ParVolumeList_provider";

	/**
	 * Volume list drilldown name for global context registry
	 */
	public static final String VOLUME_LIST_DRILLDOWN = "com.cisco.matday.ucsd.hp3par.reports.tabular.VolumeList";
	/**
	 * Label for it (not sure what it does)
	 */
	public static final String VOLUME_LIST_DRILLDOWN_LABEL = "Volume Details";

	/**
	 * Volume list drilldown name for global context registry
	 */
	public static final String CPG_LIST_DRILLDOWN = "com.cisco.matday.ucsd.hp3par.reports.tabular.CPGList";
	/**
	 * Label for it (not sure what it does)
	 */
	public static final String CPG_LIST_DRILLDOWN_LABEL = "CPG Details";

	/**
	 * UCSDs internal gen_text_input type
	 */
	public static final String GENERIC_TEXT_INPUT = "gen_text_input";

	/**
	 * Full provision 3PAR Volume
	 */
	public static final int PROVISION_FULL = 1;
	/**
	 * Thin provision 3PAR Volume
	 */
	public static final int PROVISION_THIN = 2;
	/**
	 * Snapshot provision 3PAR Volume
	 */
	public static final int PROVISION_SNAPSHOT = 3;

}
