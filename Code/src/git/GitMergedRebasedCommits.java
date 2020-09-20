package git;

import java.util.List;

public class GitMergedRebasedCommits {

	/*This method compares all the commits in the mlv after the fork date with those of the fv after the fork date and returns populates
	 * the List var1Commits
	 * var1Commits contains commits which have the same commit ID in both the mlv and the fv
	 * The item in mlvCommit is the token count.
	 * The until date parameter in the commit API used is the smaller of the two date between mlv and fv
	 * */
	public static void gitMergedRebasedCommits(List<String> var1CommitDetails, List<String> var2CommitDetails, List<String> varCommits) {
		
		for (int i = 0; i < var1CommitDetails.size(); i++) {
			String[] mlvDetails = var1CommitDetails.get(i).split("===");
			for (int j = 0; j < var2CommitDetails.size(); j++) {
				String[] fvDetails = var2CommitDetails.get(j).split("===");
				if (mlvDetails[0].equals(fvDetails[0]) && mlvDetails[1].equals(fvDetails[1]) && mlvDetails[2].equals(fvDetails[2]) && mlvDetails[7].equals(fvDetails[7]) && mlvDetails[8].equals(fvDetails[8])) {
					varCommits.add(mlvDetails[0].substring(0, 8));
				}
			}
			
		}
		

	}
}
