package net.slipp.repository.qna;

import net.slipp.domain.qna.Tag;

public interface TagRepository {
	Tag findByName(String name);
}
