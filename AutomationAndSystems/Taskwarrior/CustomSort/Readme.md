limitations:
------------
0. Currently a task where the dependency is set to 0 (Probably task id 0 which does not exist), is not processed and dangles on top of the list. 


Fast usage instructions:
------------
0. Copy `customSort.sh` and `javaSort.jar` from the folder `quickUse` in this repository to the folder `/home/<username>/customReport` of your WSL Ubuntu 16.04 using steps:

	0.1 Download the files to for example `E:\somefolder\TaskWarrior\CustomSort\quickUse\` Switch the slashes for Linux to: `E:/somefolder/TaskWarrior/CustomSort/quickUse/`.
	
	0.2 Switch the windows style drive letter `E:/` to Linux style: `/mnt/e` and copy the files:
		
`cp -a /mnt/e/somefolder/TaskWarrior/CustomSort/quickUse/. /home/<yourUsername>/maintenance/`
	
	0.3 Make the bash script runnable (otherwise it says: "permission denied"):
`chmod +x "/home/<yourUsername>/maintenance/customSort.sh"`

	
	
1. browse (WSL) Ubuntu (16.04) to `/home/<yourUsername>/maintenance`

2. type:
	
`/customSort.sh` (or if your in for example `/home/` type `./<yourUsername>/maintenance/customSort.sh`)

3. Wait for 4 minutes, the tasks are now sorted top to bottom on: 
	
	3.a project if the urgency is below threshold 13.4, 
	
	3.b then on urgency: first tasks without project, 
	
	3.c then tasks with project.

4. Get a structured overview of your tasks with command: 

`task nice0`

5. (start+arrow to right to make screen smaller and then larger again to get a compact overview)

6. (You can use the AutoHotKey (AHK) scripts as given in this public repository, to map keys alt+arrow up/down to scroll in WSL Ubuntu 16.04 (and cmd) with the arrows.) 

7. Create cronjob that runs customSort.sh every x minutes with:

Compile yourself instructions (in Windows):
------------
### Prerequisites: ###

0. Installed JDK (8+) or higher (not sure if JRE is also required)

1. Link to JDK (8+ or higher:https://www.oracle.com/technetwork/java/javase/downloads/index.html)

2. Added Java Developement Kit (JDK (8+) to path.)

	2.1 Can be done by going to/finding <yourharddrive>:/programfiles/java/jdk-<versionnr>/bin
	
	2.2 click on start>type "environment variables" 
	
	2.3 then again click on environmentvariables or advanced/something like that=smt
	
	2.4 then there is a "path" variable for the user and again for the pc. 
	
	2.5 open it/edit and "ADD" in top name jdk and in the bottom field the path you just found.	
	
	2.6 (reboot?)
	
	2.7 More accurate description/manual is found via duckduckgo/ecosia 
	

### Steps: ###

0. open cmd

1. browse to "the folder this Readme.md is in"/compileYourself/javaCustomSort/src/customSortTaskwarrior/compilation

Enter (for backwards compatibility since this script currently ensures highest JDK version 8. = 1.8 in Linux.):

```
del ClassFive.class

del Pair.class

del StringPair.class

del javaSort.jar

javac ClassFive.java -target 1.8 -source 1.8

javac Pair.java -target 1.8 -source 1.8

javac StringPair.java -target 1.8 -source 1.8

jar -cvmf manifest.mf javaSort.jar *.class
```

(You can run the java separately with: java -jar javaSort.jar (but that requires the output of the customSort.sh)

2. Open (WSL) Ubuntu (16.04) and browse in terminal(=command prompt for Linux) to: "the folder this Readme.md is in"/compileYourself/javaCustomSort/src/customSortTaskwarrior/

3. Enter: 
./customSort.sh

4. Wait for 3+minutes

5. If it's done it has sorted all tasks (excluding messed up dependency tasks) from top to bottom on: 
	 	If below threshold:(currently 13.4) 
			If has project: 
				on project
			Else:
				On urgency (low to high)
		Else:
			On urgency (low to high)

6. And it has made a new custom report type with User Defined Attribute (UDA) secretSort to store the customSorted Order.

7. You can now view your tasks in an overview using command: task nice0

8. To see how, after compilation you can put the sorting in a cron job to run the sorting code in the background without user 
interaction, see quick use.


Todo's:
------------
0. Remove all verbosity to make it run in background

1. Create a subfolder for the output that is created every run to keep it structured

2. Add customSort.sh to cronjob every 10 minutes

3. Write an efficient cronjob that detects whether taskwarrior has been changed by user w.r.t. previous state/minute, to prevent 
unnecessary running of:

	3.1 customSort.sh cronjob

	3.2 AutoBackup, (also saves discspace)

4. Ensure the layout is only with empty lines only between projects and not between tasks.

5. Ensure the view is compact regardless of window format.

6. Either:

	6.1 Convert jar to exe to make execution independent of java in Ubuntu 16.04

	6.2 Convert jar to different type that Ubuntu 16.04 can execute without requireing dependencies

	6.3 Write quick bash check to check version of jdk = 1.8 or higher so that the get-apt update can be ommitted to speed up bash

	6.4 Extend customSort.sh again to do the sorting as well, and then optimize to make it faster, and remove java dependency.

7. Refactor ClassFive.java to proper non-single-variable-name in class, test class, manifest, customsort.

8. Write Compilation instructions for Ubuntu 16.04.

9. Todo: create and document cronjob for quick use to run this sorting in background every 10 minutes.
