package beans.courses;
import utility.BlackboardUtility;
import blackboard.base.FormattedText;
import blackboard.data.announcement.Announcement;
import blackboard.data.content.CourseDocument;
import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.persist.BbPersistenceManager;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;

public class Courses {
	
	/**
	 * The default no parameter constructor.
	 */
	public Courses()
	{
		
	}

	
	/**
	 * This method creates a new course document and creates a 
	 * @param courseIdent
	 * @param contentIdent
	 */
	public void createNewCourseDocument(String courseIdent, String contentIdent)
	{
		BlackboardUtility blackboardUtility = new BlackboardUtility();
		//CourseMembershipDbLoader courseMembershipLoader = blackboardUtility.getCourseMembershipDbLoader();
		BbPersistenceManager bPersistManager = blackboardUtility.getBbPersistenceManager();
		CourseDocument courseDoc = new CourseDocument();
		Announcement announcement = new Announcement();
		FormattedText courseDocBodyText = new FormattedText("hello world!<br>",FormattedText.Type.HTML);
		
		try {
			Id courseId = bPersistManager.generateId(Course.DATA_TYPE, courseIdent);
			Id parentId = bPersistManager.generateId(CourseDocument.DATA_TYPE, contentIdent);
			
			/*The course document information. There are many more values that need to be set*/
			courseDoc.setCourseId(courseId);
			courseDoc.setParentId(parentId);
			courseDoc.setBody(courseDocBodyText);
			courseDoc.setContentHandler("resource/x-bb-b2quizlet-content-mashup");
			
			/*The course document information, this will announce that a assignment or course document has been inserted.*/
			announcement.setBody(courseDocBodyText);
			announcement.setCourseId(courseId);
			
			
			
			
			
			/* This is how the course document is set in the webwork building block
			 * contentCourseDocument.setTitle(this.publishData.getName()[assignmentNumber]);
						contentCourseDocument.setBody(comments);
						contentCourseDocument.setContentHandler(constantContentHandlerType);
						contentCourseDocument.setCourseId(generatedCourseId);
						contentCourseDocument.setParentId(generatedParentId);
						contentCourseDocument.setIsLesson(true);
						contentCourseDocument.setIsFolder(false);
						contentCourseDocument.setIsAvailable(this.publishData.getIsAvailable()[assignmentNumber]);
						contentCourseDocument.setIsTracked(this.publishData.getIsTracked()[assignmentNumber]);
						calendarStartObject.setTime(constantDateFormat2.parse(this.publishData.getStartDate()[assignmentNumber]));
						contentCourseDocument.setStartDate(calendarStartObject);
						calendarEndObject.setTime(constantDateFormat2.parse(this.publishData.getEndDate()[assignmentNumber]));
						contentCourseDocument.setEndDate(calendarEndObject);
						contentCourseDocument.setLaunchInNewWindow(true);
			 */
	
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
