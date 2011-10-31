package org.applause.lang.generator.wp7.builder;

import org.applause.lang.applauseDsl.Application;
import org.applause.lang.ui.builder.AbstractBuildStrategy;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.xpand2.output.Outlet;

public class WP7BuildStrategy extends AbstractBuildStrategy {

	@Override
	protected String getGeneratedSourcesFolderName() {
		return "Generated";
	}

	@Override
	protected String getMainTemplateName() {
		return "templates::Main::main";
	}
	
	@Override
	protected boolean canBuildProject() {
		IFile wp7Solution = findFile(getPlatformProject(), ".*\\.csproj");
		boolean isWP7 = (wp7Solution != null && wp7Solution.exists());
		System.out.println(getPlatformProject() + " is a WP7  project: " + isWP7);		
		return isWP7;
	}

	@Override
	protected void configureOutlet(Outlet outlet) {
		// do nothing
	}
	
	@Override
	public String getName() {
		return "Windows Phone 7";
	}

	@Override
	protected void copySplash(Application app, IFolder folder)
			throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IFolder getHighResImageDestinationFolder(IFile file) {
		return null;
	}

	@Override
	protected String getHighResImageFileName(String normalizedFileName) {
		return null;
	}

	@Override
	protected IFolder getImageDestinationFolder(IFile file) {
		return null;
	}

	@Override
	protected String getImageFileName(String normalizedFileName) {
		return null;
	}

}
