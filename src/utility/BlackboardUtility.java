package utility;

import java.util.ArrayList;
import java.util.List;

import blackboard.data.ValidationException;
import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.Score;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.gradebook.LineitemDbLoader;
import blackboard.persist.gradebook.ScoreDbLoader;
import blackboard.persist.gradebook.ScoreDbPersister;
import blackboard.persist.user.UserDbLoader;

public class BlackboardUtility {

	/**
	 * 
	 */
	public BlackboardUtility()
	{
		
	}
	
	/**
	 * This method returns a score object.
	 * @param coursememberShipId
	 * @param lineItemId
	 * @return
	 */
	public Score getScoreObject(Id courseMembershipId, Id lineItemId, ScoreDbLoader sl)
	{
		Score score = null;
		try{
			score = sl.loadByCourseMembershipIdAndLineitemId(courseMembershipId, lineItemId);
		}catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return score;
	}
	
	/**
	 * 
	 * @return
	 */
	public Id getCourseMembershipId(Id courseId, Id userId, CourseMembershipDbLoader cml)
	{
		Id courseMembershipId = null;
		try{
			courseMembershipId = cml.loadByCourseAndUserId(courseId, userId).getId();
		}catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return courseMembershipId;
	}
	
	/**
	 * 
	 * @param courseId
	 * @param ul
	 * @return
	 */
	public List<User> getUserListByCourseId(Id courseId, UserDbLoader ul)
	{
		
		List<User> userList = new ArrayList<User>();
		try
		{
			userList = ul.loadByCourseId(courseId);
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * 
	 * @param courseId
	 * @param ll
	 * @return
	 */
	public List<Lineitem> getLineItemListByCourseId(Id courseId, LineitemDbLoader ll)
	{
		List<Lineitem> lineItemList = new ArrayList<Lineitem>();
		try
		{
			lineItemList = ll.loadByCourseId(courseId);
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return lineItemList;
	}

	/**
	 * 
	 * @return
	 */
	public CourseMembershipDbLoader getCourseMembershipDbLoader()
	{
		CourseMembershipDbLoader cml = null;
		try{
			cml = CourseMembershipDbLoader.Default.getInstance();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return cml;
	}
	
	/**
	 * 
	 * @return
	 */
	public ScoreDbLoader getScoreDbLoader()
	{
		ScoreDbLoader sl = null;
		try{
			sl = ScoreDbLoader.Default.getInstance();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return sl;
	}
	
	/**
	 * 
	 * @return
	 */
	public LineitemDbLoader getLineItemDbLoader()
	{
		LineitemDbLoader ll = null;
		try{
			ll = LineitemDbLoader.Default.getInstance();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return ll;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public UserDbLoader getUserDbLoader()
	{
		UserDbLoader ul = null;
		try{
			ul = UserDbLoader.Default.getInstance();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		return ul;
	}
	
	/**
	 * 
	 * @return
	 */
	public ScoreDbPersister getScoreDbPersister()
	 {
		ScoreDbPersister scorePersister = null;
		try{
			scorePersister = ScoreDbPersister.Default.getInstance();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		 return scorePersister;
	 }
	
	/**
	 * 
	 * @param score
	 */
	public void persistScoreObject(ScoreDbPersister sp, Score score)
	{
		try{
			sp.persist(score);
		}
		catch(ValidationException e)
		{
			
		}catch (PersistenceException e)
		{
			
		}
	}
}
