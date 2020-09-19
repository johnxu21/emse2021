# Scripts for mining Integrated Commits
This repository contains scripts used to mine integrated commits between any two connected repositories. For example mainline (**variant1**) and fork (variant2). **variant1** and **variant2** can be interchanged. For any two connected repositories, the scripts are able to identify the following commits in the order below: 
1. Pull requests (PR) integration and their commits
a. Merged pull requests 
b. Squashed pull requests
c. Rebased pull requests
2. Git integration
a. Merge/Rebase commits
b. Cherry-picked commits Ð The source and destination of the cherry-picked commits is also determined.

## How to run
Import the project into any of your favorite editors. The code uses the following libraries: **json-simple-1.1.jar**, **joda-time-2.0.jar**. The libraries should be included in the build path of the project. The main project can be found in in the file **pr/IntegratedCommits.java**.

### Example Outputs 
**variant1 = opendatakit/collect**
**variant2 = grzesiek2010/collect**

#### Merged PR
PR-3531 : MergeCommit - 0f6558c0 :  MergeDate-2019-12-16T09:45:03Z : #Commits = 2
Merged Commits | [b87c46ba, 0f96afbe]

#### Squashed PR
PR-3462 : MergeCommit - 1ab3fe3e : MergeDate-2019-12-03T10:10:20Z : #Commits = 3
SquashedCommit | PRCommits 
1ab3fe3e | [3508d2e8, f44f5e9d, 1d912dd4]

#### Rebased PR
PR-3434 : MergeCommit-56f4623d :  MergeDate-2019-11-05T14:14:16Z : #Commits = 5
|prCommit : baseRepoCommit| -- |965a0728:56f4623d|a2a92d30:e1e19a71|55d63aaa:1c9e54ea|86a93057:6b34bb0c|42267310:9fa9bc55|




  

