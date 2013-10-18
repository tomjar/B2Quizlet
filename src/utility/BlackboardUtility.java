package utility;

import java.util.ArrayList;
import java.util.List;
import beans.error.Error;
import blackboard.data.ValidationException;
import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.Score;
import blackboard.data.user.User;
import blackboard.persist.BbPersistenceManager;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.gradebook.LineitemDbLoader;
import blackboard.persist.gradebook.ScoreDbLoader;
import blackboard.persist.gradebook.ScoreDbPersister;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.persistence.PersistenceServiceFactory;

public class BlackboardUtility {

	/**
	 * 
	 */
	public BlackboardUtility()
	{
		
	}
	
	
	/**
	 * This method returns the BbPersistenceManager object
	 * @return
	 */
	public BbPersistenceManager getBbPersistenceManager()
	{
		BbPersistenceManager bpm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();
		return bpm;
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not load the score by course membership id and line item and id");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not load the course membership by course and user id");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not load the user list by the couse id.");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not load the line item list by the course id.");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not retrieve the course membership db loader.");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not retrieve the score sb loader.");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not retrieve line item db loader.");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not retrieve the user db loader.");
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
			Error error = new Error();
			error.writeErrorToLog(e, "Could not retrieve the score persister instance.");
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
			Error error = new Error();
			error.writeErrorToLog(e, "The score has incorrect or corrupted data.");
		}catch (PersistenceException e)
		{
			Error error = new Error();
			error.writeErrorToLog(e, "Could not insert the new score into the database.");
		}
	}
}
