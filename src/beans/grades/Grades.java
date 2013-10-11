package beans.grades;

import utility.BlackboardUtility;

import java.util.ArrayList;
import java.util.List;

import com.sun.java.swing.plaf.nimbus.SliderPainter;

import blackboard.persist.*;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.gradebook.LineitemDbLoader;
import blackboard.persist.gradebook.ScoreDbPersister;
import blackboard.data.course.CourseMembership;
import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.Score;
import blackboard.data.user.User;
import blackboard.persist.gradebook.ScoreDbLoader;
import blackboard.persist.user.UserDbLoader;

public class Grades {

	/**
	 * This is a default no parameter constructor, 
	 * which is needed in order to call methods in jsp pages. 
	 * after you import it to the jsp page
	 */
	public Grades()
	{
		
	}
	
	/**
	 * This function simply retrieves a list of scores based on the course.
	 * @param courseId courseId this parameter is given by the context object from quizlet_content_mashup.jsp
	 * @return a concatenated string of scores/grades
	 */
	public String getGradesByCourseId(Id courseId)
	{
		BlackboardUtility bu = new BlackboardUtility();
		String grades = "";
		List<Lineitem> lineItemList = new ArrayList<Lineitem>();
		List<User> userList = new ArrayList<User>();
		Score score = null;
		Id courseMembershipId = null;
		int i = 0, j = 0;
		UserDbLoader ul = bu.getUserDbLoader();
		LineitemDbLoader ll = bu.getLineItemDbLoader();
		ScoreDbLoader sl = bu.getScoreDbLoader();
		CourseMembershipDbLoader cml = bu.getCourseMembershipDbLoader();
		lineItemList = bu.getLineItemListByCourseId(courseId, ll);
		userList = bu.getUserListByCourseId(courseId, ul);
		
			/*This simply concatenates all of the grade items values, simply a demonstration*/
			for(i = 0; i < userList.size(); i++)
				{
					courseMembershipId = bu.getCourseMembershipId(courseId, userList.get(i).getId(), cml);
					for(j = 0; j < lineItemList.size(); j++)
					{
						/*course membership id and line item id*/
						score = bu.getScoreObject(courseMembershipId, lineItemList.get(j).getId(), sl);
						if(score != null)
						{
							grades +=  score.getGrade() + "\n";
						}
						else
						{
							ScoreDbPersister scorePersister = bu.getScoreDbPersister();
							score = new Score();
							score.setCourseMembershipId(courseMembershipId);
							score.setLineitemId(lineItemList.get(j).getId());
							bu.persistScoreObject(scorePersister, score);
						}
					}
				}

		return grades;
	}
}
