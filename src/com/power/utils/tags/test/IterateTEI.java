package com.power.utils.tags.test;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;


//TagExtraInfo用于提供一些在标签翻译时相关的信息。
public class IterateTEI extends TagExtraInfo {
	public IterateTEI() {
		super();
	}

	@Override
	public VariableInfo[] getVariableInfo(TagData data) {
		return new VariableInfo[] { new VariableInfo(data
				.getAttributeString("name"), data.getAttributeString("type"),
				true, VariableInfo.NESTED), };
	}
}
