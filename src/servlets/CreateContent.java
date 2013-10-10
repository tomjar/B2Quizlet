package servlets;

import blackboard.base.FormattedText;
import blackboard.data.ValidationException;
import blackboard.data.content.Content;
import blackboard.data.course.Course;
import blackboard.persist.BbPersistenceManager;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbPersister;
import blackboard.platform.persistence.PersistenceServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class createContent
 */
@WebServlet(name = "createContent", urlPatterns = { "/CreateContent" })
public class CreateContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String quizletEmbedHTML = "<iframe src=\"http://quizlet.com/%s/familiarize/embedv2/\" height=\"410\" width=\"100%%\" style=\"border:0;\"></iframe>";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String courseId = request.getParameter("courseId");
		String contentId = request.getParameter("contentId");
		String quizletId = request.getParameter("quizletId");
		String quizletName = request.getParameter("quizletName");
		String httpRef = request.getParameter("http_ref");

		if (courseId != null || contentId != null) {
			try {
				String quizletHTML = this.formatQuizletEmbedHTML(quizletId);
				this.createContentItem(quizletHTML, quizletName, courseId,
						contentId);

			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("error.jsp");
				// e.printStackTrace();
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("error.jsp");
				// e.printStackTrace();
			}

			response.sendRedirect(httpRef
					+ "?inline_receipt_message=Content%20Added.");
		} else {

			response.sendRedirect(httpRef
					+ "?inline_receipt_message=Content%20Added.");
		}

	}

	/**
	 * This method receives the many parameters and then creates and calls the
	 * needed Blackboard functions. This function adds some html into the
	 * Content Management System(CMS). This is pretty much a "boiler plate"
	 * method of adding content into the CMS.
	 * 
	 * @param quizletHtml
	 *            this is the quizletHtml but with the quiz id that the user
	 *            entered
	 * @param quizletTitle
	 *            this is the quizlet title name, the user enters this on the
	 *            form
	 * @param courseId
	 *            this is the course id generated from the
	 *            quizlet_content_mashup
	 * @param contentId
	 *            this is the content id generated from the
	 *            quizlet_content_mashup
	 * @throws PersistenceException
	 * @throws ValidationException
	 */
	public void createContentItem(String quizletHTML, String quizletName,
			String courseId, String contentId) throws PersistenceException,
			ValidationException {
		/*
		 * This is the manager for inserting content into the Blackboard Learn
		 * databsse.
		 */
		BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance()
				.getDbPersistenceManager();
		/* This a Blackboard Learn Content object */
		Content content = new Content();
		/*
		 * Set the content title, content has numerous attributes for numerous
		 * types of content
		 */
		content.setTitle(quizletName);
		/* This creates a formatted text object with a type of html. */
		FormattedText text = new FormattedText(quizletHTML,
				FormattedText.Type.HTML);
		/* Set the content body of this content object */
		content.setBody(text);
		/*
		 * Types of content handlers resource/x-bb-document - Document
		 * resource/x-bb-folder - Folder resource/x-bb-lesson - Learning Unit
		 * resource/x-bb-externallink - External Link resource/x-bb-courselink -
		 * Course Link resource/x-bb-file - File (as used in a Learning Unit)
		 */
		content.setContentHandler("resource/x-bb-document");
		/* These two ids are used for database insertion of the content item */
		Id idParentId = bbPm.generateId(Course.DATA_TYPE, contentId);
		Id idCourseId = bbPm.generateId(Course.DATA_TYPE, courseId);
		content.setCourseId(idCourseId);
		content.setParentId(idParentId);
		/* Persister is what attempts to insert the content item. */
		ContentDbPersister persister = (ContentDbPersister) bbPm
				.getPersister(ContentDbPersister.TYPE);
		/* try to insert the content. */
		persister.persist(content);
	}

	/**
	 * This function simply formats the quizlet embed html value with the
	 * entered quizlet id.
	 * 
	 * @param quizletId
	 *            the quizlet id
	 * 
	 * @return the quizletHTML with the user entered quizlet id.
	 */
	public String formatQuizletEmbedHTML(String quizletId) {
		String quizletHTML = String.format(this.quizletEmbedHTML, quizletId);
		return quizletHTML;
	}

}
