package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

public class CreateFiles {

	/**
	 * This creates the Vars file required in command 8
	 * 
	 * @param serverName
	 * @throws IOException
	 */
	public static void createVars(InstallData installData) throws IOException {

		System.out.println("Incoming path inc. filename:" + installData.getLinuxPath() + installData.getVars());
		deleteFile(installData.getVars());

		// create a file called vars with content "content"
		createFile2(installData.getLinuxPath(), installData.getVars());
		// createFile1(installData.getLinuxPath(),installData.getVars());

		// write content of vars file
		writeFileContent(installData, installData.getVars());
	}
	
	public static void createGCalSyncInstall(InstallData installData) throws IOException {

		System.out.println("Incoming path inc. filename:" + installData.getLinuxPath() + installData.getgCalSyncInstallScriptName());
		deleteFile(installData.getgCalSyncInstallScriptName());

		// create a file called vars with content "content"
		createFile2(installData.getLinuxPath(), installData.getgCalSyncInstallScriptName());
		// createFile1(installData.getLinuxPath(),installData.getVars());

		// write content of vars file
		writeFileContent(installData, installData.getgCalSyncInstallScriptName());
	}

	/**
	 * This creates the sudoers.sh file required in command 8
	 * 
	 * @param serverName
	 * @throws IOException
	 */
	public static void createSudoers(InstallData installData) throws IOException {

		System.out.println(
				"Incoming path inc. filename:" + installData.getLinuxPath() + installData.getSudoersFileName());
		deleteFile(installData.getSudoersFileName());

		// create a file called vars with content "content"
		createFile2(installData.getLinuxPath(), installData.getSudoersFileName());
		// createFile1(installData.getLinuxPath(),installData.getSudoersFileName());

		// write content of vars file
		writeFileContent(installData, installData.getSudoersFileName());
	}

	/**
	 * This method writes the content of the vars file.
	 * 
	 * @param installData
	 */
	public static void writeFileContent(InstallData installData, String fileName) {
		char quotation = (char) 34; // quotation mark "

		PrintWriter writer;
		try {
			writer = new PrintWriter(installData.getLinuxPath() + fileName, "UTF-8");
			switch (fileName) {
			case "vars":
				writer = writeLinesVars(installData, writer);
				break;
			case ".bashrc":
				writer = writeLinesBashrc(installData, writer);
				break;
			case "sudoers.sh":
				writer = writeLinesSudoers(installData, writer);
				break;
			case "twUuid.txt":
				writer = writeLinesExportTwUuid(installData, writer);
				break;
			case "gCalSyncInstaller.sh":
				writer = writeLinesGCalSyncInstaller(installData, writer);
			}

			writer.close();
			System.out.println("JUST WROTE CONTENT of " + fileName + " FILE! To path:"+installData.getLinuxPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Just failed at printing " + fileName + e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Just failed at printing " + fileName + e);
		}
	}

	/**
	 * Delete a file that is located in the same folder as the src folder of this
	 * project is located.
	 * 
	 * @param fileName
	 */
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		try {
			boolean result = Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // surround it in try catch block
	}

