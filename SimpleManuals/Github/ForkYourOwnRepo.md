**TLDR**

(note difference original and origin)(Create the repo in git before you do this, don't add licence/gitignore/readme)
https://github.com/a-t-0/CoursePlanningTemplate.git original
cd original
git remote set-url origin https://github.com/a-t-0/AE4890-11-Planetary-Sciences-I.git 
git push -u origin master


**Lengthy**
Sometimes you want a private copy of a public repo, or just a copy of a repo, in your own account without having to create an extra organization.
You can just copy the content, but then you lose the history of the original repo, meaning you can't pull from it because git doesn't know how to merge
because it doesnt know of a difference with the other was is 1 new change, or 2 separate changes, 1 per repo.

Lets call the original repository: original
Lets call the (private) copy repository: target


Solution:
0. Open github in browser
1. create the new target repository with name target
2. open cmd
3. browse to "c:/path with space/"
`c:`
`c:/path with space/"
4.  clone the orignal repository:
git clone https://github.com/a-t-0/original.git original
5. go to into the orignal repository on your pc in cmd:
cd original
6. set the branch origin (master) of repository target as the target to push the content of your original repository to:
git remote set-url origin https://github.com/a-t-0/target.git
7. Push the data of the original repository to the origin master branch of the target repository:
git push -u origin master
8. Suppose you changed something in a specific file of the original repository and want to copy that over to your target branch without manually copying each file:
8. Now set the original repo as source of getting data, (as a remote source/repo)
git remote add public_template https://github.com/a-t-0/original.git
10. Get what's changed of that original repository:
git fetch public_template
11. Merge the changes of the original repository into the master branch of the target repository:
git merge public_template/master

that's it now:
git status
git push
