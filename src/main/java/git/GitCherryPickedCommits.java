package git;

import java.util.ArrayList;
import java.util.List;

import util.Constants;

public class GitCherryPickedCommits {
	
		
		/*This method compares all the commits in the mlv after the fork date with those of the fv after the fork date and populates
		 * the Lists var1CherryPickedCommit and var2CherryPickedCommit
		 * mlvCommitExact contains commits which have the different commit ID in both the mlv--var1 and the fv--var2 but they contain the
		 * same: AuthorName, AuthorDate, Commit Message
		 * The until date parameter in the commit API used is the smaller of the two date between mlv and fv
		 * */
		public static List<String> gitCherryPickedCommits(String variant1, String variant2, List<String> 
		var1CommitDetails, List<String> var2CommitDetails, List<String> var1CherryPickedCommit, 
		List<String> var2CherryPickedCommit, String[] tokens, int ct) {	
			List<String> source = new ArrayList<String>();
			for (int i = 0; i < var1CommitDetails.size(); i++) {
				String[] mlvDetails = var1CommitDetails.get(i).split("===");
				for (int j = 0; j < var2CommitDetails.size(); j++) {
					String[] fvDetails = var2CommitDetails.get(j).split("===");
					if (!mlvDetails[0].equals(fvDetails[0]) && mlvDetails[1].equals(fvDetails[1]) && mlvDetails[2].equals(fvDetails[2]) && mlvDetails[7].equals(fvDetails[7]) && mlvDetails[8].equals(fvDetails[8])) {
						var1CherryPickedCommit.add(mlvDetails[0].substring(0, 8));
						var2CherryPickedCommit.add(fvDetails[0].substring(0, 8));
						String[] str = CherryPickedCommitSource.cherryPickedCommitSource(variant1, variant2, mlvDetails[0], fvDetails[0], Constants.getToken(), ct).split("=");
						ct = Integer.parseInt(str[1]);
						source.add(str[0]);
					}
				}
				
			}
			source.add(""+ct);
			return source;

		}
		
}
