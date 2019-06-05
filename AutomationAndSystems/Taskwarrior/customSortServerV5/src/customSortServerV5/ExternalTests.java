package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ExternalTests {


//	private HardCoded hardCoded;
//
//	
//	public void createHardCoded() {
//		hardCoded  = new HardCoded();
//	}

	
	/**
	 * creates the powershell script that launches the wsl with the command that launches
	 * the JavaServerSort.jar 
	 */
	@BeforeAll
	public static void createPowershellScript() {
		HardCoded hardCoded = new HardCoded();
//		System.out.println("CreatedHardCODED");
		CreateFiles.createPowershellLauncherScript(hardCoded);
		CreateFiles.createPowershellWhoamiScript(hardCoded);
	}
	


	/**
	 * This method uses MoveTestFiles to temporarily absorb (copy to internal
	 * temporary memory) the backlog.data and pending.data file and puts the
	 * respective custom made backlog.data and pending.data files back into the
	 * taskwarrior. Then it runs the main custom sort script Verifies the results
	 * And then copies the original files back to their respective locations.
	 * 
	 * TODO: Make different test files with single and multiple tasks without
	 * recurrence and print what happens to them.
	 */

	/**
	 * ID:0 Test A: Test that has a single task in the tasklist without any backlog.
	 * Expect A: Expect the task to be placed in the backlog since the first task is
	 * always stored. Test B: Repeat with all non-recurrent Expect B: That task is
	 * not placed in the backlog!! Result B: That task was added to the backlog.
	 * Test C: Repeat with all recurrent parent Expect C: Test D: Repeat with all
	 * recurrent child Expect D:
	 */
	@Test
	public void testMainSort2() {
		HardCoded hardCoded = new HardCoded();
//		System.out.println("SHOULDV PRINTED THIS TESTFOLDER");
		// absorb original backlog.data and pending.data files for safekeeping
		MoveTestFiles moveTestFiles = new MoveTestFiles(hardCoded);

		// copy backlog0.data to home
		String sourcePath = hardCoded.getTestDataFolder();
		String sourceFileName = "backlog0.data";
		String destinationPath = hardCoded.getUbuntuFilePath();
		String destinationFileName = hardCoded.getBacklogFileName();
		File mockTestFile = new File(sourcePath + sourceFileName);
		MoveTestFiles.exportResource(mockTestFile, destinationPath, destinationFileName, false);

		// run main.
		System.out.println("Running the main!");
		RunPowershell.runPowershell(RunPowershell.powershellCommand(hardCoded),false);

		// read backlog.data

		// restore original backlog.data and pending.data files.

		assertTrue(false);
	}

	/**
	 * ID:1 Test A: that has a single task in the tasklist with that same task in
	 * backlog. Expect A: Expect the task to NOT be placed in the backlog since the
	 * first task is always stored. Test B: Repeat with all non-recurrent Expect B:
	 * Test C: Repeat with all recurrent parent Expect C: Test D: Repeat with all
	 * recurrent child Expect D:
	 */

	/**
	 * ID:2 Test A: that has two tasks in the tasklist without any backlog Expect A:
	 * Expect both task to be placed in the backlog since the first task is always
	 * stored. Test B: Repeat with all non-recurrent Expect B: Test C: Repeat with
	 * all recurrent parent Expect C: Test D: Repeat with all recurrent child Expect
	 * D:
	 */

	/**
	 * ID:3 Test A: that has two tasks in the tasklist both in backlog already, but
	 * with sole modification: they don't have customsort value (and they have a
	 * different modified value than the new backlog tasks will have). Expect A:
	 * Expect none of the two tasks will be added, since it is just the modification
	 * of the customsort value (backlog remains same) Test B: Repeat with all
	 * non-recurrent Expect B: Test C: Repeat with all recurrent parent Expect C:
	 * Test D: Repeat with all recurrent child Expect D:
	 */

	/**
	 * ID:4 Test A: that has two tasks in the tasklist both in backlog already, but
	 * with some extra modification in description: value (and they have a different
	 * modified value than the new backlog tasks will have). Expect A: Expect none
	 * of the two tasks will be added, since it is just the modification of the
	 * customsort value (backlog remains same) Test B: Repeat with all non-recurrent
	 * Expect B: Test C: Repeat with all recurrent parent Expect C: Test D: Repeat
	 * with all recurrent child Expect D:
	 */





	/**
	 * create a file in c.
	 * 
	 * @param content
	 */
	public static void createFile2(String linuxPath, String fileName) {
		{
			try {
				File file = new File(linuxPath + fileName);

				if (file.createNewFile()) {
					System.out.println("File is created! in Linuxpath"+linuxPath);
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
