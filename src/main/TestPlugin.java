package main;

import interfaces.Lorder;
import interfaces.PluginService;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.Before;
import util.MySystem;
import view.*;
//import org.junit.Test;

public class TestPlugin extends PluginService {

	/*@Test
    public void testMethod(){
		System.out.println("test");
	}*/

    @Override
    public void onCreate(Lorder lorder) {
        super.onCreate(lorder);
        CButton cButton = new CButton();
        CTextView cTextView = new CTextView();
        cTextView.setText("I am a textview please do not click me!");
        CEditView cEditView = new CEditView();
        cEditView.setSingleLine(true);
        cEditView.setSingleLine(true);
        cEditView.setText("我是一个单行的输入框");

        CImageView cImageView = new CImageView();
        cImageView.setImageResource("/fxml/image/folder_icon_16.png");

        CCheckBox cCheckBox = new CCheckBox();
        cCheckBox.setOnCheckedChangeListener(new CView.OnCheckedChangeListener() {
            @Override
            public void onCheckChanged(boolean b) {
                if (b) {
                    cCheckBox.setText("约");
                } else {
                    cCheckBox.setText("不约");
                }
            }
        });

        CProgressBar cProgressBar = new CProgressBar();
        cProgressBar.setProgress(0.5);

        //HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticleLayout verticleLayout = new VerticleLayout();
        verticleLayout.addView(cTextView);
        verticleLayout.addView(cButton);
        verticleLayout.addView(cEditView);
        verticleLayout.addView(cImageView);
        verticleLayout.addView(cCheckBox);
        verticleLayout.addView(cProgressBar);
        setContentView("hello word", verticleLayout);
        cButton.setText("弹窗按钮");
        cButton.setOnClickListener(new CView.onClickListener() {
            @Override
            public void onClicked() {
                lorder.showDialog("弹窗标题", "弹窗内容");
            }
        });
        System.out.println("A客户插件onCreate~");

        Thread thread = null;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 5000; i++) {
                    double a = Math.max(4.15, 4.153) * 3.1418926;
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    double b = i*0.002;
                    Platform.runLater(new Runnable() {
                        public void run() {
                            cProgressBar.setProgress(b);
                        }
                    });
                }
                System.out.println("end");
            }
        });
        thread.start();
    }

    @Override
    public void onDestroy(Lorder lorder) {
        System.out.println("A客户插件onDestroy~");
    }
    //must

    public static void main(String[] args) {

    }
}
