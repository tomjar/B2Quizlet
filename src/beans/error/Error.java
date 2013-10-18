package beans.error;

import blackboard.platform.log.LogServiceFactory;

public class Error {
	
	public Error()
	{
		
	}
	
	/**
	 * This method simply writes a basic error to the error log.
	 * @param error the error that occurred
	 * @param message a message explaining the error a bit.
	 */
	public void writeErrorToLog(Exception error, String message)
	{
		message += error.getMessage();
		LogServiceFactory.getInstance().logError(message);
	}
	
	/**
	 * This method simply writes a basic error to the error log.
	 * @param error the error that occurred
	 * @param message a message explaining the error a bit.
	 */
	public void writeFatalErrorToLog(Exception error, String message)
	{
		message += error.getMessage();
		LogServiceFactory.getInstance().logError(message);
	}
	
	/**
	 * This method simply writes a basic error to the error log.
	 * @param error the error that occurred
	 * @param message a message explaining the error a bit.
	 */
	public void writeInfoErrorToLog(Exception error, String message)
	{
		message += error.getMessage();
		LogServiceFactory.getInstance().logError(message);
	}
}
