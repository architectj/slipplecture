package net.slipp.domain.qna;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.slipp.domain.CreatedAndUpdatedDateEntityListener;
import net.slipp.domain.HasCreatedAndUpdatedDate;

@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Answer implements HasCreatedAndUpdatedDate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long answerId;
	
	@Column(name = "writer_id", nullable = false)
	private String writerId;
	
	@Column(name = "writer_name", nullable = false)
	private String writerName;
	
	private String contents;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;
	
	@ManyToOne
	@org.hibernate.annotations.ForeignKey(name = "fk_answer_parent_id")
	private Question question;

	void setQuestion(Question question) {
		this.question = question;
	}
	
	public Question getQuestion() {
		return this.question;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", writerId=" + writerId + ", writerName=" + writerName + ", contents="
				+ contents + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", question=" + question
				+ "]";
	}
}
