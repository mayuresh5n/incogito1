package com.example.login1;


	public class comment1 {
		  private long id;
		  private String comment;

		  public long getId() {
		    return id;
		  }

		  public void setId(long id) {
		    this.id = id;
		  }
		  public String getComment() {
		    return comment;
		  }

		  public void setComment(String comment) {
		    this.comment = comment;
		  }
		  public comment1(String s) {
			  this.comment=s;
		}
		   
		  public comment1(long id) {
			  this.id=id;
		}
		  public comment1() {
			// TODO Auto-generated constructor stub
		}

		// Will be used by the ArrayAdapter in the ListView
		  @Override
		  public String toString() {
		    return comment;
		  }
		} 


