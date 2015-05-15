package com.example.activity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.object.Book;
import com.example.object.BookParser;
import com.example.object.PullBookParser;

/**
 * XML 常用解析类展示
 * 
 * @author chenjy
 * @create: 2015/5/5
 */
public class XMLParseActivity extends Activity {

	private Button read;
	private Button write;

	private BookParser parser;
	private List<Book> books;

	private static final String TAG = "XMLParseActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xmlparse_main);

		read = (Button) findViewById(R.id.read);
		write = (Button) findViewById(R.id.write);

		read.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					InputStream is = getAssets().open("books.xml");
					// parser = new SaxBookParser(); // sax解析
					parser = new PullBookParser(); // pull解析
					books = parser.parse(is); // 解析输入流
					for (Book book : books) {
						Log.i(TAG, book.toString());
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		write.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					String xml = parser.serialize(books);// 序列化
					FileOutputStream fos = openFileOutput("books.xml",
							Context.MODE_PRIVATE);
					fos.write(xml.getBytes("UTF-8"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
