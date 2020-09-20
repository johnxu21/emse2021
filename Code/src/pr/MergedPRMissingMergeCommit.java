package pr;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;

public class MergedPRMissingMergeCommit {
	public static String missingMergeCommit(String baseRepo, String prNumber, String prMergeDate, ArrayList<String> prComList, String[] tokens,
			int ct) {
		String prCommits = "";
		String rebasedCommits = "|";

		try {

			if (ct >= (tokens.length)) {/// the the index for the tokens array...
				ct = 0; //// go back to the first index......
			}
//			String repo_url = Call_URL.callURL("https://api.github.com/repos/" + repo + "/pulls/" + prNumber
//					+ "/commits?access_token=" + tokens[ct++]);
			String repo_urlPr = Call_URL.callURL1(
					"https://api.github.com/repos/" + baseRepo + "/pulls/" + prNumber + "/commits", tokens[ct++]);

			JSONParser parser = new JSONParser();

			JSONArray jsonArrayPr = (JSONArray) parser.parse(repo_urlPr);
			for (int i = jsonArrayPr.size() - 1; i >= 0; i--) {

				JSONObject jsonObject = (JSONObject) jsonArrayPr.get(i);
				String shaPr = (String) jsonObject.get("sha");
				JSONObject jsonCommit = (JSONObject) jsonObject.get("commit");
				JSONObject jsonCommitter = (JSONObject) jsonCommit.get("committer");
				String datePr = (String) jsonCommitter.get("date");
				String msgPr = (String) jsonCommit.get("message");
				String namepr = (String) jsonCommitter.get("name");
				if (ct >= (tokens.length)) {/// the the index for the tokens array...
					ct = 0; //// go back to the first index......
				}
				String repo_url = Call_URL.callURL1(
						"https://api.github.com/repos/" + baseRepo + "/commits?until=" + datePr, tokens[ct++]);
				JSONArray jsonArray = (JSONArray) parser.parse(repo_url);
				jsonObject = (JSONObject) jsonArray.get(0);
				String sha = (String) jsonObject.get("sha");
				jsonCommit = (JSONObject) jsonObject.get("commit");
				jsonCommitter = (JSONObject) jsonCommit.get("committer");
				String date = (String) jsonCommitter.get("date");
				String msg = (String) jsonCommit.get("message");
				String name = (String) jsonCommitter.get("name");

				if (sha.equals(shaPr)) {
					prCommits = "merged";
					break;
				}
				else if (!sha.equals(shaPr) && datePr.equals(date) && msgPr.equals(msg) && namepr.equals(name)) {
					rebasedCommits += shaPr.substring(0,8) +" :"+ sha.substring(0,8) +"|";
					prComList.add(sha.substring(0,8));
					prCommits = "rebased";
				}
				else
					prCommits = "false";
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prComList.add(rebasedCommits);
		prCommits += "=" + ct;
		return prCommits;
	}

}
