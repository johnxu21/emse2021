package git;

import java.util.ArrayList;
import java.util.List;
import util.Constants;

public class GitIntegratedCommits {

	public static int integratedGit(String variant1, String variant2, List<String> prIntegratedCommitsVar1,
			List<String> prIntegratedCommitsVar2, List<String> gitCommitMergeRebase, List<String> gitCommitCherryVar1, 
			List<String> gitCommitCherryVar2, List<String> cherryPickSource, List<String> uniqueCommitsVar1,
			List<String> uniqueCommitsVar2, String startDate, String stopDate, String[] token, int ct) {

		// These are commits in a variant after the forkdate exlcuding the PR commits
		// and any "PR merge_commit"
		List<String> allCommitsAfterForkDateVar1 = new ArrayList<String>();
		List<String> allCommitsAfterForkDateVar2 = new ArrayList<String>();

		// Collect variant1 commit metadata
		List<String> var1CommitMetadata = CommitMetadata.metadata(variant1, prIntegratedCommitsVar1, startDate,
				stopDate, allCommitsAfterForkDateVar1, Constants.getToken(), ct);
		ct = Integer.parseInt(var1CommitMetadata.get(var1CommitMetadata.size() - 1));
		var1CommitMetadata.remove(var1CommitMetadata.size() - 1);

		List<String> var2CommitMetadata = CommitMetadata.metadata(variant2, prIntegratedCommitsVar2, startDate,
				stopDate, allCommitsAfterForkDateVar2, Constants.getToken(), ct);
		ct = Integer.parseInt(var2CommitMetadata.get(var2CommitMetadata.size() - 1));
		var2CommitMetadata.remove(var2CommitMetadata.size() - 1);

		GitMergedRebasedCommits.gitMergedRebasedCommits(var1CommitMetadata, var2CommitMetadata, gitCommitMergeRebase);

		cherryPickSource = GitCherryPickedCommits.gitCherryPickedCommits(variant1, variant2, var1CommitMetadata,
				var2CommitMetadata, gitCommitCherryVar1, gitCommitCherryVar2, Constants.getToken(), ct);
		ct = Integer.parseInt(cherryPickSource.get(cherryPickSource.size() - 1));
		cherryPickSource.remove(cherryPickSource.size() - 1);

		uniqueCommitsVar1 = UniqueCommits.repoUniqueCommits(allCommitsAfterForkDateVar1,
				gitCommitMergeRebase, gitCommitCherryVar1, gitCommitCherryVar2);
		uniqueCommitsVar2 = UniqueCommits.repoUniqueCommits(allCommitsAfterForkDateVar2,
				gitCommitMergeRebase, gitCommitCherryVar1, gitCommitCherryVar2);

		return ct;
	}

}
