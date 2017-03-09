package com.crowdserviceinc.crowdservice.parser;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import android.util.Log;

import com.crowdserviceinc.crowdservice.util.http.HttpPostConnector;
import com.crowdserviceinc.crowdservice.model.Category;
import com.crowdserviceinc.crowdservice.util.Constants;

public class JobbidParser {

	ArrayList<Category> list = null;
	String strUrl;

	public JobbidParser(String url) {
		// TODO Auto-generated constructor stub
		strUrl = url;
	}

	public String getParseData(ArrayList<NameValuePair> nameValueParams) {
		String str = "fail";
		try {
			Log.e("VIPII", "REQ Parser >> " + strUrl);
			HttpPostConnector conn = new HttpPostConnector(strUrl,
					nameValueParams);
			String response = conn.getResponseData();
			if (response != null) {
				JSONObject obj = new JSONObject(response);

				str = obj.getString("status");
				
				if(obj.has("is_customer")){
					Log.e("VIPII",
							"JobbidParser >> "
									+ Integer.parseInt(obj.getString("is_customer")));
					Constants.userType = Integer.parseInt(obj
							.getString("is_customer"));
				}				
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("ParserException", ">>>" + e.getMessage());
		}
		return str;
	}
}
