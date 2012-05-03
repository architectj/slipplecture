package net.slipp.repository.qna;

import java.util.Date;

import net.slipp.domain.qna.Question;
import net.slipp.domain.user.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration("classpath:applicationContext.xml")
public class QuestionRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	@Rollback(false)
	public void create() {
		User user = 
				new User("javajigi", "password", "name", "javajigi@gmail.com", true);
		Question question = new Question();
		question.writedBy(user);
		question.setTitle("제목입니다.");
		question.setContents("이건 내용입니다.");
		question.setCreatedDate(new Date());
		question.initializeTags("java eclipse", tagRepository);
		
		questionRepository.save(question);
		
		question.setTitle("제목입니다. 2");
		question.setContents("이건 내용입니다. 수정");
		question.setCreatedDate(new Date());
		question.initializeTags("eclipse ant", tagRepository);		
	}

}
