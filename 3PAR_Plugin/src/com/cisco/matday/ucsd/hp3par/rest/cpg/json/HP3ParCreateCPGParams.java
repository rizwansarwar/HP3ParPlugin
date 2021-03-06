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
package com.cisco.matday.ucsd.hp3par.rest.cpg.json;

/**
 * Parameters to represent a 3PAR CPG creation API call.
 *
 * See the HP 3PAR documentation for more detail.
 *
 * @author Matt Day
 *
 */
@SuppressWarnings("javadoc")
public class HP3ParCreateCPGParams {
	private String name;
	private HP3ParLDLayoutParams LDLayout;

	public HP3ParCreateCPGParams(String name, HP3ParLDLayoutParams lDLayout) {
		super();
		this.name = name;
		this.LDLayout = lDLayout;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lDLayout
	 */
	public HP3ParLDLayoutParams getLDLayout() {
		return this.LDLayout;
	}

	/**
	 * @param lDLayout
	 *            the lDLayout to set
	 */
	public void setLDLayout(HP3ParLDLayoutParams lDLayout) {
		this.LDLayout = lDLayout;
	}

}
