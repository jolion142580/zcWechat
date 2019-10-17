package com.gdyiko.zcwx.po.resp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="message") // 标注类名为XML根节点
@XmlAccessorType(XmlAccessType.FIELD) // 表示将所有域作为XML节点
public class Messages {

	private List<Article> article;

	public void setArticle(List<Article> article) {
		this.article = article;
	}

	public List<Article> getArticle() {
		return article;
	}

	@Override
	public String toString() {
		return "Messages [article=" + article + "]";
	}



}
