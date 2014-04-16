package com.example.textviewclick;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView mTextView2;     
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.textview);  
        
        mTextView2 = (TextView)findViewById(R.id.text2);  
        /********************************** ͬһ��TextView��ͬ���ֵĵ���¼�******************************/
		StringBuilder actionText = new StringBuilder();
		actionText
				.append("<a style=\"text-decoration:none;\" href='username'>"
						+ "username:" + " </a>");
		actionText
				.append("������"
						+ "<a style=\"color:blue;text-decoration:none;\" href='singstar'> "
						+ " love" + "</a>");
			actionText.append(" : \"" + "������" + "\"");
		mTextView2.setText(Html.fromHtml(actionText.toString()));
		mTextView2.setMovementMethod(LinkMovementMethod
				.getInstance());
		CharSequence text = mTextView2.getText();
		int ends = text.length();
		Spannable spannable = (Spannable) mTextView2.getText();
		URLSpan[] urlspan = spannable.getSpans(0, ends, URLSpan.class);
		SpannableStringBuilder stylesBuilder = new SpannableStringBuilder(text);
		stylesBuilder.clearSpans(); // should clear old spans
		for (URLSpan url : urlspan) {
			TextViewURLSpan myURLSpan = new TextViewURLSpan(url.getURL());
			stylesBuilder.setSpan(myURLSpan, spannable.getSpanStart(url),
					spannable.getSpanEnd(url), spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		mTextView2.setText(stylesBuilder);
    }
    
    private class TextViewURLSpan extends ClickableSpan {
		private String clickString;

		public TextViewURLSpan(String clickString) {
			this.clickString = clickString;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
		    ds.setColor(MainActivity.this.getResources().getColor(R.color.text_color));
		    ds.setUnderlineText(false); //ȥ���»���
		}
		
		@Override
		public void onClick(View widget) {
			if (clickString.equals("username")) {
				Toast.makeText(getApplication(), clickString, Toast.LENGTH_LONG)
				.show();
			} else if (clickString.equals("singstar")) {
				Toast.makeText(getApplication(), clickString, Toast.LENGTH_LONG)
						.show();
			}
		}
	}

}
