package net.sshplendid.examples.spring.todo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todoId;
    private String content;
    private Date createdDate;
    private Date modifiedDate;
    private Boolean completion;

//    protected Todo() {}
    public Todo(String content, Date createdDate, Date modifiedDate, Boolean completion) {
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.completion = completion;
    }

    public Long getTodoId() {
        return todoId;
    }

    private void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getCompletion() {
        return completion;
    }

    public void setCompletion(Boolean completion) {
        this.completion = completion;
    }

    public static class Builder {
        private String content;
        private Date createdDate;
        private Date modifiedDate;
        private Boolean completion;

        public Builder content(String content) {
            this.content = content;
            return this;
        }
        public Builder completion(Boolean completion) {
            this.completion = completion;
            return this;
        }

        public Todo build() {
            return new Todo(content, new Date(), new Date(), completion);
        }
    }
}
