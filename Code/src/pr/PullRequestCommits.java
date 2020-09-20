package pr;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;

public class PullRequestCommits {
	public static ArrayList<String> pullRequestCommits(String repo, String prNumber, String[] tokens, int ct){
		String prCommits = "";
		ArrayList<String> prComList = new ArrayList<String>();
		
		try {

			if (ct == (tokens.length)) {/// the the index for the tokens array...
				ct = 0; //// go back to the first index......
			}
//			String repo_url = Call_URL.callURL("https://api.github.com/repos/" + repo + "/pulls/" + prNumber
//					+ "/commits?access_token=" + tokens[ct++]);
			String repo_url = Call_URL.callURL1("https://api.github.com/repos/" + repo + "/pulls/" + prNumber
					+ "/commits" , tokens[ct++]);


			JSONParser parser = new JSONParser();

			JSONArray jsonArray = (JSONArray) parser.parse(repo_url);

			for (int i = jsonArray.size()-1; i >= 0; i--) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);

				String prCommitSha = (String) jsonObject.get("sha");
				prComList.add(prCommitSha.substring(0,8));
				if (i == 0)
					prCommits += prCommitSha.substring(0,8);
				else
					prCommits += prCommitSha.substring(0,8) + ":";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prCommits += "="+ct;
		prComList.add(prCommits);
		return prComList;
	}

}
