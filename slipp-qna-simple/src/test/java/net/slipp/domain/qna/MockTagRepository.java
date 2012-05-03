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

	@Override
	public Tag save(Tag entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Tag> save(Iterable<? extends Tag> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Tag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Tag> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
