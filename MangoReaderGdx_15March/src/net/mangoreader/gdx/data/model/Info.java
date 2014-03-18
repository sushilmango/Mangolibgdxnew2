package net.mangoreader.gdx.data.model;

import java.util.ArrayList;

public class Info {

	private String android_status;
	private String currency;
	private String ios_status;
	private boolean is_free;
	private String kindle_status;
	private String language;
	private int mango_status;
	private int price;
	private String _id;
	private String[] authors;
	private String can_record;
	private String can_remix;
	private String can_translate;
	private String[] illustrators;
	private String publisher;
	private String [] translators;
	private String []vos;
	

	
	public String[] getVos() {
		return vos;
	}
	public void setVos(String[] vos) {
		this.vos = vos;
	}
	public String[] getTranslators() {
		return translators;
	}
	public void setTranslators(String[] translators) {
		this.translators = translators;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String[] getIllustrators() {
		return illustrators;
	}
	public void setIllustrators(String[] illustrators) {
		this.illustrators = illustrators;
	}
	public String getCan_translate() {
		return can_translate;
	}
	public void setCan_translate(String can_translate) {
		this.can_translate = can_translate;
	}
	public String getCan_remix() {
		return can_remix;
	}
	public void setCan_remix(String can_remix) {
		this.can_remix = can_remix;
	}
	public String getCan_record() {
		return can_record;
	}
	public void setCan_record(String can_record) {
		this.can_record = can_record;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public boolean isIs_free() {
		return is_free;
	}
	private ArrayList<String> age_groups;
	private ArrayList<String> categories;
	private ArrayList<String> grades;
	private ArrayList<String> learning_levels;
	private ArrayList<String> publish_targets;
	private ArrayList<String> subjects;
	private ArrayList<String> tags;
	
	
	public String getAndroid_status() {
		return android_status;
	}
	public void setAndroid_status(String android_status) {
		this.android_status = android_status;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIos_status() {
		return ios_status;
	}
	public void setIos_status(String ios_status) {
		this.ios_status = ios_status;
	}
	public boolean is_free() {
		return is_free;
	}
	public void setIs_free(boolean isFree) {
		this.is_free = isFree;
	}
	public String getKindle_status() {
		return kindle_status;
	}
	public void setKindle_status(String kindle_status) {
		this.kindle_status = kindle_status;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getMango_status() {
		return mango_status;
	}
	public void setMango_status(int mango_status) {
		this.mango_status = mango_status;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public ArrayList<String> getAge_groups() {
		return age_groups;
	}
	public void setAge_groups(ArrayList<String> age_group) {
		this.age_groups = age_group;
	}
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public ArrayList<String> getGrades() {
		return grades;
	}
	public void setGrades(ArrayList<String> grades) {
		this.grades = grades;
	}
	public ArrayList<String> getLearning_levels() {
		return learning_levels;
	}
	public void setLearning_levels(ArrayList<String> learning_levels) {
		this.learning_levels = learning_levels;
	}
	public ArrayList<String> getPublish_targets() {
		return publish_targets;
	}
	public void setPublish_targets(ArrayList<String> publish_targets) {
		this.publish_targets = publish_targets;
	}
	public ArrayList<String> getSubjects() {
		return subjects;
	}
	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	
}