	/**
	 * create a file in c.
	 * 
	 * @param content
	 */
	public static void createFile2(String linuxPath, String fileName) {
		{
			try {

				// File file = new File("c:\\vars.txt");
				System.out.println("Creating new file0:" + linuxPath + fileName);
				File file = new File(linuxPath + fileName);

				if (file.createNewFile()) {
					System.out.println("File is created!");
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	public static void createFile1(String linuxPath, String fileName) {
//		try {
//			System.out.println("Creating new file1:"+linuxPath+fileName);
//            File file = new File(linuxPath+fileName);
//            file.createNewFile();
//            //file.createNewFile();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	}

	public static boolean checkIfFilesExist(String path, String[] filenames) {
		for (int i = 0; i < filenames.length; i++) {
			if (!checkIfFileExist(path, filenames[i])) {
				return false;
			}
			// String absFilePath = "/home/"+ubuntuUsername+"/.task/"+filename[i];
		}
		return true;
	}

	/**
	 * Checks if the file filename in folder path exists.
	 * 
	 * @param path
	 * @param filename
	 * @return
	 */
	public static boolean checkIfFileExist(String path, String filename) {

		// merge file path and file name to file object
		File f = new File(path + filename);

		// check if file exists
		if (f.exists() && !f.isDirectory()) {
//			System.out.println("File:" + path + filename + " exists");
			return true;
		} else {
//			System.out.println("ERROR!! The file:" + path + filename + " does NOT exist");
			return false;
		}
	}
	

	public static void makeScriptRunnable(String Path, String scriptName) {

		// create chmod command
		Command command = new Command();
		String[] commandLines = new String[4];

		commandLines[0] = "chmod";
		commandLines[1] = "+x";
		commandLines[2] = scriptName;
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath(Path);
		command.setSetWorkingPath(true);
	}

	public static PrintWriter writeLinesVars(InstallData installData, PrintWriter writer) {
		char quotation = (char) 34; // quotation mark "
		writer.println("BITS=4096");
		writer.println("EXPIRATION_DAYS=365");
		// writer.println("ORGANIZATION="+quotation+"G�teborg Bit Factory"+quotation);
		writer.println("ORGANIZATION=" + quotation + "Goteborg Bit Factory" + quotation);
		writer.println("CN=" + installData.getServerName() + ":" + installData.getServerPort());
		writer.println("COUNTRY=SE");
		// writer.println("STATE="+quotation+"V�stra G�taland"+quotation);
		writer.println("STATE=" + quotation + "Vastra Gotaland" + quotation);
		// writer.println("LOCALITY="+quotation+"G�teborg"+quotation);
		writer.println("LOCALITY=" + quotation + "Goteborg" + quotation);
		return writer;
	}

	public static PrintWriter writeLinesBashrc(InstallData installData, PrintWriter writer) {
		char quotation = (char) 34; // quotation mark "
		writer.println("#get root");
		writer.println("if [ ! -f /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool ]; then");
		writer.println("   echo " + quotation + "Getting sudo rights now." + quotation);
		writer.println("   sudo touch /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool");
		writer.println("   sudo -s");
		writer.println("fi");

		writer.println("# remove got root boolean for next time you boot up Unix");
		writer.println("sudo rm /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool");

		writer.println("#Start cron service");
		writer.println("sudo -i service cron start");

		writer.println("#Startup taskwarrior");
		writer.println("export TASKDDATA=/var/taskd");
		writer.println("cd $TASKDDATA");
		writer.println("sudo taskd config --data $TASKDDATA");

		writer.println("taskdctl start");
		writer.println("task sync");
		return writer;
	}

	public static PrintWriter writeLinesSudoers(InstallData installData, PrintWriter writer) {
		writer.println("#!/bin/sh");
		writer.println("sudo bash -c 'echo \"" + installData.getLinuxUserName()
				+ " ALL=(ALL) NOPASSWD:ALL\" >> /etc/sudoers'");
		return writer;
	}

	public static PrintWriter writeLinesGCalSyncInstaller(InstallData installData, PrintWriter writer) {
		char quotation = (char) 34; // quotation mark "
		writer.println("#!/bin/bash");
		writer.println("add-apt-repository ppa:jonathonf/python-3.6");
		writer.println("echo 1");
		writer.println("apt update");
		writer.println("echo 2");
		writer.println("yes | sudo apt install python3.6 python3-pip");
		writer.println("sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.5 1");
		writer.println("sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.6 2");
		writer.println("sudo -H pip3 install --upgrade pip");
		writer.println("sudo -H pip3 install virtualenv virtualenvwrapper");
		writer.println("export WORKON_HOME=$HOME/.virtualenvs");
		writer.println("export PROJECT_HOME=/mnt/c/Users/a/Code");
		writer.println("export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3.6");
		writer.println("source /usr/local/bin/virtualenvwrapper.sh");
		//TODO: Uncomment if required
		//writer.println("source ~/.bashrc");
		writer.println("python3 -V");
		writer.println("homeDirName="+quotation+"/home/a/"+quotation);
		writer.println("gCalSyncFolderName="+quotation+"gCalSync"+quotation);
		writer.println("task_gcal_syncFolderName="+quotation+"task_gcal_sync"+quotation);
		writer.println("cd "+quotation+"$(homeDirName "+quotation+"$0"+quotation+")"+quotation);
//		writer.println("mkdir gCalSync");
//		writer.println("cd gCalSync");
		writer.println("cd "+quotation+"$(/home/"+installData.getLinuxUserName()+"/"+installData.getMaintenanceFolder()+" "+quotation+"$0"+quotation+")"+quotation);
		writer.println("git clone https://github.com/bergercookie/taskw_gcal_sync.git");
		writer.println("cd "+installData.getgCalSyncCloneFolder());
		writer.println("cd "+quotation+"$(/home/"+installData.getLinuxUserName()+"/"+installData.getMaintenanceFolder()+"/"+installData.getgCalSyncCloneFolder()+" "+quotation+"$0"+quotation+")"+quotation);
		writer.println("pip3 install --user --upgrade requirements.txt");
		writer.println("pip3 install --user --upgrade taskw_gcal_sync");
		writer.println("/home/"+installData.getLinuxUserName()+"/"+installData.getMaintenanceFolder()+"/"+installData.getgCalSyncCloneFolder()+"/tw_gcal_sync --help");
		writer.println("tw_gcal_sync --help");
		writer.println("sudo ./tw_gcal_sync --help");
//		writer.println("tw_gcal_sync");
		writer.println("task add due:2019-06-01T13:01 tag:remindme testtask");
		writer.println("./tw_gcal_sync -c "+quotation+"TW Reminders"+quotation+" -t remindme");
		writer.println("python3 "+"/home/"+installData.getLinuxUserName()+"/"+installData.getgCalSyncCloneFolder()+"/"+"tw_gcal_sync -c "+quotation+"TW Reminders"+quotation+" -t remindme");
		return writer;
	}
	
	public static void exportTwUuid(InstallData installData) {
		writeFileContent(installData, installData.getTwUuidFileName());
		String sourcePath = installData.getLinuxPath();
		String destinationPath = installData.getCertificateOutputPath();
		try {
			CopyFiles.copyFileWithSudo(installData, sourcePath, installData.getTwUuidFileName(), destinationPath,
					installData.getTwUuidFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PrintWriter writeLinesExportTwUuid(InstallData installData, PrintWriter writer) {
		writer.println(installData.getTwUuid());
		return writer;
	}
}
