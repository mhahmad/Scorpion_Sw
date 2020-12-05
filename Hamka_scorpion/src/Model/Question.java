package Model;

import java.util.ArrayList;

public class Question {
	private String content ;
	private Level level ;
	private ArrayList<String> answers;
	private String rightAnswer ;
	
	public Question(String content , String level , ArrayList<String> answers , String rightAnswer) {
		this.answers = answers;
		this.content = content;
		this.rightAnswer = rightAnswer;
		if(level.equals("1"))
			this.level = Level.easy;
		else if(level.equals("2"))
			this.level = Level.medium;
		else
			this.level = Level.hard;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(String level) {
		if(level.equals("1"))
			this.level = Level.easy;
		else if(level.equals("2"))
			this.level = Level.medium;
		else
			this.level = Level.hard;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	
	public String getLevelNumber() {
		if(this.level == Level.easy)
			return "1";
		else if(this.level == Level.medium)
			return "2";
		else
			return "3";
	}
	@Override
	public String toString() {
		return "Question [content=" + content + ", level=" + level + ", answers=" + answers + ", rightAnswer="
				+ rightAnswer + "]";
	}
	
	
}
