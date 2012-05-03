package net.slipp.domain.qna;

import java.util.HashMap;
import java.util.Map;

import net.slipp.repository.qna.TagRepository;

public class MockTagRepository implements TagRepository {
	private Map<String, Tag> tags = new HashMap<String, Tag>();
	
	public MockTagRepository() {
		tags.put("java", new Tag("java"));
		tags.put("eclipse", new Tag("eclipse"));
		tags.put("ant", new Tag("ant"));
	}
	
	@Override
	public Tag findByName(String name) {
		return tags.get(name);
	}

}
