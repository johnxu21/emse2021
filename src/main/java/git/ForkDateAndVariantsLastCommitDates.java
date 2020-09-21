package git;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Call_URL;

public class ForkDateAndVariantsLastCommitDates {

    public static String forkDate(String fv, String[] tokens, int ct) {
        String created_at = "";
        try {
            if (ct >= (tokens.length)) {/// the the index for the tokens array...
                ct = 0; //// go back to the first index......
            }
            JSONParser parser = new JSONParser();
//			String repo_url = Call_URL.callURL("https://api.github.com/repos/" + fv + "?access_token=" + tokens[ct]);
            String repo_url = Call_URL.callURL1("https://api.github.com/repos/" + fv, tokens[ct]);
            JSONObject jsonObj = (JSONObject) parser.parse(repo_url);
            created_at = (String) jsonObj.get("created_at");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return created_at;
    }

    public static String variantLastComDate(String variant, String[] tokens, int ct) {
        String lastCommitDate = "";
        try {
            if (ct >= (tokens.length)) {/// the the index for the tokens array...
                ct = 0; //// go back to the first index......
            }
            JSONParser parser = new JSONParser();
//			String repo_url = Call_URL.callURL("https://api.github.com/repos/" + variant + "/commits?access_token=" + tokens[ct]);
            String repo_url = Call_URL.callURL1("https://api.github.com/repos/" + variant + "/commits", tokens[ct]);
            JSONArray jsonArray = (JSONArray) parser.parse(repo_url);
            JSONObject jsonObj = (JSONObject) jsonArray.get(0);
            JSONObject jsonObjCommit = (JSONObject) jsonObj.get("commit");
            JSONObject jsonObjCommitter = (JSONObject) jsonObjCommit.get("committer");
            lastCommitDate = (String) jsonObjCommitter.get("date");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lastCommitDate;
    }

}
