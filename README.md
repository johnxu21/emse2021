# Reuse and Maintenance Practices among Divergent Forks in Three Software Ecosystems

## Abstract
With the rise of social coding platforms that rely on distributed version control systems, software reuse is also on the rise. Many software developers leverage this reuse by creating variants through forking, to account for different customer needs, markets, or environments. Forked variants then form a so-called software family; they share a common code base and are maintained in parallel by same or different developers. As such, software families can easily arise within software ecosystems, which are large collections of interdependent software components maintained by communities of collaborating contributors. However, little is known about the existence and characteristics of such families within ecosystems, especially about their maintenance practices. Improving our empirical understanding of such families will help build better tools for maintaining and evolving such families.

We empirically explore maintenance practices in such fork-based software families within ecosystems of open-source software. Our focus is on three of the largest software ecosystems existence today: Android, .NET, and JavaScript. We identify and analyze software families that are maintained together and that exist both on the official distribution platform (Google play, nuget, and npm) as well as on GitHub , allowing us to analyze reuse practices in depth. We mine and identify 38 software families, 526 software families, and 8,837 software families from the ecosystems of Android, .NET, and JavaScript, to study their characteristics and code-propagation practices. We provide scripts for analyzing code integration within our families. Interestingly, our results show that there is little code integration across the studied software families from the three ecosystems. Our studied families also show that techniques of direct integration using Git (outside of GitHub) is more commonly used than GitHub pull requests. Overall, we hope to raise awareness about the existence of software families within larger ecosystems of software, calling for further research and better tools support to effectively maintain and evolve them.



# Scripts for mining Integrated Commits
This repository contains scripts used to mine integrated commits between any two connected repositories. For example mainline (**variant1**) and fork (variant2). **variant1** and **variant2** can be interchanged. For any two connected repositories, the scripts are able to identify the following commits in the order below: 

* Pull requests (PR) integration and their commits: source = variant1, destination = variant2.
	* Merged pull requests 
	* Squashed pull requests
	* Rebased pull requests
* Git integration
	* Merge/Rebase commits
	* Cherry-picked commits. The source and destination of the cherry-picked commits is also identified.
* Unique Commits for variant1 and variant2.

