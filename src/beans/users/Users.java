package beans.users;

import java.util.ArrayList;
import java.util.List;

import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.user.UserDbLoader;

public class Users {
	
	/**
	 * This is a default no parameter constructor, 
	 * which is needed in order to call methods in jsp pages. 
	 * after you import it to the jsp page
	 */
	public Users()
	{
		
	}
	
	/**
	 * This method retrieves a list of users for a specific course. The courseId
	 * @param courseId this parameter is given by the context object from quizlet_content_mashup.jsp
	 * @return a string containing the email's of the users for this course
	 */
	public String getUserInfo(Id courseId)
	{
		String userInfo = "";
		List<User> userList = new ArrayList<User>();
		try {
			UserDbLoader ul = UserDbLoader.Default.getInstance();

			userList = ul.loadByCourseId(courseId);
		}
		catch (KeyNotFoundException e)
		{
			e.printStackTrace();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		/*Get a list of user objects, this simply concatenates all of the users email addresses*/
		for(int i = 0; i < userList.size(); i++)
		{
			userInfo += userList.get(i).getEmailAddress() + "\n";
		}
		
		return userInfo;
	}

}
