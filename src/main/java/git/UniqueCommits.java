package git;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;
import core.JSONUtils;

public class UniqueCommits {
	
	public static String repoUniqueCommits(String variant1, String variant2, String[] tokens, int ct) throws ParseException {
		Long ahead_by = (long) 0;
		Long behind_by = (long) 0;
		
		String ah = "ah", bh = "bh";
		
		
		if (ct >= (tokens.length)) {/// the the index for the tokens array...
			ct = 0; //// go back to the first index......
		}
		
		String repo_url_v1 = Call_URL.callURL1("https://api.github.com/repos/" + variant1 + "/commits", tokens[ct++]);
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(repo_url_v1);
		JSONObject jsonObj = (JSONObject) jsonArray.get(0);
		String sha_v1 = (String) jsonObj.get("sha");
		
		if (ct >= (tokens.length)) {/// the the index for the tokens array...
			ct = 0; //// go back to the first index......
		}
		String repo_url_v2 = Call_URL.callURL1("https://api.github.com/repos/" + variant2 + "/commits", tokens[ct++]);
		jsonArray = (JSONArray) parser.parse(repo_url_v2);
		jsonObj = (JSONObject) jsonArray.get(0);
		String sha_v2 = (String) jsonObj.get("sha");
		
		if (ct >= (tokens.length)) {/// the the index for the tokens array...
			ct = 0; //// go back to the first index......
		}
		
		String repo_url_branch1 = Call_URL.callURL1("https://api.github.com/repos/" + variant1, tokens[ct++]);
		jsonObj = (JSONObject) parser.parse(repo_url_branch1);
		String branch1 = (String) jsonObj.get("default_branch");
		
		if (ct >= (tokens.length)) {/// the the index for the tokens array...
			ct = 0; //// go back to the first index......
		}
		
		String repo_url_branch2 = Call_URL.callURL1("https://api.github.com/repos/" + variant2, tokens[ct++]);
		jsonObj = (JSONObject) parser.parse(repo_url_branch2);
		String branch2 = (String) jsonObj.get("default_branch");
		
		String[] var2 = variant2.split("/");
		if (ct >= (tokens.length)) {/// the the index for the tokens array...
			ct = 0; //// go back to the first index......
		}
		String repo_url = Call_URL.callURL1("https://api.github.com/repos/" + variant1 + "/compare/" + branch1 + "..." + 
		var2[0] + ":" + branch2, tokens[ct++]);
//		System.out.println("https://api.github.com/repos/" + variant1 + "/compare/" + branch1 + "..." + var2[0] + ":" + branch2);
		
		if (JSONUtils.isValidJSONObject(repo_url) == true) {///
			jsonObj = (JSONObject) parser.parse(repo_url);
			ahead_by = (Long) jsonObj.get("ahead_by");
			behind_by = (Long) jsonObj.get("behind_by");
			return ahead_by + ":" + behind_by + ":" + ct;
		}
		
		else if (JSONUtils.isValidJSONObject(repo_url) == false) {///
			String[] var1 = variant1.split("/");
			if (ct >= (tokens.length)) {/// the the index for the tokens array...
				ct = 0; //// go back to the first index......
			}
			repo_url = Call_URL.callURL1("https://api.github.com/repos/" + variant2 + "/compare/" + branch2 + "..." + 
					var1[0] + ":" + branch1, tokens[ct++]);
//			System.out.println("https://api.github.com/repos/" + variant2 + "/compare/" + branch2 + "..." + 
//					var1[0] + ":" + branch1);
			if (JSONUtils.isValidJSONObject(repo_url) == true) {
			jsonObj = (JSONObject) parser.parse(repo_url);
			behind_by = (Long) jsonObj.get("ahead_by");
			ahead_by = (Long) jsonObj.get("behind_by");
			return ahead_by + ":" + behind_by + ":" + ct;
			}
			else 
				return ah + ":" + bh + ":" + ct;
		}
		else 
			return ah + ":" + bh + ":" + ct;
		
		
		
		
//		System.out.println("Unique Commits var2" + behind_by);
		
		
		
	}

}
