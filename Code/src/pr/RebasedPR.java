package pr;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;
import core.DateOperations;
import core.JSONUtils;
import util.Constants;

public class RebasedPR {

	public static ArrayList<String> rebasedPR(String baseRepo, String prMergeDate, String prNumber, String[] tokens, int ct) {
		String rebasedCommits = "|";
		ArrayList<String> rebasedComList = new ArrayList<String>();
		try {

				if (ct == (tokens.length)) {/// the the index for the tokens array...
					ct = 0; //// go back to the first index......
				}
//				String repo_url = Call_URL.callURL("https://api.github.com/repos/" + baseRepo + "/pulls/" + prNumber
//						+ "/commits?access_token=" + tokens[ct++]);
				String repo_url = Call_URL.callURL1("https://api.github.com/repos/" + baseRepo + "/pulls/" + prNumber
						+ "/commits",  tokens[ct++]);

				JSONParser parser = new JSONParser();

				JSONArray jsonArray = (JSONArray) parser.parse(repo_url);

				for (int i = jsonArray.size()-1; i >= 0; i--) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);

					String prCommitSha = (String) jsonObject.get("sha");
					if (ct == (tokens.length)) {/// the the index for the tokens array...
						ct = 0; //// go back to the first index......
					}
//					String prShaDetails = Call_URL.callURL("https://api.github.com/repos/" + baseRepo + "/commits/"
//							+ prCommitSha + "?access_token=" + tokens[ct++]);
					String prShaDetails = Call_URL.callURL1("https://api.github.com/repos/" + baseRepo + "/commits/"
							+ prCommitSha , tokens[ct++]);
					JSONObject shaObject = (JSONObject) parser.parse(prShaDetails);
//					
					JSONObject commit = (JSONObject) shaObject.get("commit");
					String messagePr = (String) commit.get("message");

					JSONObject author = (JSONObject) commit.get("author");
					String authorDatePr = (String) author.get("date");

					JSONObject stats = (JSONObject) shaObject.get("stats");
					Long totalPr = (Long) stats.get("total");
					Long additionsPr = (Long) stats.get("additions");
					Long deletionsPr = (Long) stats.get("deletions");

						if (ct == (tokens.length)) {/// the the index for the tokens array...
							ct = 0; //// go back to the first index......
						}
						String since = DateOperations.dateLessBy1Sec(prMergeDate);
//						String baseRepo_url = Call_URL.callURL("https://api.github.com/repos/" + baseRepo
//								+ "/commits?since="+since+"&until=" + prMergeDate + "&access_token=" + tokens[ct++]);
						String baseRepo_url = Call_URL.callURL1("https://api.github.com/repos/" + baseRepo
								+ "/commits?since="+since+"&until=" + prMergeDate , tokens[ct++]);

						parser = new JSONParser();
						JSONArray jsonArray1 = (JSONArray) parser.parse(baseRepo_url);
						for (Object objectSha : jsonArray1) {
							JSONObject jsonObject1 = (JSONObject) objectSha;

							String baseReposha = (String) jsonObject1.get("sha");
							if (ct == (tokens.length)) {/// the the index for the tokens array...
								ct = 0; //// go back to the first index......
							}
//							String baseRepoShaDetails = Call_URL.callURL("https://api.github.com/repos/" + baseRepo
//									+ "/commits/" + baseReposha + "?access_token=" + tokens[ct++]);
							String baseRepoShaDetails = Call_URL.callURL1("https://api.github.com/repos/" + baseRepo
									+ "/commits/" + baseReposha , tokens[ct++]);
							JSONObject baseRepoShaObject = (JSONObject) parser.parse(baseRepoShaDetails);

							JSONObject baseRepoCommit = (JSONObject) baseRepoShaObject.get("commit");
							String messageBasePepo = (String) baseRepoCommit.get("message");

							JSONObject author1 = (JSONObject) baseRepoCommit.get("author");
							String authorDateBaseRepo = (String) author1.get("date");

							JSONObject stats1 = (JSONObject) baseRepoShaObject.get("stats");
							Long totalBaserepo = (Long) stats1.get("total");
							Long additionsBaseRepo = (Long) stats1.get("additions");
							Long deletionsBaseRepo = (Long) stats1.get("deletions");

							if (!baseReposha.equals(prCommitSha) && messageBasePepo.equals(messagePr)
									&& authorDateBaseRepo.equals(authorDatePr) && (long) totalBaserepo == (long) totalPr
									&& (long) additionsBaseRepo == (long) additionsPr
									&& (long) deletionsBaseRepo == (long) deletionsPr) {
								String shortShaPR = prCommitSha.substring(0,8);
								String shortShaBR = baseReposha.substring(0,8);
								rebasedCommits += shortShaPR + ":" + shortShaBR +"|";
								rebasedComList.add(baseReposha);
								break;
							}
						}
				}
			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		rebasedCommits += "="+ct;
		rebasedComList.add(rebasedCommits);
		return rebasedComList;
	}

}