## How to run
* Clone and import the project into any of your favorite editors. 
* The project uses two libraries json simple and joda, to download these libraries: (1) Inside the editor, use clean and build function. Or (2) from the command prompt, run the command "mvn clean" inside the project's root directory.
* Project has been tested on IDEs **Intelli J IDEA CE** and **Netbeans**.
* The main project can be found in in the file **pr/IntegratedCommits.java**.
* You need to put your GitHub token in the file **util/Constants.java**. Here is a site to guide you in generation a [GitHub token]( https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token#creating-a-token). If you are running a commit heavy project, you might want to use more than one token otherwise the execution of the project with encounter a **GitHub rate limit** exception. Borrow a token from a friend. **The project will not run without a GitHub token(s)**
* In the main file **pr/IntegratedCommits.java**, change lines 24 and 25 for your variant pair. You can uncomment lines 30 to 44 and then comment out lines 27 and 28.
* You can also choose to determine code integration within a given time interval. For example, if you want to know how a given PR was integrated, you can change the **startDate** and the **stopDate** to dates around the merge date of the pull request.


## Example Outputs 
### Pair 1 (Android)
**variant1** = getodk/collect (Destination)  
**variant2** = grzesiek2010/collect (Source)  
**Fork-Date** = 2016-12-02T11:26:41Z  
**Stop-Date** = 2020-01-01T00:00:00Z  

**Merged PR**  
PR-3531 : MergeCommit - 0f6558c0 :  MergeDate-2019-12-16T09:45:03Z : #Commits = 2  
Merged Commits | [b87c46ba, 0f96afbe]  

**Squashed PR**
PR-3462 : MergeCommit - 1ab3fe3e : MergeDate-2019-12-03T10:10:20Z : #Commits = 3  
SquashedCommit | [PRCommits]  
1ab3fe3e | [3508d2e8, f44f5e9d, 1d912dd4]  


**Rebased PR**  
PR-3434 : MergeCommit-56f4623d :  MergeDate-2019-11-05T14:14:16Z : #Commits = 5  
|prCommit : baseRepoCommit|   |965a0728:56f4623d|a2a92d30:e1e19a71|55d63aaa:1c9e54ea|86a93057:6b34bb0c|42267310:9fa9bc55|  

#### Summary of Commit Integration
**PR Integrated Commits**    
#MergedPR = 95 | #MergedPRCommits = 444   
#SquashedPR = 441 | #SquashedPRCommits = 1071  
#RebasedPR = 27 | #RebasedPRCommits = 161

**Git Integrated Commits**  
Number of git merge/rebased commits = 1305  
Number of git cherry-picked commits = 0  

**Unique commits**  
variant1 Unique commits = 3,051  
variant2 Unique commits = 1,305  

### Pair 2 (Android)
**variant1** = k9mail/k-9 (Destination)  
**variant2** = philipwhiuk/k2 (Source)  
**Fork-Date** = 2018-01-17T20:06:10Z  
**Stop-Date** = 2018-01-17T20:07:10Z

**Squashed PR**  
PR-3078 : MergeCommit-e80aa401 : MergeDate-2018-01-17T20:07:10Z : #Commits = 3  
SquashedCommit | [PRCommits]  
e80aa401 | [74156dc4, cc9d2cbf, 7ee7ba6e]  

#### Summary of Commit Integration
**PR Integrated Commits**    
#MergedPR = 0 | #MergedPRCommits = 0   
#SquashedPR = 1 | #SquashedPRCommits = 3  
#RebasedPR = 0 | #RebasedPRCommits = 0  
  
**Git Integrated Commits**  
Number of git merge/rebased commits = 1  
Number of git cherry-picked commits = 0  

**Unique Commits**  
uniqueCommitsVariant1_size = 1  
uniqueCommitsVariant2_size = 1  

### Pair 3 (Android)
**variant1** = FredJul/Flym (Destination)  
**variant2** = Etuldan/spaRSS (Source)  
**Fork-Date** = 2015-01-28T17:31:32Z  
**Stop-Date** = 2016-10-17T21:06:42Z  

#### Summary of Commit Integration
**PR Integrated Commits**    
#MergedPR = 0 | #MergedPRCommits = 0   
#SquashedPR = 0 | #SquashedPRCommits = 0  
#RebasedPR = 0 | #RebasedPRCommits = 0  

**Git Integrated Commits**  
Number of git merge/rebased commits = 34  
Number of git cherry-picked commits = 5

**variant1-Commit : variant2-Commit : Cherrypick-Source-Repo**  
| fd9714a1:63d93bb5:variant2 | 495e9b0f:2aa986d1:variant2 | 3dfe67e7:6ed9d2e1:variant2 | 5fb5fee3:c0dfb2c4:variant2 | f42200ca:bd8d04b4:variant2 |  

**Unique Commits**  
uniqueCommitsVariant1_size = 120  
uniqueCommitsVariant2_size = 272  

### Pair 4 (Android)

**variant1** = dashevo/dash-wallet (Destination)  
**variant2** = sambarboza/dash-wallet (Source)  
**Fork-Date** = 2018-01-11T20:39:31Z  
**Stop-Date** = 2020-10-01T00:06:42Z 

**Merged PR**  
PR-421 : MergeCommit-c4d8801b :  MergeDate-2020-05-29T21:28:37Z : #Commits = 2 : Merged
Merged Commits | [46a88296, 8982189c] 

**Squashed PR**
PR-333 : MergeCommit-3b0f2d16 : MergeDate-2020-02-13T15:10:27Z : 
#Commits = 2 : Squashed
SquashedCommit | PRCommits
3b0f2d16 | [3b788fe3, cc493a58]

**Rebased PR**
PR-114 : MergeCommit-6614463d :  MergeDate-2018-05-31T02:50:25Z : #Commits = 3 Rebased
|prCommit : baseRepoCommit| -- |ac5cf8c3:6614463d|354d36cb:5c5b7304|3b8a3985:65c327d5|

#### Summary of Commit Integration
**PR Integrated Commits**    
#MergedPR = 3 | #MergedPRCommits = 13 
#SquashedPR = 43 | #SquashedPRCommits = 194
#RebasedPR = 2 | #RebasedPRCommits = 6 
#Unclassified PR Integration = 26 | Commits = 167

**Git Integrated Commits**  
Number of git merge/rebased commits = 405  
Number of git cherry-picked commits = 0  

**Unique commits**  
variant1 Unique commits = 414  
variant2 Unique commits = 410  

### Pair 5 (JavaScript)

**variant1** = TerriaJS/terriajs (Source)  
**variant2** = bioretics/rer3d-terriajs (Destination)  
**Fork-Date** = 2018-01-11T20:39:31Z  
**Stop-Date** = 2021-04-01T00:06:42Z 

**Merged PR**  
PR-4 : MergeCommit-48d0a02 :  MergeDate-2018-01-27T22:13:06Z : #Commits = 56 : Merged
Merged Commits | [11d2006d5, 070a1687c] only a sample of the first two commits 

**Squashed PR**
No squash prs

**Rebased PR**
No rebase Prs

#### Summary of Commit Integration
**PR Integrated Commits**    
#MergedPR = 9 | #MergedPRCommits = 1,926 
#SquashedPR = 0 | #SquashedPRCommits = 0
#RebasedPR = 0 | #RebasedPRCommits = 0 
#Unclassified PR Integration = 0 | Commits = 0

**Git Integrated Commits**  
Number of git merge/rebased commits = 0  
Number of git cherry-picked commits = 10  

**Unique commits**  
variant1 Unique commits = 225  
variant2 Unique commits = 268 

### Pair 6 (.NET)

**variant1** = lagbug/YoutubeExtractor (Destination)  
**variant2** = Kimmax/SYMMExtractor (Source)  
**Fork-Date** = 2015-06-01T21:38:37Z  
**Stop-Date** = 2017-02-01T11:38:20Z 

**Merged PR**  
PR-195 : MergeCommit-27be8a8d :  MergeDate-2016-06-29T18:22:06Z : #Commits = 1 : Merged
Merged Commits | [76820c2d]

**Merged PR**  
PR-160 : MergeCommit-2de2232e :  MergeDate-2015-12-21T11:29:44Z : #Commits = 1 : Merged
Merged Commits | [2fb0b8d2]

**Squashed PR**
No squash prs

**Rebased PR**
No rebase Prs

#### Summary of Commit Integration
**PR Integrated Commits**    
#MergedPR = 2 | #MergedPRCommits = 2 
#SquashedPR = 0 | #SquashedPRCommits = 0
#RebasedPR = 0 | #RebasedPRCommits = 0 
#Unclassified PR Integration = 0 | Commits = 0

**Git Integrated Commits**  
Number of git merge/rebased commits = 3  
Number of git cherry-picked commits = 2 

**Unique commits**  
variant1 Unique commits = 42  
variant2 Unique commits = 16 
