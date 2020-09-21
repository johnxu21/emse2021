package pr;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;
import core.JSONUtils;
import util.Constants;

public class PropagatedPR {
	
	/*
	 * @return 
	 * true if the PR is a squashed or rebased and 
	 * false if the PR is a merged
	 * */
	public static String propagatedPR(String baseRepo, String mergeCommit, String prMergeDate, String[] tokens, int ct) {

		try {
				if (ct == (tokens.length)) {/// the the index for the tokens array...
					ct = 0; //// go back to the first index......
				}
//				String repo_url = Call_URL.callURL("https://api.github.com/repos/" + baseRepo + "/commits?until=" + prMergeDate + "&access_token=" + tokens[ct++]);
				String repo_url = Call_URL.callURL1("https://api.github.com/repos/" + baseRepo + "/commits?until=" + prMergeDate , tokens[ct++]);
				if (JSONUtils.isValidJSON(repo_url) == false) {///
					return "Invalid input";
				}

				JSONParser parser = new JSONParser();

				JSONArray jsonArray = (JSONArray) parser.parse(repo_url);
				
				JSONObject jsonObject = (JSONObject) jsonArray.get(0);
				String sha1 = (String) jsonObject.get("sha");
				JSONArray parents = (JSONArray) jsonObject.get("parents");
				
				if (sha1.equals(mergeCommit) && parents.size()==1)
					return "true=" + ct;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "false=" + ct;
	}

	public static void main(String[] args) {
		
		System.out.println(propagatedPR("opendatakit/collect", "5b42bf647343ba429b642d2b69b3036632c94c5a", "2020-08-07T17:11:33Z", Constants.getToken(), 0));
	}
}
