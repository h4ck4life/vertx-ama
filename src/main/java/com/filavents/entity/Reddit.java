package com.filavents.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reddit_ama")
public class Reddit {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "QA_ID", nullable = false)
    private String qaId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQaId() {
        return qaId;
    }

    public void setQaId(String qaId) {
        this.qaId = qaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reddit reddit = (Reddit) o;
        return Objects.equals(userId, reddit.userId) && Objects.equals(qaId, reddit.qaId) && Objects.equals(title, reddit.title) && Objects.equals(url, reddit.url) && Objects.equals(question, reddit.question) && Objects.equals(answer, reddit.answer) && Objects.equals(timestamp, reddit.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, qaId, title, url, question, answer, timestamp);
    }

    @Override
    public String toString() {
        return "Reddit{" +
                "userId='" + userId + '\'' +
                ", qaId='" + qaId + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}