package net.slipp.domain.qna;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.slipp.domain.CreatedDateEntityListener;
import net.slipp.domain.HasCreatedDate;
import net.slipp.domain.user.User;
import net.slipp.repository.qna.TagRepository;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@EntityListeners({ CreatedDateEntityListener.class })
public class Question implements HasCreatedDate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long questionId;

	@Column(name = "writer_id", nullable = false)
	private String writerId;
	
	@Column(name = "writer_name", nullable = false)
	private String writerName;

	@Column(name = "title", length=100, nullable = false)
	private String title;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "question_content_holder", joinColumns = @JoinColumn(name = "question_id", unique = true))
	@org.hibernate.annotations.ForeignKey(name = "fk_question_content_holder_question_id")
	@Lob
	@Column(name = "contents", nullable = false)
	private Collection<String> contentsHolder;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@Column(name = "answer_count", nullable = false)
	private int answerCount = 0;

	@Column(name = "show_count", nullable = false)
	private int showCount = 0;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "question_tag", 
				joinColumns = @JoinColumn(name = "question_id"), 
				inverseJoinColumns = @JoinColumn(name = "tag_id"))
	@org.hibernate.annotations.ForeignKey
				(name = "fk_question_tag_question_id", inverseName = "fk_question_tag_tag_id")
	private Set<Tag> tags = Sets.newHashSet();
	
	@Transient
	private String plainTags;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	@OrderBy("answerId DESC")
	private List<Answer> answers;
	
	public Question() {
	}
	
	Question(String writerId, String writerName, String title, String contents, String plainTags) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.title = title;
		setContents(contents);
		this.plainTags = plainTags;
	}

	public void addAnswer(Answer answer) {
		if (answers == null) {
			answers = Lists.newArrayList();
		}
		answers.add(answer);
		answerCount += 1;
		answer.setQuestion(this);
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	
	public int getAnswerCount() {
		return answerCount;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void setContents(String newContents) {
		if (isEmptyContentsHolder()) {
			contentsHolder = Lists.newArrayList(newContents);
		} else {
			contentsHolder.clear();
			contentsHolder.add(newContents);
		}
	}
	
	private boolean isEmptyContentsHolder() {
		return contentsHolder == null || contentsHolder.isEmpty();
	}

	public String getContents() {
		if (isEmptyContentsHolder()) {
			return "";
		}

		return Iterables.getFirst(contentsHolder, "");
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	
	public void writedBy(User user) {
		this.writerId = user.getUserId();
		this.writerName = user.getName();
	}

	public String getWriterId() {
		return writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public String getPlainTags() {
		String displayTags = "";
		for (Tag tag : this.tags) {
			displayTags += tag.getName() + " ";
		}
		return displayTags;
	}

	public void setPlainTags(String plainTags) {
		this.plainTags = plainTags;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void update(Question newQuestion) {
		this.title = newQuestion.title;
		this.contentsHolder = newQuestion.contentsHolder;
		this.plainTags = newQuestion.plainTags;
	}
	
	public void initializeTags(String plainTags, 
			TagRepository tagRepository) {
		String[] parsedTags = parsedTags(plainTags);
		Set<Tag> newTags = loadTags(parsedTags, tagRepository);
		addedTags(newTags);
		removedTags(newTags);
		tags = newTags;
	}

	private void removedTags(Set<Tag> newTags) {
		Set<Tag> removedTags = Sets.difference(tags, newTags);
		for (Tag tag : removedTags) {
			tag.detagged();
		}
	}

	private void addedTags(Set<Tag> newTags) {
		Set<Tag> addedTags = Sets.difference(newTags, tags);
		for (Tag tag : addedTags) {
			tag.tagged();
		}
	}

	private String[] parsedTags(String plainTags) {
		String[] parsedTags = plainTags.split(" ");
		return parsedTags;
	}
	
	private Set<Tag> loadTags(String[] parsedTags, TagRepository tagRepository) {
		Set<Tag> loadTags = new HashSet<Tag>();
		for (int i = 0; i < parsedTags.length; i++) {
			loadTags.add(tagRepository.findByName(parsedTags[i]));
		}
		return loadTags;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", writerId=" + writerId + ", writerName=" + writerName
				+ ", title=" + title + ", contentsHolder=" + contentsHolder + ", createdDate=" + createdDate
				+ ", answerCount=" + answerCount + ", showCount=" + showCount + ", tags=" + tags + ", plainTags="
				+ plainTags + ", answers=" + answers + "]";
	}
}
