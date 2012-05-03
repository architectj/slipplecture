package net.slipp.domain.qna;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import net.slipp.repository.qna.TagRepository;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
	private Question question;
	
	@Before
	public void setup() {
		question = new QuestionBuilder()
			.title("title")
			.contents("this is contents")
			.build();
	}
	
	@Test
	public void create() throws Exception {
		TagRepository tagRepository = new MockTagRepository();
		question.initializeTags("java eclipse", tagRepository);
		Set<Tag> tags = question.getTags();
		assertThat(tags.size(), is(2));
		for (Tag tag : tags) {
			assertThat(tag.getTaggedCount(), is(1));
		}
	}
	
	@Test
	public void update() throws Exception {
		TagRepository tagRepository = new MockTagRepository();
		question.initializeTags("java eclipse", tagRepository);
		Set<Tag> tags = question.getTags();
		assertThat(tags.size(), is(2));
		
		question.initializeTags("eclipse ant", tagRepository);
		assertThat(tags.size(), is(2));
		
		assertThat(tagRepository.findByName("java").getTaggedCount(), is(0));
		assertThat(tagRepository.findByName("eclipse").getTaggedCount(), is(1));
		assertThat(tagRepository.findByName("ant").getTaggedCount(), is(1));
	}
}
