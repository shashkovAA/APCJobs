package Objects;

import java.io.File;
import java.util.ArrayList;

	
	public class JobFilesList {

		private String jobFolder;
		private String fileNameTrail;
		private ArrayList<String> jobFilesList;

	    public JobFilesList() {
	    }

	    public JobFilesList(String jobFolder, String fileNameTrail) {
	    	this.jobFolder = jobFolder;
	    	this.fileNameTrail = fileNameTrail;
	    }

	    public void setJobFileNameTrail(String fileNameTrail) {
	    	this.fileNameTrail = fileNameTrail;
	    }

	    public ArrayList<String> get() {
	    	jobFilesList = new ArrayList<String>();
	    	getListJobFilesInFolder();
	    	return jobFilesList;
	    }

	    private void getListJobFilesInFolder() {

		File jobsDir = new File(jobFolder);

		Debug.log.info("Get list of files in directory " + jobFolder);

		for (File file : jobsDir.listFiles()) {
		    if (!isDirectory(file)) {
			if (isHaveFileNameSpecifiedTrail(file)) {
			    if (!isEmptyFile(file)) {
			    	jobFilesList.add(file.getName());
			    	Debug.log.info("File :" + file.getName() + "   Seize :" + file.length());
			    } else
				Debug.log.warn("File :" + file.getName() + "   Seize :" + file.length()
					+ ". This file will be ignored. It is empty.");
			} else
			    Debug.log.warn("File :" + file.getName() + "   Seize :" + file.length()
				    + ". This file will be ignored. It is not *"+ fileNameTrail +" file.");
		    }
		}
	    }

	    public boolean isDirectory(File file) {
		return !file.isFile();
	    }

	    public boolean isEmptyFile(File file) {
		return file.length() == 0;
	    }

	    public boolean isHaveFileNameSpecifiedTrail(File file) {
		return file.getName().endsWith(fileNameTrail);
	    }
	

}
