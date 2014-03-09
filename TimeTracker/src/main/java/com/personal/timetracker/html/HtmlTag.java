/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.personal.timetracker.html;

/**
 *
 * @author Michael
 */
public enum HtmlTag
{
	HTML("html"),
	HEAD("head"),
	BODY("body"),
	DIV("div"),
	TABLE("table"),
	ROW("tr"),
	COL("td");
	
	String name;
	HtmlTag(String name)
	{
		this.name = name;
	}
}
