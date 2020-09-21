package git;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;
import core.DateOperations;
import core.JSONUtils;

public class CherryPickedCommitSource {
	
	public static String cherryPickedCommitSource(String variant1, String variant2, String commitVar1, String commitVar2,
			String[] tokens, int ct) {
		String source = "";
		try {
			if (ct >= (tokens.length)) {/// the the index for the tokens array...
				ct = 0; //// go back to the first index......
			}
			String repo_var1 = Call_URL.callURL("https://api.github.com/repos/" + variant1 + "/commits/" + commitVar1
					+ "?access_token=" + tokens[ct++]);
			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(repo_var1);
			
			JSONObject commit = (JSONObject) jsonObject.get("commit");
			JSONObject committer = (JSONObject) commit.get("committer");
			String dateVar1 = (String) committer.get("date");
			
			if (ct >= (tokens.length)) {/// the the index for the tokens array...
				ct = 0; //// go back to the first index......
			}
			String repo_var2 = Call_URL.callURL("https://api.github.com/repos/" + variant2 + "/commits/" + commitVar2
					+ "?access_token=" + tokens[ct++]);
			jsonObject = (JSONObject) parser.parse(repo_var2);
			
			commit = (JSONObject) jsonObject.get("commit");
			committer = (JSONObject) commit.get("committer");
			String dateVar2 = (String) committer.get("date");
			
			String dateComp = DateOperations.dates(dateVar1, dateVar2);
			
			if (dateComp.equals("false"))
				source = "variant1";
			else
				source = "variant2";

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return source + "="+ct;
	}
	

}
