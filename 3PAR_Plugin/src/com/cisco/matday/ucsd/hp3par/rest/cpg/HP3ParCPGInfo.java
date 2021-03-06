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
package com.cisco.matday.ucsd.hp3par.rest.cpg;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.cisco.matday.ucsd.hp3par.account.HP3ParCredentials;
import com.cisco.matday.ucsd.hp3par.exceptions.InvalidHP3ParTokenException;
import com.cisco.matday.ucsd.hp3par.rest.UcsdHttpConnection;
import com.cisco.matday.ucsd.hp3par.rest.UcsdHttpConnection.httpMethod;
import com.cisco.matday.ucsd.hp3par.rest.cpg.json.CPGResponseMember;
import com.google.gson.Gson;

/**
 * Get a list of CPGs from the 3PAR array - returns the JSON in object form
 *
 * @author Matt Day
 *
 */
public class HP3ParCPGInfo {

	private CPGResponseMember member;

	/**
	 * @param loginCredentials
	 *            Credentials for the array you wish to query
	 * @param cpg
	 *            Name of the CPG to look up
	 * @throws HttpException
	 * @throws IOException
	 * @throws InvalidHP3ParTokenException
	 *             If the token provided is invalid
	 */
	@Deprecated
	public HP3ParCPGInfo(HP3ParCredentials loginCredentials, String cpg)
			throws HttpException, IOException, InvalidHP3ParTokenException {

		UcsdHttpConnection request = new UcsdHttpConnection(loginCredentials, httpMethod.GET);
		// Use defaults for a GET request
		request.setGetDefaults();

		String uri = "/api/v1/cpgs/" + cpg;
		request.setUri(uri);
		request.execute();
		String response = request.getHttpResponse();

		Gson gson = new Gson();
		this.member = gson.fromJson(response, CPGResponseMember.class);
	}

	/**
	 * Get the response from the array
	 *
	 * @return CPG information from the 3PAR array
	 */
	public CPGResponseMember getMember() {
		return this.member;
	}

}
