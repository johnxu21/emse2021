package git;

import java.util.ArrayList;
import java.util.List;

public class UniqueCommits {
	
	public static List<String> repoUniqueCommits(List<String> allCommits, List<String> gitCommitMergeRebase, List<String> gitCommitCherryVar1, List<String> gitCommitCherryVar2) {
		List<String> uniqueCommits = new ArrayList<String>();
		
		for(String commit : allCommits) {
			if (!gitCommitMergeRebase.contains(commit) && !gitCommitCherryVar1.contains(commit) && !gitCommitCherryVar2.contains(commit))
				uniqueCommits.add(commit);
		}
		
		return uniqueCommits;
	}

}
