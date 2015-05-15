package com.example.object;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * pull 解析
 * 
 * @author chenjy
 * @create 2015/5/5
 */
public class PullBookParser implements BookParser {

	private List<Book> books;
	private Book book;

	@Override
	public List<Book> parse(InputStream is) throws Exception {
		// TODO Auto-generated method stub

		XmlPullParser parser = Xml.newPullParser();// 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF_8");// 设置输入流 并指明编码方式

		int eventType = parser.getEventType();

		// 内部循环解析每个节点
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT: // 文档开始
				books = new ArrayList<Book>();
				break;

			case XmlPullParser.START_TAG:// book节点开始
				if (parser.getName().equals("book")) {
					book = new Book();
				} else if (parser.getName().equals("id")) {
					eventType = parser.next();
					book.setId(Integer.parseInt(parser.getText()));
				} else if (parser.getName().equals("name")) {
					eventType = parser.next();// 这里只是读到了标签 name 要获取其文本值需要往下走
					book.setName(parser.getText());
				} else if (parser.getName().equals("price")) {
					eventType = parser.next();
					book.setPrice(Float.parseFloat(parser.getText()));
				}
				break;

			case XmlPullParser.END_TAG:// book节点结束
				if (parser.getName().equals("book")) {
					books.add(book);
					book = null;
				}
				break;
			}
			eventType = parser.next();// 节点往下走
		}

		return books;
	}

	@Override
	public String serialize(List<Book> books) throws Exception {

		// 由android.util.Xml创建一个XmlSerializer实例
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		serializer.setOutput(writer);// 设置输出方向为writer
		serializer.startDocument("UTF-8", true);
		serializer.startTag("", "books");
		for (Book book : books) {
			serializer.startTag("", "book");
			serializer.attribute("", "id", book.getId() + "");

			serializer.startTag("", "name");
			serializer.text(book.getName());
			serializer.endTag("", "name");

			serializer.startTag("", "price");
			serializer.text(book.getPrice() + "");
			serializer.endTag("", "price");

			serializer.endTag("", "book");
		}
		serializer.endTag("", "books");
		serializer.endDocument();

		return writer.toString();
	}

}
