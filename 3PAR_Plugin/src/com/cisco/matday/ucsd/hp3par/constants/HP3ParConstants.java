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

public class HP3ParConstants {
	public static final String INFRA_ACCOUNT_TYPE = "HP3PAR"; 
	public static final String INFRA_ACCOUNT_LABEL = "HP 3PAR";
	
	public static final String INFRA_ACCOUNT_NAME = "HP 3PAR";
	
	// Docs say to give this a really big number (> 1000); I've used my birthday...
	public static final int INFRA_ACCOUNT_MAGIC_NUMBER = 19832701;
	
	public static final String POD_TYPE = "HP 3PAR Pod";
	
	public static final String WORKFLOW_CATEGORY = "HP 3PAR";
	public static final String TASK_PREFIX = "HP 3PAR Tasks";
	
	public final static int DEFAULT_PORT = 8080;
	
	public final static int TOKEN_LIFE = 3600;
	
	public static final String ACCOUNT_LIST_FORM_NAME = "HP3ParAccountList";
	public static final String CPG_LIST_FORM_NAME = "HP3ParCPGList";
	public static final String VOLUME_LIST_FORM_NAME = "HP3ParVolumeList";
	
	public static final String ACCOUNT_LIST_FORM_TABLE_NAME = "HP3ParAccountList_table";
	public static final String CPG_LIST_FORM_TABLE_NAME = "HP3ParCPGList_table";
	public static final String VOLUME_LIST_FORM_TABLE_NAME = "HP3ParVolumeList_table";
	
	public static final String ACCOUNT_LIST_FORM_PROVIDER = "HP3ParAccountList_provider";
	public static final String CPG_LIST_FORM_PROVIDER = "HP3ParCPGList_provider";
	public static final String VOLUME_LIST_FORM_PROVIDER = "HP3ParVolumeList_provider";
	
	public static final String GENERIC_TEXT_INPUT = "gen_text_input";

	
}
