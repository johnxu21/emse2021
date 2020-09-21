package git;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;
import core.DateOperations;
import core.JSONUtils;

public class CommitMetadata {

	public static List<String> metadata(String repo, List<String> prRepoCommits, String forkDate, String stopDate, List<String> allCommits,
			String[] tokens, int ct) {

		List<String> repoCommitDetails = new ArrayList<String>();

		// Pick the smaller of the two dates

		int p = 1;

		try {
			while (true) {
				//// loop thru the pagess....
				if (ct >= (tokens.length)) {/// the the index for the tokens array...
					ct = 0; //// go back to the first index......
				}
//				String repo_url = Call_URL.callURL("https://api.github.com/repos/" + repo + "/commits?page=" + p
//						+ "&per_page=100&since=" + forkDate + "&until=" + stopDate + "&access_token=" + tokens[ct++]);
				String repo_url = Call_URL.callURL1("https://api.github.com/repos/" + repo + "/commits?page=" + p
						+ "&per_page=100&since=" + forkDate + "&until=" + stopDate , tokens[ct++]);
				if (JSONUtils.isValidJSON(repo_url) == false) {///
					System.out.println(" :Invalid Pull found!");
					break;
				}

				JSONParser parser = new JSONParser();

				JSONArray jsonArray = (JSONArray) parser.parse(repo_url);

				if (jsonArray.toString().equals("[]")) {
					/// Break out of the loop, when empty array is found!
					break;
				}
				for (Object jsonObj : jsonArray) {

					String loginAuthor = "";
					String loginCommitter = "";
					JSONObject jsonObject = (JSONObject) jsonObj;

					String shamlv = (String) jsonObject.get("sha");
					
					JSONArray parents = (JSONArray) jsonObject.get("parents");
					if (!prRepoCommits.contains(shamlv) && parents.size() == 1) { // Exclude the integrated PR commits and merge commits parents.size() = 2

						JSONObject jsonCommit = (JSONObject) jsonObject.get("commit");

						JSONObject jsonAuthor = (JSONObject) jsonCommit.get("author");
						String authorName = (String) jsonAuthor.get("name");
						String authorDate = (String) jsonAuthor.get("date");

						JSONObject jsonCommitter = (JSONObject) jsonCommit.get("committer");
						String committerName = (String) jsonCommitter.get("name");
						String commiterDate = (String) jsonCommitter.get("date");

						String commiterMsg = (String) jsonCommit.get("message");

						if (jsonCommit.get("author") != null) {
							JSONObject jsonAuthorLogin = (JSONObject) jsonCommit.get("author");
							loginAuthor = (String) jsonAuthorLogin.get("login");
						}

						if (jsonCommit.get("committer") != null) {
							JSONObject jsonCommitterLogin = (JSONObject) jsonCommit.get("committer");
							loginCommitter = (String) jsonCommitterLogin.get("login");
						}

						if (ct == (tokens.length)) {/// the the index for the tokens array...
							ct = 0; //// go back to the first index......
						}
//						String repo_urlSha = Call_URL.callURL("https://api.github.com/repos/" + repo + "/commits/"
//								+ shamlv + "?access_token=" + tokens[ct++]);
						String repo_urlSha = Call_URL.callURL1("https://api.github.com/repos/" + repo + "/commits/"
								+ shamlv , tokens[ct++]);

						parser = new JSONParser();
						JSONObject jsonObjF = (JSONObject) parser.parse(repo_urlSha);
						JSONObject jsonStats = (JSONObject) jsonObjF.get("stats");
						Long changes = (Long) jsonStats.get("total");

						repoCommitDetails.add(shamlv + "===" + authorName + "===" + authorDate + "===" + committerName
								+ "===" + commiterDate + "===" + loginAuthor + "===" + loginCommitter + "==="
								+ commiterMsg + "===" + changes);
						allCommits.add(shamlv);
					}
				}
				p++;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repoCommitDetails.add(ct + "");
		return repoCommitDetails;
	}
}
