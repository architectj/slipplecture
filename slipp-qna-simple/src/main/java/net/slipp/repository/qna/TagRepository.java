package net.slipp.repository.qna;

import org.springframework.data.repository.CrudRepository;

import net.slipp.domain.qna.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
	Tag findByName(String name);
}
