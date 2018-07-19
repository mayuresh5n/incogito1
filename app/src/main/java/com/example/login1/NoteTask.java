package com.example.login1;

public class NoteTask {
    private String taskName;
    private String note;
    private int id;
   
    public NoteTask()
    {
        this.taskName=null;
    }
    public NoteTask(String taskName, String note) {
        super();
        this.taskName = taskName;
        this.note = note;
    }
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getNote() {
		return note;
    }
    public void setNote(String note) {
    	this.note = note;
    }
   
}