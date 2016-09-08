package com.tagmycode.eclipse;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.tagmycode.plugin.AbstractTaskFactory;

public class TaskFactory extends AbstractTaskFactory {

	public void create(final Runnable runnable, final String title) {
		Job job = new Job(title + "...") {
			protected IStatus run(IProgressMonitor monitor) {
				runnable.run();
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.schedule();
	}

}
